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
     * oper type ,eq 0 for DownLoad, eq 1 for Upload
     */
    private int operType;

    /**
     * server files for oper
     */
    private List<String> serverFiles;

    /**
     * local files for oper
     */
    private List<String> localFiles;



    @Override
    public boolean checkParameters() {
        if (operType == Flag.NO.ordinal() || operType == Flag.YES.ordinal()) {
            return StringUtils.isNotEmpty(ip)
                    && StringUtils.isNotEmpty(port)
                    && StringUtils.isNotEmpty(userName)
                    && StringUtils.isNotEmpty(password)
                    && !serverFiles.isEmpty()
                    && !localFiles.isEmpty();
        } else {
            return false;
        }
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
                ", operType=" + operType +
                ", serverFiles=" + serverFiles +
                ", localFiles=" + localFiles +
                '}';
    }
}
