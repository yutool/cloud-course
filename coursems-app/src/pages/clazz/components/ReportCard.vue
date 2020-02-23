<template>
  <el-card>
    <h5 class="text-center">成绩单</h5>
    <el-row :gutter="20">
      <el-col :md="6"  class="text-center">
        <img :src="member.avatar" class="wpx-90 rounded-circle">
      </el-col>
      <el-col :md="18"  class="pt-2">
        <el-row class="text-center">
          <el-col :md="8"><span>姓名：{{member.stuName}}</span></el-col>
          <el-col :md="8"><span>学号：{{member.stuNum}}</span></el-col>
          <el-col :md="8"><span>评分：{{member.score}}</span></el-col>
        </el-row>
        <div class="pt-3"> &emsp;评语：{{member.remark}}</div>
      </el-col>
    </el-row>
  </el-card>
</template>

<script>
export default {
  name: 'ReportCard',
  props: ['member'],
  data: () => ({

  }),
  components: {

  }
}
</script>

<style lang="scss" scoped>

</style>
