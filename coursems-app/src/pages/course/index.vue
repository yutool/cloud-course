<template>
  <div class="container" v-loading.fullscreen.lock="fullscreenLoading">
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
    <el-tabs v-if="userCourse" v-model="activeName" class="mb-5">
      <!-- 加入的课程 -->
      <el-tab-pane :label="'加入的课程 ( '+ userCourse.joinCourses.length +' )'" name="join">
        <div v-if="!userCourse.joinCourses.length" class="p-5 text-center">
          暂无加入的班级
        </div>
        <el-row v-if="userCourse.joinCourses.length" :gutter="20">
          <!-- data1 -->
          <el-col v-for="course in userCourse.joinCourses.filter(item => {
              return item.courseName.toLowerCase().indexOf(serachName.toLowerCase()) !== -1 || serachName === ''
            })" :key="course.clazzId" :xs="24" :sm="8" :md="6" class="mb-3">
            <a class="card border-0 shadow-sm pointer" @click="bindClazz(course)">
              <el-row>
                <el-col :xs="9">
                  <img src="@/assets/1.jpeg" class="card-img-top" alt="..." />
                </el-col>
                <el-col :xs="15">
                  <div class="text-dark clazz-info">
                    <p class="course-name">{{course.courseName}}</p>
                    <p class="clazz-num">指导老师：{{course.teaName}}</p>
                    <p class="clazz-time pr-2">
                      <span>{{course.term}}</span>
                      <span class="float-right">
                        进入
                        <i class="el-icon-arrow-right"></i>
                      </span>
                    </p>
                  </div>
                </el-col>
              </el-row>
            </a>
          </el-col>
        </el-row>
      </el-tab-pane>
      <!-- 创建的课程 -->
      <el-tab-pane :label="'创建的课程 ( '+ userCourse.createCourses.length +' )'" name="create">
        <div v-if="!userCourse.createCourses.length" class="p-5 text-center">
          暂无创建的班级
        </div>
        <el-row v-if="userCourse.createCourses.length" :gutter="20">
          <!-- data2 -->
          <el-col v-for="course in userCourse.createCourses.filter(item => {
              return item.courseName.toLowerCase().indexOf(serachName.toLowerCase()) !== -1 || serachName === ''
            })" :key="course.clazzId" :xs="24" :sm="8" :md="6" class="mb-3">
            <a class="card border-0 shadow-sm  pointer" @click="bindClazz(course)" >
              <el-row>
                <el-col :xs="9">
                  <img src="@/assets/1.jpeg" class="card-img-top" alt="..." />
                </el-col>
                <el-col :xs="15">
                  <div class="text-dark clazz-info">
                    <p class="course-name">{{course.courseName}}</p>
                    <p class="clazz-num">班课号：{{course.clazzNum}}</p>
                    <p class="clazz-time pr-2">
                      <span>{{course.term}}</span>
                      <span class="float-right">
                        进入
                        <i class="el-icon-arrow-right"></i>
                      </span>
                    </p>
                  </div>
                </el-col>
              </el-row>
            </a>
          </el-col>
        </el-row>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
import { getAllCourse } from '@/api/course'
import { mapState, mapActions, mapGetters } from 'vuex'
export default {
  name: 'Course',
  data () {
    return {
      serachName: '',
      activeName: 'join'
    }
  },
  computed: {
    ...mapState(['userCourse', 'fullscreenLoading']),
    ...mapGetters(['getUserId'])
  },
  methods: {
    ...mapActions(['setClazzDetail', 'setUserCourse']),
    bindClazz (course) { // 跳转绑定班级
      this.setClazzDetail(course)
      this.$router.push('/clazz')
    }
  },
  mounted () {
    getAllCourse(this.getUserId).then(res => {
      if (res.code === 0) {
        this.setUserCourse(res.data)
        this.$log.info('getJoinCourses', res)
      }
    })
  }
}
</script>

<style lang="scss" scoped>
  a:hover {
    text-decoration: none;
  }
  .clazz-info {
    color: #333;
    padding-left: 0.6rem;
    > * {
      margin: 0;
      padding: 0.3rem 0;
      overflow: hidden;
      text-overflow: ellipsis;
      white-space: nowrap;
    }
    .course-name {
      font-size: 1.1rem;
    }
    .clazz-num {
      font-size: 0.8rem;
    }
    .clazz-time {
      font-size: 0.8rem;
    }
  }
</style>
