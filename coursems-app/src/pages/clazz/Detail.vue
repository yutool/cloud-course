<template>
  <div class="shadow p-3 rounded mb-5">
    <div class="card border-0">
      <!-- 基本信息 -->
      <div class="card-header bg-white">
        <span>基本信息</span>
        <Popconfirm v-if="!isTeacher" title="确定退出班级吗？" width="200" @confirm="quitClazz" class="float-right">
          <el-button class="btn-flat" type="primary">退出班级</el-button>
        </Popconfirm>
        <Popconfirm v-if="isTeacher" title="确定解散班级吗？" width="200" @confirm="dissolveClazz" class="float-right">
          <el-button class="btn-flat" type="primary">解散班级</el-button>
        </Popconfirm>
      </div>
      <div class="card-body">
        <div class="row">
          <div class="col-md-1"></div>
          <div class="col-md-3 text-center mb-2">
            <div class="mb-2">
              <img :src="course.coursePic" class="rounded wpx-150" alt="">
            </div>
            <el-upload
              class="upload-demo mt-2"
              action="https://jsonplaceholder.typicode.com/posts/"
              :show-file-list="false"
              :before-upload="beforePhotoUpload">
              <el-button size="small" type="primary">点击上传</el-button>
              <cropper-dialog :url="imgUrl" :visible="cdVisible" @confirm="confirmUpload" @close="cancelUpload" />
            </el-upload>
          </div>
          <div class="col-md-7 text-truncate text-center">
            <p>教&emsp;师：{{course.teacherName}}</p>
            <p>班课号：{{course.courseNum}}</p>
            <p>班级名：{{course.clazzName}}</p>
            <p>课程名：{{course.courseName}}</p>
            <p>学&emsp;期：{{course.term}}</p>
          </div>
          <div class="col-md-1"></div>
        </div>
      </div>
      <!-- 班级说明 -->
      <div class="card-header bg-white">
        <span>班级说明</span>
      </div>
      <div class="card-body">
        <p class="card-text">{{course.synopsis || "暂无说明"}}</p>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState, mapGetters } from 'vuex'
import { dissolveCourse } from '@/api/clazz'

export default {
  name: 'Detail',
  data: () => ({
    cdVisible: false,
    imgUrl: ''
  }),
  computed: {
    ...mapState({
      course: state => state.clazz.course
    }),
    ...mapGetters(['getUserId', 'isTeacher'])
  },
  methods: {
    quitClazz () {
      this.$store.dispatch('clazz/deleteMember', {userId: this.getUserId, clazzId: this.course.courseId})
    },
    dissolveClazz () {
      dissolveCourse(this.course.courseId).then(res => {
        this.$log.info('dissolveCourse', res)
        if (res.code === 0) {
          this.$router.push('/course')
          this.$message({type: 'success', message: '成功解散班级'})
          this.$log.info('dissolveCourse', res)
        }
      })
    },
    beforePhotoUpload (file) {
      this.imgUrl = window.URL.createObjectURL(file)
      this.cdVisible = true
      return false
    },
    confirmUpload (file) {
      console.log(file.type)
      let data = new FormData()
      data.append('file', file)
      this.$store.dispatch('clazz/uploadPhoto', {id: this.getCourseId, data: data}).then(res => {
        this.$notify({ title: '成功', message: '更换图片成功', type: 'success' })
      })
      this.cdVisible = false
    },
    cancelUpload () {
      this.cdVisible = false
    },
    handleConfirm () {
      console.log('confirm')
    }
  }
}
</script>

<style lang="scss" scoped>
  .card-text {
    p {
      margin: 10px 0;
    }
  }
</style>
