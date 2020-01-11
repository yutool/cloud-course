<template>
  <div class="container">
    <el-row class="pb-5">
      <el-col :md="7" :sm="10" class="text-center">
          <img src="@/assets/computing-on-a-chart.png" width="200">
        </el-col>
      <el-col :md="17" :sm="14" class="pt-5">
        <h2 class="pl-3">课程管理系统</h2>
        <h6 class="pl-3">Course Management System</h6>
        <p class="text-muted">
          &emsp;课程管理系统就是为课程建立网站，老师可以在其上发布课程大纲、教学计划,以及每堂课的教学内容，布置作业,批改作业，公布学生成绩。课程管理系统往往还为课程提供一些通讯工具，如讨论组、聊天室,支持这门课程师生之间的交流。
        </p>
      </el-col>
    </el-row>
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
              content="不懂就问他">
              <p>不懂的就问他</p>
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
    <div class="text-center pb-5">
      <h5 class="pb-3">Get started with here and build an era of cloud courses</h5>
      <router-link v-if="!this.$store.state.userInfo" to="/register" class="btn btn-primary">Sign Up Free</router-link>
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
