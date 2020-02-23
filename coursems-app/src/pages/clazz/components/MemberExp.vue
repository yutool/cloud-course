<template>
  <div>
    <el-row :gutter="20">
      <el-col :md="6" :sm="12" class="text-center pt-5 pb-4 border-right">
        <img src="https://static.cdn.oss.mosoteach.cn/mosoteach2/common/images/member-icon-questions.png" alt="">
        <div class="hpx-10"></div>
        <span>热心解答</span>
        <div class="hpx-10"></div>
        <span>0次</span>
      </el-col>
      <el-col :md="6" :sm="12" class="text-center pt-5 pb-4 border-right">
        <img src="https://static.cdn.oss.mosoteach.cn/mosoteach2/common/images/member-icon-praise.png" alt="">
        <div class="hpx-10"></div>
        <span>获取点赞</span>
        <div class="hpx-10"></div>
        <span>0次</span>
      </el-col>
      <el-col :md="6" :sm="12" class="text-center pt-5 pb-4 border-right">
        <img src="https://static.cdn.oss.mosoteach.cn/mosoteach2/common/images/member_list_icon_performance.png" alt="">
        <div class="hpx-10"></div>
        <span>课堂表现</span>
        <div class="hpx-10"></div>
        <span>0次/0分</span>
      </el-col>
        <el-col :md="6" :sm="12" class="text-center pt-5 pb-4">
          <img src="https://static.cdn.oss.mosoteach.cn/mosoteach2/common/images/member-icon-video.png" alt="">
          <div class="hpx-10"></div>
          <span>视频学习</span>
          <div class="hpx-10"></div>
          <span>0个/0分</span>
        </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: 'MemberExp',
  data: () => ({

  }),
  components: {

  }
}
</script>

<style lang="scss" scoped>

</style>
