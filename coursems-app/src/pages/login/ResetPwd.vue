<template>
  <div class="container mt-1">
    <!-- 导航栏 -->
    <ol class="breadcrumb bg-white">
      <li class="breadcrumb-item"><router-link to="/login">登录</router-link></li>
      <li class="breadcrumb-item active" aria-current="page">忘记密码</li>
    </ol>
    <!-- 表单 -->
    <div class="row">
      <div class="col-md-3 col-ms-1"></div>
      <div class="col-md-6 col-ms-10">
        <el-form :model="resetPwdForm" status-icon :rules="rules" ref="resetPwdForm" label-width="80px" class="demo-resetPwdForm pr-3">
          <el-form-item label="邮 箱" prop="email">
            <el-input v-model="resetPwdForm.email"></el-input>
          </el-form-item>
          <el-form-item label="密 码" prop="userPwd">
            <el-input type="password" v-model="resetPwdForm.userPwd" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="确认密码" prop="checkPass">
            <el-input type="password" v-model="resetPwdForm.checkPass" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="验 证 码" prop="securityCode">
            <el-input type="securityCode" v-model="resetPwdForm.securityCode">
              <el-button slot="append" @click="getSecurityCode">获取验证码</el-button>
            </el-input>
          </el-form-item>
          <div class="form-group-btn">
            <el-button type="primary" @click="submitForm('resetPwdForm')">提交</el-button>
            <el-button @click="resetForm('resetPwdForm')">重置</el-button>
          </div>
        </el-form>
      </div>
      <div class="col-md-3 col-ms-1"></div>
    </div>
  </div>
</template>

<script>
import { randomString } from '@/utils/utils'
import { resetPassWord } from '@/api/account'
export default {
  name: 'ResetPwd',
  data () {
    var validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'))
      } else {
        if (this.resetPwdForm.checkPass !== '') {
          this.$refs.resetPwdForm.validateField('checkPass')
        }
        callback()
      }
    }
    var validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'))
      } else if (value !== this.resetPwdForm.userPwd) {
        callback(new Error('两次输入密码不一致!'))
      } else {
        callback()
      }
    }
    return {
      resetPwdForm: {
        userPwd: '',
        checkPass: '',
        email: '',
        securityCode: ''
      },
      rules: {
        userPwd: [
          { required: true, validator: validatePass, trigger: 'change' }
        ],
        checkPass: [
          { required: true, validator: validatePass2, trigger: 'change' }
        ],
        email: [
          { required: true, message: '请输入邮箱地址', trigger: 'change' },
          { type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change'] }
        ],
        securityCode: [
          { required: true, message: '请输入验证码', trigger: 'change' }
        ]
      }
    }
  },
  methods: {
    submitForm (formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) { // 提交表单
          var userForm = {
            email: this.resetPwdForm.email,
            userPwd: this.resetPwdForm.userPwd
          }
          resetPassWord(userForm).then(res => {
            if (res.status === 1) {
              this.$router.push('/login')
              this.$message({ showClose: true, type: 'success', message: '重置密码成功' })
            } else {
              this.$message({ showClose: true, type: 'error', message: res.message })
            }
          })
          this.$log.info('resetPwd/form', this.resetPwdForm)
        } else {
          this.$log.info('resetPwd/form', 'error submit!!')
          return false
        }
      })
    },
    resetForm (formName) {
      this.$refs[formName].resetFields()
    },
    getSecurityCode () {
      this.resetPwdForm.securityCode = randomString()
    }
  }
}
</script>

<style lang="scss" scoped>
  .el-form-item__label {
    width: 60px !important;
  }
</style>
