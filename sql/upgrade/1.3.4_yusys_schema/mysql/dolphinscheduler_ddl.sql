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

drop table if exists dolphinscheduler.t_ds_sftp;
create table dolphinscheduler.t_ds_sftp
(
    id          int auto_increment comment 'key'
        primary key,
    name        varchar(64) not null comment 'sftp name',
    note        varchar(256) null comment 'description',
    type        tinyint     not null comment 'connect type: 0:password,1:privatekey',
    host        varchar(64) not null comment 'sftp host',
    port        int         not null comment 'sftp port',
    connect_name   varchar(64) not null comment 'sftp user',
    connect_key         varchar(64) not null comment 'sftp connect key(password or privatekey)',
    user_id     int         not null comment 'the creator id',
    create_time datetime    not null comment 'create time',
    update_time datetime null comment 'update time'
);



