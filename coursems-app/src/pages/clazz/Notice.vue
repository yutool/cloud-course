<template>
  <div class="shadow p-3 rounded mb-5">
    <div class="clearfix p-2">
      <el-switch v-model="unread" active-text="只看未读"></el-switch>
      <el-button v-if="isTeacher" type="primary" class="float-right btn-flat" @click="dialogVisible=true">发布通知
      </el-button>
    </div>
    <el-row :gutter="20">
      <!-- 班级通知 -->
      <el-col :md="16" :sm="12" :xs="24">
        <div v-if="!notices.length" class="text-center pt-5">
          暂无班级通知
        </div>
        <notice-list v-else :notices="notices" />
      </el-col>
      <!-- 系统通知 -->
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
    <auto-dialog :title="'新建通知'" :visible="dialogVisible" :width="600" @close="closeDialog" class="m-dialog">
      <el-form>
        <el-input type="textarea" v-model="content" :rows="6" placeholder="请输入通知内容"></el-input>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitForm">确 定</el-button>
      </div>
    </auto-dialog>
  </div>
</template>

<script>
import { mapState, mapGetters } from 'vuex'
import NoticeList from './components/NoticeList'

export default {
  name: 'Notice',
  components: {
    NoticeList
  },
  data: () => ({
    unread: false,
    dialogVisible: false,
    content: ''
  }),
  computed: {
    ...mapState({
      course: state => state.clazz.course,
      notices: state => state.clazz.notices
    }),
    ...mapGetters(['isTeacher'])
  },
  methods: {
    submitForm () {
      var noticeForm = {
        content: this.content,
        author: this.course.teacherName,
        type: 3, // 班级通知
        courseId: this.course.courseId
      }
      this.$store.dispatch('clazz/sendNotice', noticeForm).then(res => {
        this.dialogVisible = false
        this.content = ''
      })
    },
    closeDialog () {
      this.dialogVisible = false
    }
  }
}
</script>

<style lang="scss" scoped>
.dialog-footer {
  padding: 10px 0;
  text-align: right;
}
</style>
