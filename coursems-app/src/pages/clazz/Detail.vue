<template>
  <div class="shadow p-3 rounded mb-5">
    <div class="card border-0">
      <!-- 基本信息 -->
      <div class="card-header bg-white">
        <span>基本信息</span>
        <Popconfirm v-if="!getIsTeacher" title="确定退出班级吗？" width="200" @confirm="quitClazz" class="float-right">
          <el-button class="btn-flat" type="primary">退出班级</el-button>
        </Popconfirm>
        <Popconfirm v-if="getIsTeacher" title="确定解散班级吗？" width="200" @confirm="dissolveClazz" class="float-right">
          <el-button class="btn-flat" type="primary">解散班级</el-button>
        </Popconfirm>
      </div>
      <div class="card-body">
        <div class="row">
          <div class="col-md-1"></div>
          <div class="col-md-3 text-center mb-2">
            <div class="mb-2">
              <img src="@/assets/1.jpeg" class="rounded wpx-150" alt="">
            </div>
            <el-button type="primary" class="mt-1 bt-1">
              <i class="el-icon-upload"></i>上传图片
            </el-button>
          </div>
          <div class="col-md-7 text-truncate text-center">
            <p>教&emsp;师：{{clazzDetail.teaName}}</p>
            <p>班课号：{{clazzDetail.clazzNum}}</p>
            <p>班级名：{{clazzDetail.clazzName}}</p>
            <p>课程名：{{clazzDetail.courseName}}</p>
            <p>学&emsp;期：{{clazzDetail.term}}</p>
          </div>
          <div class="col-md-1"></div>
        </div>
      </div>
      <!-- 班级说明 -->
      <div class="card-header bg-white">
        <span>班级说明</span>
      </div>
      <div class="card-body">
        <p class="card-text">{{clazzDetail.clazzExplain || "暂无说明"}}</p>
      </div>
    </div>
  </div>
</template>

<script>
import { mapGetters, mapState } from 'vuex'
import { quitCourse, dissolveCourse } from '@/api/course'
export default {
  name: 'Detail',
  data: () => ({
  }),
  computed: {
    ...mapState(['clazzDetail']),
    ...mapGetters(['getClazzId', 'getUserId', 'getIsTeacher'])
  },
  methods: {
    quitClazz () {
      quitCourse(this.getClazzId, this.getUserId).then(res => {
        if (res.code === 0) {
          this.$router.push('/course')
          this.$message({type: 'success', message: '成功退出班级'})
          this.$log.info('quitCourse', res)
        }
      })
    },
    dissolveClazz () {
      dissolveCourse(this.getClazzId).then(res => {
        this.$log.info('dissolveCourse', res)
        if (res.code === 0) {
          this.$router.push('/course')
          this.$message({type: 'success', message: '成功解散班级'})
          this.$log.info('dissolveCourse', res)
        }
      })
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
