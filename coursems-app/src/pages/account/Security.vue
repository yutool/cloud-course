<template>
  <div>
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>账号安全</span>
      </div>
      <!-- 绑定邮箱 -->
      <div class="card-body border-bottom">
        <el-row class="pb-4">
          <el-col :md="5" :sm="2">&emsp;</el-col>
          <el-col :md="3" :sm="6" :xs="24" class="text-center">
            <img src="@/assets/images/1.jpeg" class="wpx-90 rounded-circle" alt="...">
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
      <!-- 绑定手机 -->
      <div class="card-body border-bottom">
        <el-row class="pb-4">
          <el-col :md="5" :sm="2">&emsp;</el-col>
          <el-col :md="3" :sm="6" :xs="24" class="text-center">
            <img src="@/assets/images/1.jpeg" class="wpx-90 rounded-circle" alt="...">
          </el-col>
          <el-col :md="13" :sm="16" :xs="24" class="text-center pt-2">
            <a href="javascript:void(0);" class="pointer" @click="enPhone = !enPhone">关联手机：{{userInfo.phoneNum || '未绑定'}}</a>
            <br />
            <small>·关联手机后可使用手机号加密码登录，也可使用短信验证登录</small>
            <br />
            <small>·密码丢失时可以通过手机轻松找回</small>
          </el-col>
        </el-row>
        <div v-if="enPhone" class="row mt-3">
          <div class="col-md-3 col-sm-2"></div>
          <div class="col-md-6 col-sm-8">
             <el-form :model="phoneForm" :rules="rules" ref="phoneForm">
              <el-form-item prop="phone">
                <el-input v-model.trim="phoneForm.phone" placeholder="请输入新手机号码"></el-input>
              </el-form-item>
              <el-form-item prop="securityCode" >
                <el-input v-model.trim="phoneForm.securityCode" placeholder="点击获取验证码">
                  <el-button slot="append" @click="getSecurityCode(phoneForm)">获取验证码</el-button>
                </el-input>
              </el-form-item>
              <el-button type="primary" @click="submitPhone('phoneForm')" class="w-100">提交</el-button>
            </el-form>
          </div>
          <div class="col-md-3 col-sm-2"></div>
        </div>
      </div>
      <!-- 修改密码 -->
      <div class="card-body">
        <el-row class="pb-4">
          <el-col :md="5" :sm="2">&emsp;</el-col>
          <el-col :md="3" :sm="6" :xs="24" class="text-center">
            <img src="@/assets/images/1.jpeg" class="wpx-90 rounded-circle" alt="...">
          </el-col>
          <el-col :md="13" :sm="16" :xs="24" class="text-center pt-3">
            <a href="javascript:void(0);" class="pointer" @click="enRestPwd = !enRestPwd">修改密码</a>
            <br />
            <small>·为了你的账号安全，请不要向他人透露账号密码</small>
          </el-col>
        </el-row>
        <div v-if="enRestPwd" class="row mt-3">
          <div class="col-md-3 col-sm-2"></div>
          <div class="col-md-6 col-sm-8">
            <el-form :model="pwdForm" status-icon :rules="rules" ref="pwdForm">
              <el-form-item prop="pass">
                <el-input type="password" v-model.trim="pwdForm.pass" autocomplete="off" placeholder="请输入新密码"></el-input>
              </el-form-item>
              <el-form-item prop="checkPass">
                <el-input type="password" v-model.trim="pwdForm.checkPass" autocomplete="off" placeholder="确认密码"></el-input>
              </el-form-item>
              <el-button type="primary" @click="submitPwd('pwdForm')" class="w-100">提交</el-button>
            </el-form>
          </div>
          <div class="col-md-3 col-sm-2"></div>
        </div>
      </div>
    </el-card>
  </div>
</template>

<script>
import { mapState, mapActions } from 'vuex'
import { randomString } from '@/assets/js/utils'
import { bindEmail, bindPhone, resetPassWord } from '@/api/account'
export default {
  name: 'Security',
  data () {
    var validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'))
      } else {
        if (this.pwdForm.checkPass !== '') {
          this.$refs.pwdForm.validateField('checkPass')
        }
        callback()
      }
    }
    var validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'))
      } else if (value !== this.pwdForm.pass) {
        callback(new Error('两次输入密码不一致!'))
      } else {
        callback()
      }
    }
    return {
      enEmail: false,
      enPhone: false,
      enRestPwd: false,
      emailForm: {
        email: '',
        securityCode: ''
      },
      phoneForm: {
        phone: '',
        securityCode: ''
      },
      pwdForm: {
        pass: '',
        checkPass: ''
      },
      rules: {
        email: [
          { required: true, message: '请输入邮箱地址', trigger: 'change' },
          { type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change'] }
        ],
        phone: [
          { required: true, message: '请输入手机号码', trigger: 'change' },
          { len: 11, message: '请输入有效手机号码', trigger: 'blur' }
        ],
        securityCode: [
          { required: true, message: '请输入验证码', trigger: 'change' }
        ],
        pass: [
          { validator: validatePass, trigger: 'change' },
          { min: 6, max: 15, message: '长度6到15位', trigger: 'blur' }
        ],
        checkPass: [
          { validator: validatePass2, trigger: 'change' }
        ]
      }
    }
  },
  computed: {
    ...mapState(['userInfo'])
  },
  methods: {
    ...mapActions(['setUserPwd', 'setUserEmail', 'setUserPhoneNum']),
    submitEmail (formName) { // 绑定邮箱
      this.$refs[formName].validate((valid) => {
        if (valid) {
          var userInfo = {
            email: this.emailForm.email,
            userId: this.userInfo.userId
          }
          this.$log.error('userInfo', userInfo)
          if (userInfo.email === this.userInfo.email) {
            this.$message({type: 'error', message: '邮箱不能和上次的一样哦'})
            return
          }
          bindEmail(userInfo).then(res => {
            if (res.status === 1) {
              this.setUserEmail(userInfo.email)
              this.$message({type: 'success', message: '绑定邮箱成功'})
              this.$refs[formName].resetFields()
              this.enEmail = false
            } else {
              this.$message({type: 'error', message: res.message})
            }
          })
        } else {
          this.$log.error('security', 'errorsubmit!')
          return false
        }
      })
    },
    submitPhone (formName) { // 绑定手机
      this.$refs[formName].validate((valid) => {
        if (valid) {
          var userInfo = {
            phoneNum: this.phoneForm.phone,
            userId: this.userInfo.userId
          }
          this.$log.info('userInfo', userInfo)
          if (userInfo.phoneNum === this.userInfo.phoneNum) {
            this.$message({type: 'error', message: '手机号不能和上次的一样哦'})
            return
          }
          bindPhone(userInfo).then(res => {
            if (res.status === 1) {
              this.setUserPhoneNum(userInfo.phoneNum)
              this.$message({type: 'success', message: '绑定手机成功'})
              this.$refs[formName].resetFields()
              this.enPhone = false
            } else {
              this.$message({type: 'error', message: res.message})
            }
          })
        } else {
          this.$log.error('security', 'errorsubmit!')
          return false
        }
      })
    },
    submitPwd (formName) { // 重置密码
      this.$refs[formName].validate((valid) => {
        if (valid) {
          var userInfo = {
            userPwd: this.pwdForm.pass,
            email: this.userInfo.email
          }
          this.$log.info('pwdForm', userInfo)
          if (userInfo.userPwd === this.userInfo.userPwd) {
            this.$message({type: 'error', message: '密码不能和上次的一样哦'})
            return
          }
          resetPassWord(userInfo).then(res => {
            if (res.status === 1) {
              this.setUserPwd(userInfo.userPwd)
              this.$message({type: 'success', message: '修改密码成功'})
              this.$refs[formName].resetFields()
              this.enRestPwd = false
            }
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
  .card-body {
    padding: 2rem 0;
  }
</style>
