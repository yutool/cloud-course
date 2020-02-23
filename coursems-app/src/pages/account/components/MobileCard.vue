<template>
  <div class="card-body border-bottom">
    <el-row class="pb-4">
      <el-col :md="5" :sm="2">&emsp;</el-col>
      <el-col :md="3" :sm="6" :xs="24" class="text-center">
        <img src="@/assets/1.jpeg" class="wpx-90 rounded-circle" alt="...">
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
</template>

<script>
import { mapState } from 'vuex'
import { randomString } from '@/utils/utils'

export default {
  name: 'MobileCard',
  data () {
    return {
      enPhone: false,
      phoneForm: {
        phone: '',
        securityCode: ''
      },
      rules: {
        phone: [
          { required: true, message: '请输入手机号码', trigger: 'change' },
          { len: 11, message: '请输入有效手机号码', trigger: 'blur' }
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
    submitPhone (formName) { // 绑定手机
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$store.dispatch('user/bindPhone', { id: this.userInfo.userId, phone: this.phoneForm.phone }).then(res => {
            this.$notify({ title: '成功', message: '绑定手机成功', type: 'success' })
            this.$refs[formName].resetFields()
            this.enPhone = false
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
