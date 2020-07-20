<template>
  <div class="card-body border-bottom">
    <el-row class="pb-4">
      <el-col :md="5" :sm="2">&emsp;</el-col>
      <el-col :md="3" :sm="6" :xs="24" class="text-center">
        <img src="@/assets/icon_mail.png" class="wpx-90 rounded-circle" alt="...">
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
          <el-form-item prop="verifyCode" >
            <el-input v-model.trim="emailForm.verifyCode" placeholder="点击获取验证码">
              <el-button id="verifyBtn" slot="append" @click="getVerifyCode(emailForm)">{{verifyHint}}</el-button>
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
import { updateEmail } from '@/api/verify'

export default {
  name: 'EmailCard',
  data () {
    return {
      enEmail: false,
      verifyHint: '获取验证码',
      emailForm: {
        email: '',
        verifyCode: ''
      },
      rules: {
        email: [
          { required: true, message: '请输入邮箱地址', trigger: 'change' },
          { type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change'] }
        ],
        verifyCode: [
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
          this.$store.dispatch('user/bindEmail', {userId: this.userInfo.userId, email: this.emailForm.email, verifyCode: this.emailForm.verifyCode}).then(res => {
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
    getVerifyCode (form) {
      const toAddr = this.emailForm.email
      var reg = /^\w+((.\w+)|(-\w+))@[A-Za-z0-9]+((.|-)[A-Za-z0-9]+).[A-Za-z0-9]+$/
      if (toAddr === '' || !reg.test(toAddr)) {
        this.$message({type: 'info', message: '请输入有效邮箱'})
        return
      }
      $('#verifyBtn').attr('disabled', true)
      updateEmail(toAddr).then(res => {
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
    }
  }
}
</script>

<style lang="scss" scoped>

</style>
