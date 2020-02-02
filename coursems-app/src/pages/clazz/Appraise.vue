<template>
  <div class="shadow p-3 rounded mb-5">
    <div class="card border-0">
      <!-- 给Ta评价 -->
      <div class="card-header bg-white">
        <span>给Ta评价</span>
        <el-switch v-if="getIsTeacher" v-model="clazzDetail.enAppraise" active-text="开启评分" class="float-right" @click.native="enAppraise"></el-switch>
      </div>
      <!-- 关闭评分 -->
      <div v-if="!clazzDetail.enAppraise" class="text-center p-5">
        结课后开启评分
      </div>
      <div v-if="clazzDetail.enAppraise && this.getClazzMember.length==0" class="pt-5 pb-5 text-center">
        <h5>这个班级还没有同学</h5>
        <div class="hpx-10"></div>
        <h6>快把<router-link to="/clazz/detail">班课号</router-link>告诉同学吧...</h6>
      </div>
      <div v-if="clazzDetail.enAppraise" class="card-body">
        <el-row :gutter="20">
        <!-- 成员列表 -->
        <el-col :md="9" :sm="12" class="pb-3">
          <ul class="list-group">
            <li v-for="member in this.getClazzMember" :key="member.userId" class="list-group-item list-group-item-action pointer" @click="selectMember(member)">
              <img src="@/assets/1.jpeg" class="wpx-30 mr-2 rounded-circle" alt="">
              <span>{{member.stuName}}</span> <span>{{member.stuNum}}</span>
              <span class="float-right">{{member.score!==-1 ? member.score : '未评分'}}</span>
            </li>
          </ul>
          <!-- 分页 -->
          <el-pagination v-if="this.getClazzMember.length>10" small layout="prev, pager, next" :total="50" class="mt-3 mb-2 text-center"></el-pagination>
        </el-col>
        <!-- 教师评分-->
        <el-col v-if="this.getClazzMember.length && getIsTeacher" :md="15" :sm="12"  class="border rounded-lg">
          <el-form ref="scoreForm" :model="selectedMember" :rules="rules" label-width="50px" class="p-3">
            <h5 class="text-center pb-3">给 {{selectedMember.stuName}} 评分</h5>
            <el-form-item label="分数" prop="score">
              <el-input oninput="value=value.replace(/[^\d]/g,'')" maxlength="3" v-model.trim="selectedMember.score" ></el-input>
            </el-form-item>
            <el-form-item label="评语" prop="remark">
              <el-input type="textarea" v-model="selectedMember.remark" :rows="6"></el-input>
            </el-form-item>
            <el-form-item class="float-right">
              <el-button type="primary" @click="submitForm('scoreForm')">立即评分</el-button>
            </el-form-item>
          </el-form>
        </el-col>
        <!-- 学分查看 -->
        <el-col v-if="!getIsTeacher" :md="15" :sm="12">
          <el-card>
            <h5 class="text-center">成绩单</h5>
            <el-row :gutter="20">
              <el-col :md="6"  class="text-center">
                <img src="@/assets/1.jpeg" class="wpx-90 rounded-circle">
              </el-col>
              <el-col :md="18"  class="pt-2">
                <el-row class="text-center">
                  <el-col :md="8"><span>姓名：{{selectedMember.stuName}}</span></el-col>
                  <el-col :md="8"><span>学号：{{selectedMember.stuNum}}</span></el-col>
                  <el-col :md="8"><span>评分：{{selectedMember.score}}</span></el-col>
                </el-row>
                <div class="pt-3"> &emsp;评语：{{selectedMember.remark}}</div>
              </el-col>
            </el-row>
          </el-card>
        </el-col>
      </el-row>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState, mapGetters, mapActions } from 'vuex'
import { enableAppraise, gradeStudent } from '@/api/clazz'
export default {
  name: 'Appraise',
  data () {
    return {
      rules: {
        score: [
          { required: true, message: '请输入分数', trigger: 'change' }
        ],
        remark: [
          { required: true, message: '请输入评价', trigger: 'blur' },
          { max: 100, message: '长度在小于100字符', trigger: 'change' }
        ]
      }
    }
  },
  computed: {
    ...mapState(['clazzDetail', 'selectedMember']),
    ...mapGetters(['getClazzId', 'getIsTeacher', 'getClazzMember'])
  },
  methods: {
    ...mapActions(['setClazzAppraise', 'setSelectedMember']),
    selectMember (member) { // 切换成员
      this.setSelectedMember(member)
    },
    submitForm (formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          var scoreForm = {
            clazzId: this.getClazzId,
            userId: this.selectedMember.userId,
            score: this.selectedMember.score,
            remark: this.selectedMember.remark
          }
          gradeStudent(scoreForm).then(res => {
            if (res.code === 0) {
              this.$message({ message: '评分成功', type: 'success' })
            }
          })
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    enAppraise () {
      enableAppraise(this.clazzDetail).then(res => {
        if (res.code === 0) {
          this.setClazzAppraise(this.clazzDetail.enAppraise)
          this.$log.info('enableAppraise', res)
        }
      })
    }
  }
}
</script>

<style lang="scss" scoped>

</style>
