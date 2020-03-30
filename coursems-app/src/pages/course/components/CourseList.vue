<template>
  <el-row :gutter="20">
    <el-col v-for="course in courses.filter(item =>
        item.courseName.toLowerCase().indexOf(filter.toLowerCase()) !== -1 || filter === ''
      )" :key="course.courseId" :xs="24" :sm="8" :md="6" class="mb-3">
      <a class="course-card img-hover pointer" @click="bindClazz(course)" >
        <el-row>
          <el-col :xs="9" class="course-pic">
            <img :src="course.coursePic"  alt="..." />
          </el-col>
          <el-col :xs="15">
            <div class="text-dark course-info">
              <p class="course-name">{{course.courseName}}</p>
              <p v-if="getUserId === course.teacherId" class="course-num">班课号：{{course.courseNum}}</p>
              <p v-else class="course-num">指导老师：{{course.teacherName}}</p>
              <p class="clazz-term pr-2">
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
</template>

<script>
import { mapGetters } from 'vuex'

export default {
  name: 'CourseCard',
  props: ['courses', 'filter'],
  data: () => ({
  }),
  computed: {
    ...mapGetters(['getUserId'])
  },
  methods: {
    bindClazz (course) { // 跳转绑定班级
      this.$router.push(`/clazz/${course.courseId}`)
    }
  }
}
</script>

<style lang="scss" scoped>
$border-radius: 0.25rem;
.course-card {
    display: -ms-flexbox;
    display: flex;
    flex-direction: column;
    background-color: #fff;
    background-clip: border-box;
    border-radius: $border-radius;
    box-shadow: 0 0.125rem 0.25rem rgba(0, 0, 0, 0.075);
    .course-pic {
      overflow: hidden;
      max-height: 230px;
      img {
        width: 100%;
        border-top-left-radius: $border-radius;
        border-top-right-radius: $border-radius;
      }
    }
    .course-info {
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
      .course-num {
        font-size: 0.8rem;
      }
      .clazz-term {
        font-size: 0.8rem;
      }
    }
  }
</style>
