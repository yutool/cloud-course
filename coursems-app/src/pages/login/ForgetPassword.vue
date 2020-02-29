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
        <el-form :model="forgetFrom" status-icon :rules="rules" ref="forgetFrom" label-width="80px" class="demo-forgetFrom pr-3">
          <el-form-item label="邮 箱" prop="email">
            <el-input v-model="forgetFrom.email"></el-input>
          </el-form-item>
          <el-form-item label="密 码" prop="password">
            <el-input type="password" v-model="forgetFrom.password" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="确认密码" prop="checkPass">
            <el-input type="password" v-model="forgetFrom.checkPass" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="验 证 码" prop="securityCode">
            <el-input type="securityCode" v-model="forgetFrom.securityCode">
              <el-button slot="append" @click="getSecurityCode">获取验证码</el-button>
            </el-input>
          </el-form-item>
          <div class="form-group-btn">
            <el-button type="primary" @click="submitForm('forgetFrom')">提交</el-button>
            <el-button @click="resetForm('forgetFrom')">重置</el-button>
          </div>
        </el-form>
      </div>
      <div class="col-md-3 col-ms-1"></div>
    </div>
  </div>
</template>

<script>
import { randomString } from '@/utils/utils'
import { forgetPassWord } from '@/api/account'
export default {
  name: 'ForgerPassword',
  data () {
    var validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'))
      } else {
        if (this.forgetFrom.checkPass !== '') {
          this.$refs.forgetFrom.validateField('checkPass')
        }
        callback()
      }
    }
    var validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'))
      } else if (value !== this.forgetFrom.password) {
        callback(new Error('两次输入密码不一致!'))
      } else {
        callback()
      }
    }
    return {
      forgetFrom: {
        password: '',
        checkPass: '',
        email: '',
        securityCode: ''
      },
      rules: {
        password: [
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
          this.$log.info('resetPwd/form', this.forgetFrom)
          forgetPassWord({email: this.forgetFrom.email, password: this.forgetFrom.password}).then(res => {
            this.$router.push('/login')
            this.$message({ showClose: true, type: 'success', message: '重置密码成功' })
          })
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
      this.forgetFrom.securityCode = randomString()
    }
  }
}
</script>

<style lang="scss" scoped>
  .el-form-item__label {
    width: 60px !important;
  }
</style>
