<template>
  <div class="shadow p-3 rounded mb-5">
    <div class="clearfix p-2">
      <el-switch v-model="unread" active-text="只看未读"></el-switch>
      <el-button v-if="getIsTeacher" type="primary" class="float-right btn-flat" @click="dialogFormVisible=true">发布通知</el-button>
    </div>
    <el-row :gutter="20">
      <el-col :md="16" :sm="12" :xs="24">
        <!-- 暂无消息 -->
        <div v-if="!this.getClazzNotice.length" class="text-center pt-5">
          暂无班级通知
        </div>
        <el-card v-for="notice in this.getClazzNotice" :key="notice.noticeId" class="box-card mt-2" :body-style="{padding:'0px'}">
          <div class="border-bottom p-1 text-center">
            <span>班级通知</span>
            <Popconfirm v-if="getIsTeacher" title="确定删除通知吗？" width="200" @confirm="removeNotice(notice)" class="float-right">
              <i v-if="getIsTeacher" class="el-icon-delete float-right pr-3 pt-1 pointer"></i>
            </Popconfirm>
          </div>
          <div class="card-body ">
            <span>{{notice.content}}</span>
            <div class="hpx-10"></div>
            <small>{{notice.releaseTime}}<span style="margin:0 5px;">|</span>{{notice.author}}</small>
          </div>
        </el-card>
      </el-col>
      <el-col :md="8" :sm="12" :xs="0">
        <el-card class="box-card mt-2" :body-style="{padding:'0px'}">
          <div class="border-bottom p-1 text-center">
            <span>班级公告</span>
          </div>
          <div class="card-body ">
            <span>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Excepturi nam porro nobis dolorum facilis hic repellendus architecto, deserunt, asperiores voluptates, odio minima dolore corrupti natus. Quam consequuntur deserunt vero sint.</span>
            <div class="hpx-10"></div>
            <small>2019-09-09 14:01:08</small>
          </div>
        </el-card>
        <el-card class="box-card mt-2" :body-style="{padding:'0px'}">
          <div class="border-bottom p-1 text-center">
            <span>系统消息</span>
          </div>
          <div class="card-body ">
            <span>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Excepturi nam porro nobis dolorum facilis hic repellendus architecto, deserunt, asperiores voluptates, odio minima dolore corrupti natus. Quam consequuntur deserunt vero sint.</span>
            <div class="hpx-10"></div>
            <small>2019-09-09 14:01:08</small>
          </div>
        </el-card>
      </el-col>
    </el-row>
    <!-- 新建通知弹窗 -->
    <el-dialog title="新建通知" :visible.sync="dialogFormVisible" :width="dialogWidth">
      <el-form>
        <el-input type="textarea" v-model="content" :rows="6" placeholder="请输入通知内容"></el-input>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitForm">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { mapGetters, mapActions } from 'vuex'
import { sendNotice, deleteNotice } from '@/api/clazz'
export default {
  name: 'Notice',
  data: () => ({
    unread: false,
    dialogFormVisible: false,
    content: '',
    dialogWidth: document.documentElement.clientWidth > 600 ? '600px' : '100%'
  }),
  computed: {
    ...mapGetters(['getIsTeacher', 'getClazzId', 'getClazzDetail', 'getClazzNotice'])
  },
  methods: {
    ...mapActions(['addClazzNotice', 'removeClazzNotice']),
    submitForm () {
      var noticeForm = {
        content: this.content,
        releaseTime: new Date(),
        author: this.getClazzDetail.teaName,
        type: 3, // 班级通知
        clazzId: this.getClazzId
      }
      sendNotice(noticeForm).then(res => { // 发送通知
        if (res.status === 1) {
          this.addClazzNotice(res.content)
          this.dialogFormVisible = false
          this.content = ''
          this.$log.info('sendNotice', res)
        }
      })
    },
    removeNotice (notice) { // 删除通知
      deleteNotice(notice.noticeId).then(res => {
        if (res.status === 1) {
          for (let i = 0; i < this.getClazzNotice.length; i++) {
            if (notice === this.getClazzNotice[i]) {
              this.removeClazzNotice(i)
              this.$log.info('deleteNotice', res)
            }
          }
        }
      })
    }
  },
  mounted () {
    var _this = this
    window.onresize = () => {
      _this.dialogWidth = document.documentElement.clientWidth > 600 ? '600px' : '100%'
    }
  },
  watch: {
    dialogWidth (val) {
      this.dialogWidth = val
    }
  }
}
</script>

<style lang="scss" scoped>
</style>
