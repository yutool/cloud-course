<template>
  <div class="container" v-loading.fullscreen.lock="fullscreenLoading">
    <!-- 班级标题 -->
    <el-row class="w-70 mb-3">
      <el-col :md="4" :sm="5" :xs="12">
        <img src="@/assets/1.jpeg" class="rounded-circle wpx-90 float-left" alt="...">
      </el-col>
      <el-col :md="8" :sm="7" :xs="12" class="text-truncate pt-3">
        <span>{{clazzDetail.courseName}}</span>
        <div class="hpx-10"></div>
        <span>{{clazzDetail.clazzName}}</span>
      </el-col>
      <el-col :md="12" :sm="12" :xs="24" class="pt-4">
        <el-autocomplete v-model.trim="courseList" :fetch-suggestions="querySearchAsync" placeholder="选择其他班课" @select="handleSelect" class="w-100" clearable></el-autocomplete>
      </el-col>
    </el-row>
    <!-- 子导航栏 -->
    <el-tabs v-model="activeName" @tab-click="handleClick">
      <el-tab-pane label="班级成员" name="/clazz/member"></el-tab-pane>
      <el-tab-pane label="资源列表" name="/clazz/resource"></el-tab-pane>
      <el-tab-pane label="班级消息" name="/clazz/notice"></el-tab-pane>
      <el-tab-pane label="结课评分" name="/clazz/appraise"></el-tab-pane>
      <el-tab-pane label="班级详情" name="/clazz/detail"></el-tab-pane>
    </el-tabs>
    <!-- 子路由 -->
    <router-view />
  </div>
</template>

<script>
import { mapState, mapGetters, mapActions } from 'vuex'
import { getClazzDetail } from '@/api/clazz'
export default {
  name: 'Clazz',
  data: () => ({
    selected: {},
    activeName: '/clazz/member',
    serachName: '',
    restaurants: [],
    courseList: '',
    timeout: null
  }),
  computed: {
    ...mapState(['clazzDetail', 'userCourse', 'fullscreenLoading']),
    ...mapGetters(['getClazzId', 'getClazzMember'])
  },
  methods: {
    ...mapActions(['setClazzDetail', 'setSelectedMember']),
    handleClick (tabs) { // 路由跳转
      if (this.$route.path === this.activeName) return
      this.$router.push({path: this.activeName})
    },
    loadAll () {
      for (let course of this.userCourse.joinCourses) {
        let list = {}
        list['value'] = course.courseName + ': ' + course.teaName
        list['clazzId'] = course.clazzId
        this.restaurants.push(list)
      }
      for (let course of this.userCourse.createCourses) {
        let list = {}
        list['value'] = course.courseName + ': ' + course.teaName
        list['clazzId'] = course.clazzId
        this.restaurants.push(list)
      }
      this.$log.info('fasdfa', this.restaurants)
    },
    courseListFilter (queryString) {
      return (courseList) => {
        return (courseList.value.toLowerCase().indexOf(queryString.toLowerCase()) !== -1)
      }
    },
    handleSelect (item) {
      this.courseList = ''
      getClazzDetail(item.clazzId).then(res => {
        this.setClazzDetail(res.data)
        this.setSelectedMember(this.getClazzMember[0])
        this.$log.info('getClazzDetail', res)
      })
    },
    querySearchAsync (queryString, cb) {
      var restaurants = this.restaurants
      var results = queryString ? restaurants.filter(this.courseListFilter(queryString)) : restaurants
      cb(results)
      // clearTimeout(this.timeout)
      // this.timeout = setTimeout(() => {
      //   cb(results)
      // }, 600 * Math.random())
    }
  },
  mounted () {
    this.loadAll()
    getClazzDetail(this.getClazzId).then(res => {
      this.setClazzDetail(res.data)
      this.setSelectedMember(this.getClazzMember[0])
      this.$router.push('/clazz/member')
      this.$log.info('getClazzDetail', res)
    })
  }
}
</script>

<style lang="scss" scoped>
</style>
