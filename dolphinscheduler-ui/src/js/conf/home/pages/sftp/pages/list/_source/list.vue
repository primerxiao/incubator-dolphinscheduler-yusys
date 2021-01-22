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
  <div class="list-model">
    <div class="table-box">
      <table class="fixed">
        <tr>
          <th scope="col">
            <span>{{$t('#')}}</span>
          </th>
          <th scope="col">
            <span>name</span>
          </th>
          <th scope="col">
            <span>note</span>
          </th>
          <th scope="col">
            <span>type</span>
          </th>
        </tr>
        <tr v-for="(item, $index) in list" :key="$index">
          <td>
            <span>{{parseInt(pageNo === 1 ? ($index + 1) : (($index + 1) + (pageSize * (pageNo - 1))))}}</span>
          </td>
          <td>
            <span class="ellipsis">
              {{item.name}}
            </span>
          </td>
          <td>
            <span class="ellipsis">
              {{item.note}}
            </span>
          </td>
          <td>
            <span class="ellipsis">
              {{item.type}}
            </span>
          </td>
        </tr>
      </table>
    </div>
  </div>
</template>
<script>
import { mapActions } from 'vuex'
import { findComponentDownward } from '@/module/util/'
import mTooltipsJSON from '@/module/components/tooltipsJSON/tooltipsJSON'

export default {
  name: 'sftp-list',
  data () {
    return {
      // list
      list: []
    }
  },
  props: {
    // External incoming data
    sftpList: Array,
    // current page number
    pageNo: Number,
    // Total number of articles
    pageSize: Number
  },
  methods: {
    ...mapActions('sftp', ['deleteSftp']),
    /**
     * Close delete popup layer
     */
    _closeDelete (i) {
      this.$refs[`poptip-delete-${i}`][0].doClose()
    },
    /**
     * Delete current line
     */
    _delete (item, i) {
      this.$refs[`poptip-delete-${i}`][0].doClose()
      this.deleteSftp({
        id: item.id
      }).then(res => {
        this.$emit('on-update')
        this.$message.success(res.msg)
      }).catch(e => {
        this.$message.error(e.msg || '')
      })
    },
    /**
     * edit
     */
    _edit (item) {
      findComponentDownward(this.$root, 'sftp-indexP')._create(item)
    }
  },
  watch: {
    /**
     * Monitor external data changes
     */
    sftpList (a) {
      this.list = []
      setTimeout(() => {
        this.list = a
      })
    }
  },
  created () {
    this.list = this.sftpList
  },
  mounted () {
  },
  components: { mTooltipsJSON }
}
</script>
