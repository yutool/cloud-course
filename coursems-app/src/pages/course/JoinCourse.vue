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
            <el-input placeholder="请输入班课号" prefix-icon="el-icon-search" v-model.trim="serachNum" @keyup.enter.native="submit">
            </el-input>
            <!-- 班级存在 -->
            <el-row v-if="clazzInfo" :gutter="20" class="pt-5">
              <el-col :md="9" :sm="9" :xs="24" class="text-center">
                <img src="@/assets/images/1.jpeg" class="wpx-150 mt-2" alt="">
                <div class="text-center pt-3 pb-3">
                  <el-button type="primary" class="btn-flat" @click="goJoinCourse">加入班级</el-button>
                </div>
              </el-col>
              <el-col :md="15" :sm="15" :xs="24" class="text-center">
                <div class="hpx-10"></div>
                <p>教师名：{{clazzInfo.teaName}}</p>
                <p>班课号：{{clazzInfo.clazzNum}}</p>
                <p>学期：{{clazzInfo.term}}</p>
                <div class="hpx-10"></div>
                <p>课程名：{{clazzInfo.courseName}}</p>
                <p>班级名：{{clazzInfo.clazzName}}</p>
              </el-col>
            </el-row>
            <!-- 班级不存在 -->
            <div v-if="!clazzInfo" class="hint text-center pt-5">
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
import { searchCourse, joinCourse } from '@/api/course'
import { mapState } from 'vuex'
export default {
  name: 'JoinCourse',
  data: () => ({
    serachNum: '',
    clazzInfo: null,
    hint: '输入班课号搜索'
  }),
  computed: {
    ...mapState(['userInfo'])
  },
  methods: {
    submit () {
      searchCourse(this.serachNum).then(res => {
        if (res.status === 1) {
          this.clazzInfo = res.content
        } else {
          this.clazzInfo = null
          this.hint = res.message
        }
        this.$log.info('searchCourse', res)
      })
    },
    goJoinCourse () {
      if (this.userInfo.userId === this.clazzInfo.teaId) {
        this.$message({type: 'error', message: '不用加入自己班级哦！'})
        return
      }
      var joinForm = {
        clazzId: this.clazzInfo.clazzId,
        userId: this.$store.state.userInfo.userId
      }
      joinCourse(joinForm).then(res => {
        if (res.status === 1) {
          this.$log.info('join/result', res)
          this.$router.push('/course')
          this.$message({type: 'success', message: res.message})
        } else {
          this.$message({type: 'error', message: res.message})
        }
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
