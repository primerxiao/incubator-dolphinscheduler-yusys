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
package org.apache.dolphinscheduler.common.task.sftp;

import org.apache.commons.lang.StringUtils;
import org.apache.dolphinscheduler.common.enums.Flag;
import org.apache.dolphinscheduler.common.process.ResourceInfo;
import org.apache.dolphinscheduler.common.task.AbstractParameters;

import java.util.ArrayList;
import java.util.List;

/**
 * Sftp parameter
 */
public class SftpParameters extends AbstractParameters {

    /**
     * sftp server ip
     */
    private String ip;

    /**
     * sftp server port
     */
    private String port;

    /**
     * sftp user
     */
    private String userName;

    /**
     * sftp password
     */
    private String password;

    /**
     * oper type ,eq "0" for DownLoad, eq "1" for Upload
     */
    private String operType;

    /**
     * server filepath for oper
     */
    private String serverFilePath;

    /**
     * server filename for oper
     */
    private String serverFileName;

    /**
     * local filepath for oper
     */
    private String localFilePath;

    /**
     * local filename for oper
     */
    private String localFileName;


    @Override
    public boolean checkParameters() {
        return StringUtils.isNotEmpty(operType)
                && StringUtils.isNotEmpty(ip)
                && StringUtils.isNotEmpty(port)
                && StringUtils.isNotEmpty(userName)
                && StringUtils.isNotEmpty(password)
                && StringUtils.isNotEmpty(serverFilePath)
                && StringUtils.isNotEmpty(serverFileName)
                && StringUtils.isNotEmpty(localFilePath)
                && StringUtils.isNotEmpty(localFileName);
    }

    @Override
    public List<ResourceInfo> getResourceFilesList() {
        return new ArrayList<>();
    }

    @Override
    public String toString() {
        return "SftpParameters{" +
                "ip='" + ip + '\'' +
                ", port='" + port + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", operType='" + operType + '\'' +
                ", serverFilePath='" + serverFilePath + '\'' +
                ", serverFileName='" + serverFileName + '\'' +
                ", localFilePath='" + localFilePath + '\'' +
                ", localFileName='" + localFileName + '\'' +
                '}';
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOperType() {
        return operType;
    }

    public void setOperType(String operType) {
        this.operType = operType;
    }

    public String getServerFilePath() {
        return serverFilePath;
    }

    public void setServerFilePath(String serverFilePath) {
        this.serverFilePath = serverFilePath;
    }

    public String getServerFileName() {
        return serverFileName;
    }

    public void setServerFileName(String serverFileName) {
        this.serverFileName = serverFileName;
    }

    public String getLocalFilePath() {
        return localFilePath;
    }

    public void setLocalFilePath(String localFilePath) {
        this.localFilePath = localFilePath;
    }

    public String getLocalFileName() {
        return localFileName;
    }

    public void setLocalFileName(String localFileName) {
        this.localFileName = localFileName;
    }
}
