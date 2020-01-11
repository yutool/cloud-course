<template>
  <div class="container clearfix" v-loading.fullscreen.lock="fullscreenLoading">
    <!-- 标题 -->
    <el-divider class="title">登录</el-divider>
    <el-row :gutter="20">
      <!-- 二维码 -->
      <el-col :md="12" :sm="9" class="text-center">
        <img src="@/assets/QRcode.jpg" class="rounded mx-auto d-block wpx-160" alt="...">
        <h6 class="pt-3">扫描二维码登录</h6>
        <p>请使用<a href="#">客户端</a>扫码登录</p>
      </el-col>
      <el-col :md="12" :sm="15">
        <el-form :model="loginForm" status-icon :rules="rules" ref="loginForm" label-width="100px" class="demo-loginForm pr-3">
          <el-form-item label="账 号" prop="account" label-width="60px">
            <el-input v-model="loginForm.account" placeholder="邮箱 / 手机号"></el-input>
          </el-form-item>
          <el-form-item label="密 码" prop="password" label-width="60px">
            <el-input type="password" v-model.trim="loginForm.password" maxlength="16" @keyup.enter.native="submitForm('loginForm')" placeholder="密 码"></el-input>
          </el-form-item>
          <div class="form-save-pwd clearfix" style="margin: 0 1.3rem 1rem;">
            <el-checkbox v-model="checked">记住密码</el-checkbox>
            <router-link to="/resetpwd" class="text-right-sm">忘记密码?</router-link>
          </div>
          <div class="form-group-btn">
            <el-button type="primary" @click="submitForm('loginForm')" style="widht:200px;">登录</el-button>
            <el-button @click="goRegister()">注册</el-button>
          </div>
        </el-form>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import { login } from '@/api/account'
import { mapActions } from 'vuex'
import auth from '@/utils/auth'

export default {
  name: 'Register',
  data () {
    return {
      checked: false,
      fullscreenLoading: false,
      loginForm: {
        account: '',
        password: ''
      },
      rules: {
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' }
        ],
        account: [
          { required: true, message: '请输入账号', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    ...mapActions(['setUserInfo']),
    submitForm (formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) { // 提交表单
          this.fullscreenLoading = true
          this.$log.info('login/from', this.loginForm)
          var userInfo = {
            email: this.loginForm.account,
            phoneNum: this.loginForm.account,
            userPwd: this.loginForm.password
          }
          login(userInfo).then(res => {
            if (res.status === 1) { // 登录成功
              auth.login(res.content, res.token)
              this.$log.info('login', res)
              this.$router.push(this.$route.query.redirect || '/course')
              this.$message({ showClose: true, type: 'success', message: '登录成功，又是元气满满的一天' })
            } else {
              this.$message({ showClose: true, type: 'error', message: res.message })
            }
            this.fullscreenLoading = false
          }).catch(res => {
            this.fullscreenLoading = false
            this.$message({type: 'error', message: '连接超时了，检查下网络'})
          })
        } else {
          this.$log.info('login/from', 'error submit!!')
          return false
        }
      })
    },
    goRegister () {
      this.$router.push({path: '/register'})
    }
  },
  mounted () {
    if (this.$store.state.userInfo && auth.getToken()) {
      this.$router.push('/course')
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
