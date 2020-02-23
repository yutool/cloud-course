<template>
  <div class="member shadow rounded mb-5">
    <div class="member-header p-3">
      <span>班级成员 ({{members.length}})</span>
      <Popconfirm v-if="isTeacher && members.length!==0" :title="'确定删除 '+selectedMember.stuName+' 吗？'" width="200" @confirm="deleteMember()" class="float-right">
        <el-button type="primary" class="float-right btn-flat">移除班级</el-button>
      </Popconfirm>
    </div>
    <hr class="m-0"/>
    <div class="memver-content p-3">
      <!-- 班级还没同学 -->
      <div v-if="members.length===0" class="pt-5 pb-5 text-center">
        <h5>这个班级还没有同学</h5>
        <div class="hpx-10"></div>
        <h6>快把<router-link to="/clazz/detail">班课号</router-link>告诉同学吧...</h6>
      </div>
      <el-row v-else :gutter="20">
        <!-- 成员列表 -->
        <el-col :md="7" :sm="10">
          <member-list :members="members" />
        </el-col>
        <!-- 详细情况 -->
        <el-col v-if="members.length" :md="17" :sm="14"  class="hidden-xs-only border p-0">
          <!-- 成员信息 -->
          <member-info :member="selectedMember" />
          <!-- 学习情况 -->
          <member-status :course="course" :member="selectedMember" />
          <!-- 不实现 -->
          <member-exp />
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
import { mapGetters, mapState, mapActions } from 'vuex'
import MemberList from './components/MemberList'
import MemberInfo from './components/MemberInfo'
import MemberStatus from './components/MemberStatus'
import MemberExp from './components/MemberExp'

export default {
  name: 'Member',
  data: () => ({
  }),
  components: {
    MemberList, MemberInfo, MemberStatus, MemberExp
  },
  computed: {
    ...mapState({
      course: state => state.clazz.course,
      members: state => state.clazz.members,
      selectedMember: state => state.clazz.selectedMember
    }),
    ...mapGetters(['isTeacher'])
  },
  methods: {
    ...mapActions(['clazz/setSelectedMember']),
    selectMember (member) { // 切换成员
      this.$store.dispatch('clazz/setSelectedMember', member)
    },
    deleteMember () { // 删除成员
      this.$store.dispatch('clazz/deleteMember', this.selectedMember)
    }
  },
  mounted () {
    this.$log.info('fdsfsdfadsfadsfas', this.selectedMember)
  }
}
</script>

<style lang="scss" scoped>
</style>
