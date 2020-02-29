<template>
  <div class="border-bottom pb-5">
    <el-row :gutter="20">
      <el-col :span="8" class="text-center pt-4">
        <p>获取经验详情</p>
        <el-col :md="12">
          <el-progress type="circle" :percentage="0" :width="90"></el-progress>
        </el-col>
        <el-col :md="12" class="pt-2 text-truncate">
          <small>当前经验：{{member.experience}}</small>
          <div class="hpx-10"></div>
          <small>总经验值：{{course.experiences}}</small>
        </el-col>
      </el-col>
      <el-col :span="8" class="text-center pt-4">
        <p>查看资源详情</p>
        <el-col :md="12">
          <el-progress type="circle" :percentage="0" :width="90"></el-progress>
        </el-col>
        <el-col :md="12" class="pt-2 text-truncate">
          <small>已查看：{{member.viewRes}}</small>
          <div class="hpx-10"></div>
          <small>当前发布：{{course.resourses}}</small>
        </el-col>
      </el-col>
      <el-col :span="8" class="text-center pt-4">
        <p>课堂出勤详情</p>
        <el-col :md="12">
          <el-progress type="circle" :percentage="0" :width="90"></el-progress>
        </el-col>
        <el-col :md="12" class="pt-2 text-truncate">
          <small>已签到：{{member.arrive}}</small>
          <div class="hpx-10"></div>
          <small>签到总数：{{course.arrives}}</small>
        </el-col>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: 'LearnStatus',
  props: ['member', 'course'],
  data: () => ({

  })
}
</script>

<style lang="scss" scoped>

</style>
