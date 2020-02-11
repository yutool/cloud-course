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
      <el-tab-pane label="班级成员" name="member"></el-tab-pane>
      <el-tab-pane label="资源列表" name="resource"></el-tab-pane>
      <el-tab-pane label="班级消息" name="notice"></el-tab-pane>
      <el-tab-pane label="结课评分" name="appraise"></el-tab-pane>
      <el-tab-pane label="班级详情" name="detail"></el-tab-pane>
    </el-tabs>
    <!-- 子路由 -->
    <router-view />
  </div>
</template>

<script>
import { mapState, mapGetters, mapActions } from 'vuex'
export default {
  name: 'Clazz',
  data: () => ({
    selected: {},
    activeName: 'member',
    serachName: '',
    restaurants: [],
    courseList: '',
    timeout: null
  }),
  computed: {
    ...mapState(['clazzDetail', 'userCourse', 'fullscreenLoading']),
    ...mapGetters(['getClazzMember'])
  },
  methods: {
    ...mapActions(['setClazzDetail', 'setSelectedMember']),
    handleClick (tabs) { // 路由跳转
      if (this.$route.path === this.activeName) return
      this.$router.push(this.activeName)
    },
    loadAll () {
      if (this.restaurants.length > 0 || this.userCourse === null) {
        return this.restaurants
      }
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
      return this.restaurants
    },
    courseListFilter (queryString) {
      return (courseList) => {
        return (courseList.value.toLowerCase().indexOf(queryString.toLowerCase()) !== -1)
      }
    },
    handleSelect (item) {
      this.courseList = ''
      this.$router.push(`/clazz/${item.clazzId}`)
      this.$store.dispatch('getClazz', item.clazzId)
    },
    querySearchAsync (queryString, cb) {
      var restaurants = this.loadAll()
      var results = queryString ? restaurants.filter(this.courseListFilter(queryString)) : restaurants
      cb(results)
      // clearTimeout(this.timeout)
      // this.timeout = setTimeout(() => {
      //   cb(results)
      // }, 600 * Math.random())
    }
  },
  mounted () {
    this.$store.dispatch('getAllCourse')
    this.$store.dispatch('getClazz', this.$route.params.id)
  }
}
</script>

<style lang="scss" scoped>
</style>
