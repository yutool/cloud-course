<template>
  <div class="container">
    <!-- 导航栏 -->
    <el-breadcrumb separator-class="el-icon-arrow-right" class="mb-3">
      <el-breadcrumb-item :to="{ path: '/course' }">课程</el-breadcrumb-item>
      <el-breadcrumb-item>加入班级</el-breadcrumb-item>
    </el-breadcrumb>
    <!-- 内容 -->
    <div class="row">
      <div class="col-md-2"></div>
      <div class="col-md-8 shadow rounded pt-5 pb-5">
        <div class="row">
          <div class="col-md-2"></div>
          <div class="col-md-8">
            <!-- 输入框 -->
            <el-input placeholder="请输入班课号" prefix-icon="el-icon-search" v-model.trim="courseNum" @keyup.enter.native="submit">
            </el-input>
            <!-- 班级存在 -->
            <el-row v-if="JSON.stringify(course) !== '{}'" :gutter="20" class="pt-5">
              <el-col :md="9" :sm="9" :xs="24" class="text-center">
                <img :src="course.coursePic" class="wpx-150 mt-2" alt="">
                <div class="text-center pt-3 pb-3">
                  <el-button type="primary" class="btn-flat" @click="confirm">加入班级</el-button>
                </div>
              </el-col>
              <el-col :md="15" :sm="15" :xs="24" class="text-center">
                <div class="hpx-10"></div>
                <p>教师名：{{course.teacherName}}</p>
                <p>班课号：{{course.courseNum}}</p>
                <p>学期：{{course.term}}</p>
                <div class="hpx-10"></div>
                <p>课程名：{{course.courseName}}</p>
                <p>班级名：{{course.clazzName}}</p>
              </el-col>
            </el-row>
            <!-- 班级不存在 -->
            <div v-else class="hint text-center pt-5">
              {{hint}}
            </div>
          </div>
          <div class="col-md-2"></div>
        </div>
      </div>
      <div class="col-md-2"></div>
    </div>
  </div>
</template>

<script>
import { searchCourse } from '@/api/course'
import { addMember } from '@/api/clazz'
import { mapState } from 'vuex'

export default {
  name: 'JoinCourse',
  data: () => ({
    courseNum: '',
    course: {},
    hint: '输入班课号搜索'
  }),
  computed: {
    ...mapState({
      userInfo: state => state.user.userInfo
    })
  },
  methods: {
    submit () {
      searchCourse(this.courseNum).then(res => {
        console.log(res)
        if (res.data !== null) {
          this.course = res.data
        } else {
          this.course = {}
          this.hint = '课程不存在'
        }
        this.$log.info('searchCourse', res)
      })
    },
    confirm () {
      addMember({courseId: this.course.courseId, userId: this.userInfo.userId}).then(res => {
        this.$router.push('/course')
      })
    }
  },
  mounted () {
    if (this.userInfo.stuNum === null || this.userInfo.stuNum === '') {
      this.$message({type: 'info', message: '请先完善个人资料'})
      this.$router.push('/account')
    }
  }
}
</script>

<style lang="scss" scoped>
</style>
