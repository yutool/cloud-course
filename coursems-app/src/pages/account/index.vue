<template>
  <div class="container p-3">
    <div class="row">
      <!-- 选项卡 -->
      <div class="col-md-2">
         <el-tabs :tab-position="tabPosition" :stretch="true" v-model="activeName" @tab-click="handleClick">
          <el-tab-pane label="我的信息" name="/account/information"></el-tab-pane>
          <el-tab-pane label="学习历程" name="/account/experience"></el-tab-pane>
          <el-tab-pane label="用户安全" name="/account/security"></el-tab-pane>
        </el-tabs>
      </div>
      <!-- 内容，子路由 -->
      <div class="col-md-10">
          <router-view />
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Account',
  data: () => ({
    activeName: '/account/information',
    tabPosition: document.documentElement.clientWidth > 720 ? 'left' : 'top'
  }),
  methods: {
    handleClick (tabs) { // 路由跳转
      if (this.$route.path === this.activeName) return
      this.$router.push({path: this.activeName})
    }
  },
  mounted  () {
    var _this = this
    window.onresize = () => {
      _this.tabPosition = document.documentElement.clientWidth > 720 ? 'left' : 'top'
    }
  },
  watch: {
    tabPosition (val) {
      this.tabPosition = val
    }
  }
}
</script>

<style lang="scss" scoped>

</style>
