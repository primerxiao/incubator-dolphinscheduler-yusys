/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.dolphinscheduler.server.worker.task.sftp;


import com.alibaba.fastjson.JSONObject;
import com.jcraft.jsch.*;
import org.apache.dolphinscheduler.common.Constants;
import org.apache.dolphinscheduler.common.enums.SftpOperType;
import org.apache.dolphinscheduler.common.task.AbstractParameters;
import org.apache.dolphinscheduler.common.task.sftp.SftpParameters;
import org.apache.dolphinscheduler.common.utils.DateUtils;
import org.apache.dolphinscheduler.server.entity.TaskExecutionContext;
import org.apache.dolphinscheduler.server.worker.task.AbstractTask;
import org.slf4j.Logger;

import java.io.*;
import java.util.Properties;

/**
 * sftp down or upload task
 * @author gcb
 */
public class SftpTask extends AbstractTask {

    public static final String PATH_END_STR = "/";

    /**
     * task param
     */
    private SftpParameters sftpParameters;

    /**
     * taskExecutionContext
     */
    private TaskExecutionContext taskExecutionContext;

    private ChannelSftp sftp;

    private Session session;

    /**
     * constructor
     *
     * @param taskExecutionContext taskExecutionContext
     * @param logger               logger
     */
    public SftpTask(TaskExecutionContext taskExecutionContext, Logger logger) {
        super(taskExecutionContext, logger);
        this.taskExecutionContext = taskExecutionContext;
    }

    @Override
    public void init() {
        logger.info("http task params {}", taskExecutionContext.getTaskParams());
        this.sftpParameters = JSONObject.parseObject(taskExecutionContext.getTaskParams(), SftpParameters.class);

        if (!sftpParameters.checkParameters()) {
            throw new RuntimeException("sftp task params is not valid");
        }
    }

    @Override
    public void handle() throws Exception {
        String threadLoggerInfoName = String.format(Constants.TASK_LOG_INFO_FORMAT, taskExecutionContext.getTaskAppId());
        Thread.currentThread().setName(threadLoggerInfoName);
        long startTime = System.currentTimeMillis();
        try {
            logger.info("sftp task startTime: {}, sftp ip: {}, sftp port: {}, sftp username : {}",
                    DateUtils.format2Readable(startTime), sftpParameters.getIp(), sftpParameters.getPort(), sftpParameters.getUserName());
            if (SftpOperType.DOWN.getCode().equals(sftpParameters.getOperType())) {
                downFile();
            }
            if (SftpOperType.UPLOAD.getCode().equals(sftpParameters.getOperType())) {
                uploadFile();
            }
            exitStatusCode = 0;
        } catch (Exception e) {
            exitStatusCode = -1;
            logger.error("sftp task handle error", e);
            throw e;
        }
    }

    @Override
    public AbstractParameters getParameters() {
        return this.sftpParameters;
    }

    private void downFile() throws FileNotFoundException, JSchException, SftpException {
        try {
            login();
            download(sftpParameters.getServerFilePath(),
                    sftpParameters.getServerFileName(),
                    sftpParameters.getLocalFilePath().concat(PATH_END_STR).concat(sftpParameters.getLocalFileName()));
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("down file from sftp error{}", e.getMessage());
            throw e;
        } finally {
            logout();
        }

    }

    private void uploadFile() throws Exception {
        try {
            login();
            upload(sftpParameters.getServerFilePath(),
                    sftpParameters.getServerFileName(),
                    new FileInputStream(sftpParameters.getLocalFilePath().concat(PATH_END_STR).concat(sftpParameters.getLocalFileName())));
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("upload file to sftp error{}", e.getMessage());
            throw e;
        } finally {
            logout();
        }

    }

    private void login() throws JSchException {
        JSch jsch = new JSch();
        session = jsch.getSession(sftpParameters.getUserName(),
                sftpParameters.getIp(),
                Integer.parseInt(sftpParameters.getPort()));
        session.setPassword(sftpParameters.getPassword());
        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");
        session.setConfig(config);
        session.connect();
        Channel channel = session.openChannel("sftp");
        channel.connect();
        sftp = (ChannelSftp) channel;
    }

    private void logout() {
        if (sftp != null) {
            if (sftp.isConnected()) {
                sftp.disconnect();
            }
        }
        if (session != null) {
            if (session.isConnected()) {
                session.disconnect();
            }
        }
    }

    private void upload(String directory, String sftpFileName, InputStream input) throws SftpException {
        if (directory != null && !"".equals(directory)) {
            sftp.cd(directory);
        }
        //上传文件
        sftp.put(input, sftpFileName);
    }

    private void cd(String directory) throws SftpException {
        if (directory != null && !"".equals(directory) && !PATH_END_STR.equals(directory)) {
            sftp.cd(directory);
        }
    }

    private void download(String directory, String downloadFile, String saveFile) throws SftpException, FileNotFoundException {
        if (directory != null && !"".equals(directory)) {
            sftp.cd(directory);
        }
        File file = new File(saveFile);
        sftp.get(downloadFile, new FileOutputStream(file));
    }

}
