<template>
  <div class="card-body">
    <el-row class="pb-4">
      <el-col :md="5" :sm="2">&emsp;</el-col>
      <el-col :md="3" :sm="6" :xs="24" class="text-center">
        <img src="@/assets/1.jpeg" class="wpx-90 rounded-circle" alt="...">
      </el-col>
      <el-col :md="13" :sm="16" :xs="24" class="text-center pt-3">
        <a href="javascript:void(0);" class="pointer" @click="enPassword = !enPassword">修改密码</a>
        <br />
        <small>·为了你的账号安全，请不要向他人透露账号密码</small>
      </el-col>
    </el-row>
    <div v-if="enPassword" class="row mt-3">
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
</template>

<script>
import { resetPassWord } from '@/api/user'
import { mapGetters } from 'vuex'

export default {
  name: 'PasswordCard',
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
      enPassword: false,
      pwdForm: {
        pass: '',
        checkPass: ''
      },
      rules: {
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
    ...mapGetters(['getUserId'])
  },
  methods: {
    submitPwd (formName) { // 重置密码
      this.$refs[formName].validate((valid) => {
        if (valid) {
          resetPassWord({userId: this.getUserId, password: this.pwdForm.pass}).then(res => {
            if (res.code === 0) {
              this.$notify({ title: '成功', message: '修改密码成功', type: 'success' })
              this.$refs[formName].resetFields()
              this.enRestPwd = false
            }
          })
        } else {
          this.$log.error('security', 'errorsubmit!')
          return false
        }
      })
    }
  }
}
</script>

<style lang="scss" scoped>

</style>
