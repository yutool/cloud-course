<template>
  <div class="container">
    <!-- 导航栏 -->
    <el-breadcrumb separator-class="el-icon-arrow-right" class="mb-3">
      <el-breadcrumb-item :to="{ path: '/course' }">课程</el-breadcrumb-item>
      <el-breadcrumb-item>创建班级</el-breadcrumb-item>
    </el-breadcrumb>
    <!-- 表单 -->
    <div class="row">
      <div class="col-md-2"></div>
      <div class="col-md-8 shadow rounded pt-5 pb-5">
        <el-form :model="clazzForm" :rules="rules" ref="clazzForm" label-width="100px" class="pr-3">
          <h5 class="text-center mb-5">创建班级</h5>
          <el-form-item label="班级名称" prop="clazzName">
            <el-input v-model.trim="clazzForm.clazzName"></el-input>
          </el-form-item>
          <el-form-item label="课程名称" prop="courseName">
            <el-input v-model.trim="clazzForm.courseName"></el-input>
          </el-form-item>
          <el-form-item label="学期区间" prop="term">
            <el-select v-model="clazzForm.term" class="w-100">
              <el-option label="2019-2020" value="2019-2020"></el-option>
              <el-option label="2019-2021" value="2019-2021"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="班级说明" prop="clazzExplain">
            <el-input type="textarea" v-model="clazzForm.clazzExplain"></el-input>
          </el-form-item>
          <div class="form-group-btn">
            <el-button type="primary" class="mb-2" @click="submitForm('clazzForm')">立即创建</el-button>
            <el-button @click="resetForm('clazzForm')">重置</el-button>
          </div>
        </el-form>
      </div>
      <div class="col-md-2"></div>
    </div>
  </div>
</template>

<script>
import { createCourse } from '@/api/course'
import { mapGetters, mapState } from 'vuex'
export default {
  name: 'CreateCourse',
  data () {
    return {
      clazzForm: {
        clazzName: '',
        courseName: '',
        term: '',
        clazzExplain: '',
        teaId: this.$store.state.userInfo.userId
      },
      rules: {
        clazzName: [
          { required: true, message: '请输入班级名称', trigger: 'change' },
          { min: 2, max: 15, message: '长度在2到15位', trigger: ['blur', 'change'] }
        ],
        courseName: [
          { required: true, message: '请输入课程名称', trigger: 'change' },
          { min: 2, max: 15, message: '长度在2到15位', trigger: ['blur', 'change'] }
        ],
        term: [
          { required: true, message: '请选择学期', trigger: 'change' }
        ]
      }
    }
  },
  computed: {
    ...mapState(['userId', 'userInfo']),
    ...mapGetters(['getUserId'])
  },
  methods: {
    submitForm (formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) { // 提交表单
          this.$log.info('create/form', this.clazzForm)
          createCourse(this.clazzForm).then(res => {
            this.$log.info('create/result', res)
            if (res.code === 0) { // 班级创建成功
              this.$router.push('/course')
              this.$message({type: 'success', message: res.message})
            }
          })
        } else {
          this.$log.info('create/form', 'error submit!!')
          return false
        }
      })
    },
    resetForm (formName) {
      this.$refs[formName].resetFields()
    }
  },
  mounted () {
    if (this.userInfo.stuNum === null || this.userInfo.stuNum === '') {
      this.$message({type: 'info', message: '请先完善个人资料'})
      this.$router.push('/account')
    }
  }
}
</script>

<style lang="scss" scoped>
</style>
