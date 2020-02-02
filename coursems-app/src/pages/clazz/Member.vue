<template>
  <div class="member shadow rounded mb-5">
    <div class="member-header p-3">
      <span>班级成员 ({{this.getClazzMember.length}})</span>
      <Popconfirm v-if="getIsTeacher && this.getClazzMember.length!==0" :title="'确定删除 '+selectedMember.stuName+' 吗？'" width="200" @confirm="removeMember()" class="float-right">
        <el-button type="primary" class="float-right btn-flat">移除班级</el-button>
      </Popconfirm>
    </div>
    <hr class="m-0"/>
    <div class="memver-content p-3">
      <!-- 班级还没同学 -->
      <div v-if="this.getClazzMember.length==0" class="pt-5 pb-5 text-center">
        <h5>这个班级还没有同学</h5>
        <div class="hpx-10"></div>
        <h6>快把<router-link to="/clazz/detail">班课号</router-link>告诉同学吧...</h6>
      </div>
      <el-row :gutter="20">
        <!-- 成员列表 -->
        <el-col :md="7" :sm="10">
          <ul class="list-group">
            <li v-for="member in this.getClazzMember" :key="member.userId" class="list-group-item list-group-item-action pointer" @click="selectMember(member)">
              <img src="@/assets/1.jpeg" class="wpx-30 mr-2 rounded-circle" alt="">
              <span>{{member.stuName}}</span> <span>{{member.stuNum}}</span>
              <span class="float-right">{{member.experience}}</span>
            </li>
          </ul>
          <!-- 分页 -->
          <el-pagination v-if="this.getClazzMember.length>10" small layout="prev, pager, next" :total="50" class="mt-3 mb-2 text-center"></el-pagination>
        </el-col>
        <!-- 详细情况 -->
        <el-col v-if="this.getClazzMember.length" :md="17" :sm="14"  class="hidden-xs-only border p-0">
          <!-- 个人信息 -->
          <div class="border-bottom clearfix p-1">
            <img src="@/assets/1.jpeg" class="wpx-75 rounded-circle m-2 float-left" alt="头像">
            <div class="float-left pt-1 pl-2">
                <div class="hpx-10"></div>
                <span>{{selectedMember.stuName}}</span>
                <span>{{selectedMember.stuNum}}</span>
                <span>{{selectedMember.experience}}</span>
                <div class="hpx-10"></div>
                <small class="align-middle" >
                  个性签名: {{selectedMember.signature || '这个人很懒，什么都没写。。。'}}
                </small>
            </div>
          </div>
          <!-- 情况 -->
          <div class="border-bottom pb-5">
            <el-row :gutter="20">
              <el-col :span="8" class="text-center pt-4">
                <p>获取经验详情</p>
                <el-col :md="12">
                  <el-progress type="circle" :percentage="0" :width="90"></el-progress>
                </el-col>
                <el-col :md="12" class="pt-2 text-truncate">
                  <small>当前经验：{{selectedMember.experience}}</small>
                  <div class="hpx-10"></div>
                  <small>总经验值：{{clazzDetail.experience}}</small>
                </el-col>
              </el-col>
              <el-col :span="8" class="text-center pt-4">
                <p>查看资源详情</p>
                <el-col :md="12">
                  <el-progress type="circle" :percentage="0" :width="90"></el-progress>
                </el-col>
                <el-col :md="12" class="pt-2 text-truncate">
                  <small>已查看：{{selectedMember.viewRes}}</small>
                  <div class="hpx-10"></div>
                  <small>当前发布：{{clazzDetail.resourceNum}}</small>
                </el-col>
              </el-col>
              <el-col :span="8" class="text-center pt-4">
                <p>课堂出勤详情</p>
                <el-col :md="12">
                  <el-progress type="circle" :percentage="0" :width="90"></el-progress>
                </el-col>
                <el-col :md="12" class="pt-2 text-truncate">
                  <small>已签到：{{selectedMember.arrive}}</small>
                  <div class="hpx-10"></div>
                  <small>签到总数：{{clazzDetail.arriveNum}}</small>
                </el-col>
              </el-col>
            </el-row>
          </div>
          <!-- 不实现 -->
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
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
import { mapGetters, mapState, mapActions } from 'vuex'
import { deleteMember } from '@/api/clazz'
export default {
  name: 'Member',
  data: () => ({
  }),
  computed: {
    ...mapState(['clazzDetail', 'selectedMember']),
    ...mapGetters(['getClazzId', 'getIsTeacher', 'getClazzMember'])
  },
  methods: {
    ...mapActions(['removeClazzMember', 'setSelectedMember']),
    selectMember (member) { // 切换成员
      this.setSelectedMember(member)
    },
    removeMember () { // 删除成员
      deleteMember(this.getClazzId, this.selectedMember.userId).then(res => {
        if (res.code === 0) { // 删除成员成功
          for (let i = 0; i < this.getClazzMember.length; i++) {
            if (this.getClazzMember[i] === this.selectedMember) {
              this.removeClazzMember(i)
              break
            }
          }
          this.selectedMember = this.getClazzMember[0]
          this.$log.info('deleteMember', res)
        }
      })
    }
  },
  mounted () {
    this.$log.info('fdsfsdfadsfadsfas', this.selectedMember)
  }
}
</script>

<style lang="scss" scoped>
</style>
