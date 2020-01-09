<template>
  <div>
    <!-- 学习经验 -->
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>学习历程</span>
      </div>
      <!-- 学习经验 -->
      <div class="card-body">
        <h6>学习经验</h6><hr class="mb-4"/>
        <div class="row mt-3">
          <div class="col-md-1"></div>
          <div class="col-md-2"><span>加入班课</span></div>
          <div class="col-md-8"><el-progress :percentage="50" status="exception"></el-progress></div>
          <div class="col-md-1"></div>
        </div>
        <div class="row mt-3">
          <div class="col-md-1"></div>
          <div class="col-md-2"><span>参与签到</span></div>
          <div class="col-md-8"><el-progress :percentage="50" status="exception"></el-progress></div>
          <div class="col-md-1"></div>
        </div>
        <div class="row mt-3">
          <div class="col-md-1"></div>
          <div class="col-md-2"><span>查看资源</span></div>
          <div class="col-md-8"><el-progress :percentage="50" status="exception"></el-progress></div>
          <div class="col-md-1"></div>
        </div>
        <div class="row mt-3">
          <div class="col-md-1"></div>
          <div class="col-md-2"><span>获取经验</span></div>
          <div class="col-md-8"><el-progress :percentage="50" status="exception"></el-progress></div>
          <div class="col-md-1"></div>
        </div>
      </div>
      <!-- 教学经验 -->
      <div class="card-body">
        <h6>教学经验</h6><hr class="mb-4"/>
        <el-row>
          <el-col :md="8" :sm="8" class="text-center">
            <el-progress type="circle" :percentage="25"></el-progress>
            <p class="pt-3">创建班课：1213</p>
          </el-col>
          <el-col :md="8" :sm="8" class="text-center">
            <el-progress type="circle" :percentage="25"></el-progress>
            <p class="pt-3">累计学生：1213</p>
          </el-col>
          <el-col :md="8" :sm="8" class="text-center">
            <el-progress type="circle" :percentage="25"></el-progress>
            <p class="pt-3">发布资源：1213</p>
          </el-col>
        </el-row>
      </div>
    </el-card>
  </div>
</template>

<script>
export default {
  name: 'Experience',
  data: () => ({

  }),
  components: {

  }
}
</script>

<style lang="scss" scoped>

</style>
