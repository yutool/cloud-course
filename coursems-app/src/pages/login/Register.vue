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
          <el-form-item label="验证码" prop="verifyCode" class="mb-2">
            <el-input v-model.trim="registerForm.verifyCode">
              <el-button id="verifyBtn" slot="append" @click="getVerifyCode">{{verifyHint}}</el-button>
            </el-input>
          </el-form-item>
          <el-form-item prop="checked" label-width="20px" class="m-0">
            <el-checkbox v-model="checked">我已阅读<a href="#">《用户使用协议》</a></el-checkbox>
            <a href="javascript:;" @click="visitorsLogin" class="text-right-sm">不想注册，游客登录</a>
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
import { register } from '@/api/account'
import { registerEmail } from '@/api/verify'

export default {
  userName: 'Register',
  data () {
    return {
      checked: true,
      verifyHint: '获取验证码',
      registerForm: {
        userName: '',
        password: '',
        email: '',
        verifyCode: ''
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
        verifyCode: [
          { required: true, message: '请输入验证码', trigger: ['blur', 'change'] }
        ]
      }
    }
  },
  methods: {
    submitForm (formName) {
      this.$refs[formName].validate((valid) => {
        if (!this.checked) {
          this.$message({type: 'info', message: '点击阅读协议'})
          return
        }
        if (valid) { // 提交表单
          this.$log.info('register/form', this.registerForm)
          register(this.registerForm).then(res => {
            this.$message({type: 'success', message: '注册成功'})
            this.$log.info('register', res)
            this.$router.push('/login')
          })
        } else {
          this.$log.info('register/form', 'error submit!!')
          return false
        }
      })
    },
    getVerifyCode () {
      const toAddr = this.registerForm.email
      var reg = /^\w+((.\w+)|(-\w+))@[A-Za-z0-9]+((.|-)[A-Za-z0-9]+).[A-Za-z0-9]+$/
      if (toAddr === '' || !reg.test(toAddr)) {
        this.$message({type: 'info', message: '请输入有效邮箱'})
        return
      }
      $('#verifyBtn').attr('disabled', true)
      registerEmail(toAddr).then(res => {
        console.log(res.data)
        let second = 30
        const interval = setInterval(() => {
          second--
          this.verifyHint = second + 's重新获取'
          if (second <= 0) {
            window.clearInterval(interval)
            this.verifyHint = '获取验证码'
            $('#verifyBtn').attr('disabled', false)
          }
        }, 1000)
      }).catch(err => {
        console.log(err)
        $('#verifyBtn').attr('disabled', false)
      })
    },
    visitorsLogin () {
      this.$store.dispatch('user/visitorsLogin')
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
