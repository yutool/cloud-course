<template>
  <div class="card-body border-bottom">
    <el-row class="pb-4">
      <el-col :md="5" :sm="2">&emsp;</el-col>
      <el-col :md="3" :sm="6" :xs="24" class="text-center">
        <img src="@/assets/1.jpeg" class="wpx-90 rounded-circle" alt="...">
      </el-col>
      <el-col :md="13 " :sm="16" :xs="24" class="text-center pt-2">
        <a href="javascript:void(0);" class="pointer" @click="enEmail = !enEmail">关联邮箱：{{userInfo.email}}</a>
        <br />
        <small>·关联邮箱后，可使用邮箱加密码进行登录</small>
        <br />
        <small>·密码丢失时可以通过邮箱轻松找回</small>
      </el-col>
    </el-row>
    <div v-if="enEmail" class="row mt-3">
      <div class="col-md-3 col-sm-2"></div>
      <div class="col-md-6 col-sm-8">
        <el-form :model="emailForm" :rules="rules" ref="emailForm">
          <el-form-item prop="email" >
            <el-input v-model.trim="emailForm.email" placeholder="请输入新邮箱"></el-input>
          </el-form-item>
          <el-form-item prop="securityCode" >
            <el-input v-model.trim="emailForm.securityCode" placeholder="点击获取验证码">
              <el-button slot="append" @click="getSecurityCode(emailForm)">获取验证码</el-button>
            </el-input>
          </el-form-item>
          <el-button type="primary" @click="submitEmail('emailForm')" class="w-100">提交</el-button>
        </el-form>
      </div>
      <div class="col-md-3 col-sm-2"></div>
    </div>
  </div>
</template>

<script>
import { mapState } from 'vuex'
import { randomString } from '@/utils/utils'

export default {
  name: 'EmailCard',
  data () {
    return {
      enEmail: false,
      emailForm: {
        email: '',
        securityCode: ''
      },
      rules: {
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
  computed: {
    ...mapState({
      userInfo: state => state.user.userInfo
    })
  },
  methods: {
    submitEmail (formName) { // 绑定邮箱
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$store.dispatch('user/bindEmail', {id: this.userInfo.userId, email: this.emailForm.email}).then(res => {
            this.$notify({ title: '成功', message: '绑定邮箱成功', type: 'success' })
            this.$refs[formName].resetFields()
            this.enEmail = false
          })
        } else {
          this.$log.error('security', 'errorsubmit!')
          return false
        }
      })
    },
    getSecurityCode (form) {
      form.securityCode = randomString()
    }
  }
}
</script>

<style lang="scss" scoped>

</style>
