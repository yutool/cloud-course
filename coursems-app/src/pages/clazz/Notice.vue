<template>
  <div class="shadow p-3 rounded mb-5">
    <el-row>
      <!-- 导航栏 -->
      <el-col :md="6" :xs="24" class="notice-nav">
        <div class="nav-item" :class="{active: navIndex==0}" @click="navIndex = 0">
          <div class="nav-icon icon-inform"></div>
          <div class="nav-title">班课通知</div>
          <div class="nav-last-msg">
            <span v-if="notices.length">{{notices[0].content}}</span>
            <span v-else>暂无消息</span>
          </div>
        </div>
        <div class="nav-item" :class="{active: navIndex==1}" @click="navIndex = 1">
          <div class="nav-icon icon-advices"></div>
          <div class="nav-title">系统通知</div>
          <div class="nav-last-msg">暂无消息</div>
        </div>
      </el-col>
      <!-- 消息 -->
      <el-col :md="18" :xs="24">
        <div v-if="navIndex == 0">
          <div class="clearfix">
            <el-button v-if="isTeacher" type="primary" class="float-right btn-flat mr-3" @click="dialogVisible=true">发布通知
            </el-button>
          </div>
          <notice-list v-if="notices.length" :notices="notices" />
          <div v-else class="text-center pt-5">
            暂无班级通知
          </div>
        </div>
        <div v-if="navIndex == 1">
          <div class="text-center pt-5">
            暂无系统通知
          </div>
        </div>
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
    navIndex: 0,
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
.notice-nav {
  border-right: 1px solid #E1E1E1;
  .nav-item {
    padding: 20px;
    cursor: pointer;
  }
  .nav-icon {
    position: relative;
    float: left;
    width: 40px;
    height: 40px;
    margin-right: 10px;
  }
  .icon-inform {
    background-image: url("../../assets/icon_inform.png")
  }
  .icon-advices {
    background-image: url("../../assets/icon_advices.png")
  }
  .nav-title {
    font-size: 14px;
    color: #333;
  }
  .nav-last-msg {
    font-size: 14px;
    color: #666;
    overflow: hidden;
    text-overflow: ellipsis;
  }
  .active {
    border-left: 2px solid #0Bd;
  }
}
.dialog-footer {
  padding: 10px 0;
  text-align: right;
}
</style>
