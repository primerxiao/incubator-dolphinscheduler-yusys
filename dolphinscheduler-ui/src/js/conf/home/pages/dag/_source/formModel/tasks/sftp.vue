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
<template>
  <div class="sftp-model">
    <m-list-box>
      <div slot="text">操作类型</div>
      <div slot="content">
        <div style="display: inline-block;">
          <x-select
            style="width: 150px;"
            v-model="operType"
            :disabled="isDetails">
            <x-option
              v-for="i in sftpOperTypeList"
              :key="i.code"
              :value="i.code"
              :label="i.value">
            </x-option>
          </x-select>
        </div>
      </div>
    </m-list-box>
    <m-list-box>
      <div slot="text">服务器ip</div>
      <div slot="content">
        <x-input
          :disabled="isDetails"
          type="input"
          v-model="ip"
          :placeholder="请输入sftp服务器ip地址"
          autocomplete="off">
        </x-input>
      </div>
    </m-list-box>
    <m-list-box>
      <div slot="text">端口</div>
      <div slot="content">
        <x-input
          :disabled="isDetails"
          type="input"
          v-model="port"
          :placeholder="请输入sftp服务器端口"
          autocomplete="off">
        </x-input>
      </div>
    </m-list-box>
    <m-list-box>
      <div slot="text">用户名</div>
      <div slot="content">
        <x-input
          :disabled="isDetails"
          type="input"
          v-model="userName"
          :placeholder="请输入sftp用户名"
          autocomplete="off">
        </x-input>
      </div>
    </m-list-box>
    <m-list-box>
      <div slot="text">密码</div>
      <div slot="content">
        <x-input
          :disabled="isDetails"
          type="input"
          v-model="password"
          :placeholder="请输入sftp密码"
          autocomplete="off">
        </x-input>
      </div>
    </m-list-box>
    <m-list-box>
      <div slot="text">服务端路径</div>
      <div slot="content">
        <x-input
          :disabled="isDetails"
          type="input"
          v-model="serverFilePath"
          :placeholder="请输入服务端路径"
          autocomplete="off">
        </x-input>
      </div>
    </m-list-box>
    <m-list-box>
      <div slot="text">服务端文件名</div>
      <div slot="content">
        <x-input
          :disabled="isDetails"
          type="input"
          v-model="serverFileName"
          :placeholder="请输入服务端文件名"
          autocomplete="off">
        </x-input>
      </div>
    </m-list-box>
    <m-list-box>
      <div slot="text">本地路径</div>
      <div slot="content">
        <x-input
          :disabled="isDetails"
          type="input"
          v-model="localFilePath"
          :placeholder="请输入本地路径"
          autocomplete="off">
        </x-input>
      </div>
    </m-list-box>
    <m-list-box>
      <div slot="text">本地文件名</div>
      <div slot="content">
        <x-input
          :disabled="isDetails"
          type="input"
          v-model="localFileName"
          :placeholder="请输入本地文件名"
          autocomplete="off">
        </x-input>
      </div>
    </m-list-box>
  </div>
</template>
<script>
import _ from "lodash";
import mListBox from './_source/listBox'
import disabledState from '@/module/mixin/disabledState'

export default {
  name: 'sftp',
  data() {
    return {
      sftpOperTypeList: [{code: '0', value: '下载'}, {code: '1', value: '上传'}],
      operType: '0',
      ip: '',
      port: '',
      userName: '',
      password: '',
      serverFilePath: '',
      serverFileName: '',
      localFilePath: '',
      localFileName: ''
    }
  },
  mixins: [disabledState],
  props: {
    backfillItem: Object
  },
  methods: {
    /**
     * verification
     */
    _verification() {
      if (!this.ip) {
        this.$message.warning('请输入ip地址')
        return false
      }
      if (!this.port) {
        this.$message.warning('请输入端口')
        return false
      }
      if (!this.userName) {
        this.$message.warning('请输入用户名')
        return false
      }
      if (!this.password) {
        this.$message.warning('请输入密码')
        return false
      }
      if (!this.serverFilePath) {
        this.$message.warning('请输入服务器路径')
        return false
      }
      if (!this.serverFileName) {
        this.$message.warning('请输入服务器文件名')
        return false
      }
      if (!this.localFilePath) {
        this.$message.warning('请输入本地路径')
        return false
      }
      // 如果是下载：服务器路径 和 服务器文件名 以及本地路径不能为空
      if (this.operType === '1') {
        if (!this.localFileName) {
          this.$message.warning('请输入本地文件名')
          return false
        }
      }
      // 缓存
      this.$emit('on-params', {
        operType: this.operType,
        ip: this.ip,
        port: this.port,
        userName: this.userName,
        password: this.password,
        serverFilePath: this.serverFilePath,
        serverFileName: this.serverFileName,
        localFilePath: this.localFilePath,
        localFileName: this.localFileName
      })
      return true
    }
  },
  computed: {
    cacheParams() {
      return {
        operType: this.operType,
        ip: this.ip,
        port: this.port,
        userName: this.userName,
        password: this.password,
        serverFilePath: this.serverFilePath,
        serverFileName: this.serverFileName,
        localFilePath: this.localFilePath,
        localFileName: this.localFileName
      }
    }
  },
  watch: {
    cacheParams(val) {
      this.$emit('on-cache-params', val);
    }
  },
  created() {
    let o = this.backfillItem
    // Non-null objects represent backfill
    if (!_.isEmpty(o)) {
        this.operType = o.params.operType
        this.ip = o.params.ip
        this.port = o.params.port
        this.userName = o.params.userName
        this.password = o.params.password
        this.serverFilePath = o.params.serverFilePath
        this.serverFileName = o.params.serverFileName
        this.localFilePath = o.params.localFilePath
        this.localFileName = o.params.localFileName
    }
  },

  components: {mListBox}
}
</script>


