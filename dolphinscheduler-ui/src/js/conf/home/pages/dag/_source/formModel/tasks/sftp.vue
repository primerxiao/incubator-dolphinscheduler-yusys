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
            v-model="sftpOperType"
            :disabled="isDetails">

<script>
import mListBox from './_source/listBox'
import mSftpOperType from './_source/sftpOperType'
import _ from "lodash";

export default {
  name: 'sftp',
  data() {
    return {
      sftpOperType: 0,
      sftpOperTypeList: [{ code: 0,value:'下载' }, { code: 1,value:'上传' }]
    }
  },
  mixins: [disabledState],
  props: {
    backfillItem: Object,
    createNodeId: Number
  },
  methods: {
    /**
     * return SftpOperType
     */
    _onSftpOperType(a) {
      this.sftpOperType = a
    },
    /**
     * verification
     */
    _verification() {
      return true
    }
  },
  watch: {
    // Listening to sqlType
  },
  created() {
    let o = this.backfillItem
    // Non-null objects represent backfill
    if (!_.isEmpty(o)) {
      this.sftpOperType = o.params.sftpOperType
    }
  },
  computed: {
    cacheParams() {
      return {}
    }
  },
  components: {mSftpOperType, mListBox}
}
</script>


