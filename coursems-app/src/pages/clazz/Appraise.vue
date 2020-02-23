<template>
  <div class="shadow p-3 rounded mb-5">
    <div class="card border-0">
      <!-- 给Ta评价 -->
      <div class="card-header bg-white">
        <span>给Ta评价</span>
        <el-switch v-if="isTeacher" v-model="course.appraise" active-text="开启评分" class="float-right" @click.native="toggleAppraise">
        </el-switch>
      </div>
      <!-- 关闭评分 -->
      <div v-if="!course.appraise" class="text-center p-5">
        结课后开启评分
      </div>
      <div v-if="course.appraise && members.length==0" class="pt-5 pb-5 text-center">
        <h5>这个班级还没有同学</h5>
        <div class="hpx-10"></div>
        <h6>快把<router-link to="/clazz/detail">班课号</router-link>告诉同学吧...</h6>
      </div>
      <div v-if="course.appraise" class="card-body">
        <el-row :gutter="20">
        <!-- 成员列表 -->
        <el-col :md="9" :sm="12" class="pb-3">
          <member-list :members="members" />
        </el-col>
        <!-- 教师评分-->
        <el-col v-if="members.length && isTeacher" :md="15" :sm="12"  class="border rounded-lg">
          <grade-form :member="selectedMember" />
        </el-col>
        <!-- 学分查看 -->
        <el-col v-if="!isTeacher" :md="15" :sm="12">
          <report-card :member="selectedMember" />
        </el-col>
      </el-row>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState, mapGetters } from 'vuex'
import MemberList from './components/MemberList'
import ReportCard from './components/ReportCard'
import GradeForm from './components/GradeForm'

export default {
  name: 'Appraise',
  components: {
    MemberList, ReportCard, GradeForm
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
    submitForm (formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          var scoreForm = {
            courseId: this.course.courseId,
            userId: this.selectedMember.userId,
            score: this.selectedMember.score,
            remark: this.selectedMember.remark
          }
          this.$store.dispatch('clazz/grade', scoreForm)
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    toggleAppraise () {
      this.$store.dispatch('clazz/toggleAppraise', this.course.courseId)
    }
  }
}
</script>

<style lang="scss" scoped>

</style>
