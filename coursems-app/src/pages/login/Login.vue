<template>
  <div class="container clearfix" v-loading.fullscreen.lock="loading">
    <!-- 标题 -->
    <el-divider class="title">登录</el-divider>
    <el-row :gutter="20">
      <!-- 二维码 -->
      <el-col :md="12" :sm="9" class="text-center">
        <img src="@/assets/QRcode.jpg" class="rounded mx-auto d-block wpx-160" alt="...">
        <h6 class="pt-3">扫描二维码登录</h6>
        <p>请使用<a href="#">客户端</a>扫码登录</p>
      </el-col>
      <!-- 表单 -->
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
            <router-link to="/forget_password" class="text-right-sm">忘记密码?</router-link>
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
import { mapState } from 'vuex'

export default {
  name: 'Register',
  data () {
    return {
      checked: false,
      loginForm: {
        account: 'admin@qq.com',
        password: '123456'
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
    submitForm (formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) { // 提交表单
          this.$log.info('login/from', this.loginForm)
          this.$store.dispatch('user/login', this.loginForm)
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
  computed: {
    ...mapState({
      loading: state => state.app.loading
    })
  }
}
</script>

<style lang="scss" scoped>
  .title {
    margin: 3rem 0;
    font-size: 30px !important;
  }
</style>
