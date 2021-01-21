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
package org.apache.dolphinscheduler.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.dolphinscheduler.dao.entity.Sftp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * sftp mapper interface
 */
public interface SftpMapper extends BaseMapper<Sftp> {

    /**
     * query sftp by type
     *
     * @param userId userId
     * @param type   type
     * @return sftp list
     */
    List<Sftp> querySftpByType(@Param("userId") int userId, @Param("type") Integer type);

    /**
     * sftp page
     *
     * @param page   page
     * @param userId userId
     * @param name   name
     * @return sftp IPage
     */
    IPage<Sftp> selectPaging(IPage<Sftp> page,
                             @Param("userId") int userId,
                             @Param("name") String name);

    /**
     * query sftp by name
     *
     * @param name name
     * @return sftp list
     */
    List<Sftp> querySftpByName(@Param("name") String name);


    /**
     * query authed sftp
     *
     * @param userId userId
     * @return sftp list
     */
    List<Sftp> queryAuthedSftp(@Param("userId") int userId);

    /**
     * query sftp except userId
     *
     * @param userId userId
     * @return sftp list
     */
    List<Sftp> querySftpExceptUserId(@Param("userId") int userId);

    /**
     * list all sftp by type
     *
     * @param type sftp type
     * @return sftp list
     */
    List<Sftp> listAllSftpByType(@Param("type") Integer type);


    /**
     * list authorized UDF function
     *
     * @param userId  userId
     * @param sftpIds sftp id array
     * @param <T>     T
     * @return UDF function list
     */
    <T> List<Sftp> listAuthorizedSftp(@Param("userId") int userId, @Param("sftpIds") T[] sftpIds);


}
