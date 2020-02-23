<template>
  <div class="container" v-loading.fullscreen.lock="loading">
    <!-- 功能栏 -->
    <el-row :gutter="20">
      <el-col :md="12" :sm="9" :xs="24" class="pb-2">
        <router-link to="/course/create" class="mr-3">
          <i class="el-icon-edit pt-2" >创建班级</i>
        </router-link>
        <router-link to="/course/join">
          <i class="el-icon-plus pt-2" >加入班课</i>
        </router-link>
      </el-col>
      <el-col :md="12" :sm="15" :xs="24">
        <el-input placeholder="输入课程名帅选" prefix-icon="el-icon-search"
        v-model.trim="serachName" clearable></el-input>
      </el-col>
    </el-row>
    <!-- 班课 -->
    <el-tabs v-model="activeName" class="mb-5">
      <!-- 加入的课程 -->
      <el-tab-pane :label="'加入的课程 ( '+ joinCourses.length +' )'" name="join">
        <div v-if="!joinCourses.length" class="p-5 text-center"> 暂无加入的班级 </div>
        <course-list v-else :courses="joinCourses" :filter="serachName" />
      </el-tab-pane>
      <!-- 创建的课程 -->
      <el-tab-pane :label="'创建的课程 ( '+ createCourses.length +' )'" name="create">
        <div v-if="!createCourses.length" class="p-5 text-center"> 暂无创建的班级 </div>
        <course-list v-else :courses="createCourses" :filter="serachName" />
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
import { mapState, mapGetters } from 'vuex'
import CourseList from './components/CourseList'

export default {
  name: 'Course',
  data () {
    return {
      serachName: '',
      activeName: 'join'
    }
  },
  components: {
    CourseList
  },
  computed: {
    ...mapState({
      loading: state => state.app.loading,
      joinCourses: state => state.course.joinCourses,
      createCourses: state => state.course.createCourses
    }),
    ...mapGetters(['getUserId'])
  },
  mounted () {
    this.$store.dispatch('course/getCourses', this.getUserId)
  }
}
</script>

<style lang="scss" scoped>
  a:hover {
    text-decoration: none;
  }
</style>
