<template>
  <div class="container">
    <!-- 标题 -->
    <el-divider class="title">注册</el-divider>
    <div class="row">
      <div class="col-md-3"></div>
      <div class="col-md-6">
        <el-form :model="registerForm" :rules="rules" ref="registerForm" label-width="70px" class="pr-3">
          <el-form-item label="用户名" prop="userName">
            <el-input v-model.trim="registerForm.userName"></el-input>
          </el-form-item>
          <el-form-item label="邮 箱" prop="email">
            <el-input v-model.trim="registerForm.email"></el-input>
          </el-form-item>
          <el-form-item label="密 码" prop="password">
            <el-input type="password" v-model.trim="registerForm.password"></el-input>
          </el-form-item>
          <el-form-item label="验证码" prop="securityCode" class="mb-2">
            <el-input v-model.trim="registerForm.securityCode">
              <el-button slot="append" @click="getSecurityCode">获取验证码</el-button>
            </el-input>
          </el-form-item>
          <el-form-item prop="checked" label-width="20px" class="m-0">
            <el-checkbox v-model="checked">我已阅读<a href="#">《用户使用协议》</a></el-checkbox>
          </el-form-item>
          <div class="ml-4">
            <el-button type="primary" class="btn-block" @click="submitForm('registerForm')">注册</el-button>
          </div>
          <router-link class="text-right-sm mt-2" to="/login">已有账号，直接登录&gt;</router-link>
        </el-form>
      </div>
      <div class="col-md-3"></div>
    </div>
  </div>
</template>

<script>
import { randomString } from '@/assets/js/utils'
import { register } from '@/api/account'
import auth from '@/api/auth'

export default {
  userName: 'Register',
  data () {
    return {
      checked: true,
      registerForm: {
        userName: '',
        password: '',
        email: '',
        securityCode: ''
      },
      rules: {
        userName: [
          { required: true, message: '请输入用户名', trigger: 'change' },
          { min: 2, max: 6, message: '长度在2到6位', trigger: ['blur', 'change'] }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'change' },
          { min: 6, max: 15, message: '长度在6到15位', trigger: ['blur', 'change'] }
        ],
        email: [
          { required: true, message: '请输入邮箱地址', trigger: 'change' },
          { type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change'] }
        ],
        securityCode: [
          { required: true, message: '请输入验证码', trigger: ['blur', 'change'] }
        ]
      }
    }
  },
  methods: {
    submitForm (formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) { // 提交表单
          this.$log.info('register/form', this.registerForm)
          var userInfo = {
            userName: this.registerForm.userName,
            nickname: this.registerForm.userName,
            userPwd: this.registerForm.password,
            email: this.registerForm.email,
            gender: '男'
          }
          register(userInfo).then(res => {
            if (res.status === 1) { // 注册成功
              auth.login(res.content, res.token)
              this.$log.info('register', res)
              this.$router.push('/account')
              this.$message({type: 'success', message: '注册成功，请完善资料'})
            } else {
              this.$message({type: 'error', message: res.message})
            }
          })
        } else {
          this.$log.info('register/form', 'error submit!!')
          return false
        }
      })
    },
    getSecurityCode () {
      this.registerForm.securityCode = randomString()
    }
  }
}
</script>

<style lang="scss" scoped>
  .title {
    margin: 3rem 0;
    font-size: 30px !important;
  }
</style>
