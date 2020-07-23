<template>
  <div class="container">
    <el-alert
      title="系统升级通知"
      type="warning"
      description="项目刚进行重构，尚未测试，可能存在些许BUG"
      show-icon>
    </el-alert>
    <!-- 项目介绍 -->
    <el-row class="pb-5">
      <el-col :md="7" :sm="10" class="text-center">
          <img src="@/assets/computing-on-a-chart.png" width="200">
        </el-col>
      <el-col :md="17" :sm="14" class="pt-5">
        <h2 class="pl-3">云班课系统</h2>
        <h6 class="pl-3">Cloud Course System</h6>
        <p class="text-muted">
          &emsp;云班课系统就是为课程建立网站，教师可以在其上发布课程大纲、教学计划、教学资源、以及每堂课的教学内容、布置作业、批改作业、公布学生成绩等。云班课系统往往还为课程提供一些通讯工具，如讨论组、聊天室，支持这门课程师生之间的交流。
        </p>
      </el-col>
    </el-row>
    <!-- 资料卡片 -->
    <el-row :gutter="30" class="pb-5">
      <el-col :md="8" :sm="12" class="pb-2">
        <el-card class="box-card text-center pt-3">
          <img src="@/assets/1.jpeg" class="wpx-75 rounded-circle" >
          <div class="pt-2">
            <el-popover placement="bottom"  width="200" trigger="click"
              content="任务分配：系统设计实现">
              <el-button slot="reference" class="p-0 border-0">
                <h3>Han Qin</h3>
              </el-button>
            </el-popover>
          </div>
          <p>
            <i class="el-icon-ice-tea pl-1 pr-1 pointer" ></i>
            <i class="el-icon-chat-line-round pl-1 pr-1 pointer"></i>
          </p>
        </el-card>
      </el-col>
      <el-col :md="8" :sm="12" class="pb-2">
        <el-card class="box-card text-center pt-3">
          <img src="@/assets/1.jpeg" class="wpx-75 rounded-circle" >
          <div class="pt-2">
            <el-popover placement="bottom"  width="200" trigger="click"
              content="任务分配：系统BUG测试">
              <el-button slot="reference" class="p-0 border-0">
                <h3>Xia Yu Long</h3>
              </el-button>
            </el-popover>
          </div>
          <p>
            <i class="el-icon-ice-tea pl-1 pr-1 pointer" ></i>
            <i class="el-icon-chat-line-round pl-1 pr-1 pointer"></i>
          </p>
        </el-card>
      </el-col>
      <el-col :md="8" :sm="12" class="pb-2">
        <el-card class="box-card text-center pt-3">
          <img src="@/assets/1.jpeg" class="wpx-75 rounded-circle" >
          <div class="pt-2">
            <el-popover placement="bottom"  width="200" trigger="click"
              content="任务分配：系统需求分析">
              <el-button slot="reference" class="p-0 border-0">
                <h3>Zhang Kai Li</h3>
              </el-button>
            </el-popover>
          </div>
          <p>
            <i class="el-icon-ice-tea pl-1 pr-1 pointer" ></i>
            <i class="el-icon-chat-line-round pl-1 pr-1 pointer"></i>
          </p>
        </el-card>
      </el-col>
      <el-col :md="8" :sm="12" class="pb-2">
        <el-card class="box-card text-center pt-3">
          <img src="@/assets/1.jpeg" class="wpx-75 rounded-circle" >
          <div class="pt-2">
            <el-popover placement="bottom"  width="200" trigger="click"
              content="任务分配：数据库设计">
              <el-button slot="reference" class="p-0 border-0">
                <h3>Guo Wang Yu</h3>
              </el-button>
            </el-popover>
          </div>
          <p>
            <i class="el-icon-ice-tea pl-1 pr-1 pointer" ></i>
            <i class="el-icon-chat-line-round pl-1 pr-1 pointer"></i>
          </p>
        </el-card>
      </el-col>
      <el-col :md="8" :sm="12" class="pb-2">
        <el-card class="box-card text-center pt-3">
          <img src="@/assets/1.jpeg" class="wpx-75 rounded-circle" >
          <div class="pt-2">
            <el-popover placement="bottom" width="200" trigger="click"
              content="其他小伙伴">
              <p>其他小伙伴</p>
              <a href="http://geekjoker.com/">他的作品</a>
              <el-button slot="reference" class="p-0 border-0">
                <h3>Zheng Quan</h3>
              </el-button>
            </el-popover>
          </div>
          <p>
            <i class="el-icon-ice-tea pl-1 pr-1 pointer" ></i>
            <i class="el-icon-chat-line-round pl-1 pr-1 pointer"></i>
          </p>
        </el-card>
      </el-col>
    </el-row>
    <!-- 注册 -->
    <div class="text-center pb-5">
      <h5 class="pb-3">Get started with here and build an era of cloud course</h5>
      <router-link v-if="!this.$store.getters.getUserId" to="/register" class="btn btn-primary">Sign Up Free</router-link>
    </div>
  </div>
</template>

<script>
export default {
  name: 'About',
  data: () => ({

  })
}
</script>

<style lang="scss" scoped>

</style>
