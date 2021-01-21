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

import org.apache.dolphinscheduler.common.enums.SftpConnectType;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.dolphinscheduler.common.enums.DbType;
import org.apache.dolphinscheduler.common.enums.UserType;
import org.apache.dolphinscheduler.common.utils.DateUtils;
import org.apache.dolphinscheduler.dao.entity.DataSource;
import org.apache.dolphinscheduler.dao.entity.DatasourceUser;
import org.apache.dolphinscheduler.dao.entity.Sftp;
import org.apache.dolphinscheduler.dao.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.junit.Assert.*;

/**
 * datasource mapper test
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback(true)
public class SftpMapperTest {

    /**
     * datasource mapper
     */
    @Autowired
    SftpMapper sftpMapper;

    /**
     * test insert
     */
    @Test
    public void testInsert() {
        Sftp sftp = new Sftp();
        sftp.setName("test");
        sftp.setNote("test");
        sftp.setType(SftpConnectType.PASSWORD);
        sftp.setHost("127.0.0.1");
        sftp.setPort(22);
        sftp.setConnectName("ocmsftpuser");
        sftp.setConnectKey("ocmsftpuser123");
        sftp.setUserId(0);
        sftp.setCreateTime(new Date());
        sftp.setUpdateTime(new Date());

        sftpMapper.insert(sftp);

        Sftp sftp1 = sftpMapper.selectById(sftp.getId());

        System.out.println(sftp1.toString());

    }

}