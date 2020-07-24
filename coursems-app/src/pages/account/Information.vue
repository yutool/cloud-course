<template>
  <div>
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>我的信息</span>
        <el-button type="primary" class="float-right btn-flat" @click="submitForm('userInfo')">保存</el-button>
      </div>
      <div class="card-body">
        <div class="row">
          <!-- 头像 -->
          <div class="col-md-3 text-center">
            <img :src="userInfo.avatar" class="wpx-150" alt="">
            <el-upload
              class="upload-demo mt-2"
              action="https://jsonplaceholder.typicode.com/posts/"
              :show-file-list="false"
              :before-upload="beforeAvatarUpload">
              <el-button size="small" type="primary">点击上传</el-button>
            </el-upload>
            <cropper-dialog :visible="cdVisible" :url="avatarUrl" @confirm="confirmUpload" @close="cancelUpload" />
            <div class="hpx-10"></div>
          </div>
          <div class="col-md-1"></div>
          <!-- 信息 -->
          <div class="col-md-7">
            <el-form :model="userInfo" :rules="rules" ref="userInfo" label-width="60px">
              <el-form-item label="姓 名" prop="userName">
                <el-input v-model="userInfo.userName"></el-input>
              </el-form-item>
              <el-form-item label="昵 称" prop="nickname">
                <el-input v-model="userInfo.nickname"></el-input>
              </el-form-item>
              <el-form-item label="学 号" prop="stuNum">
                <el-input v-model="userInfo.stuNum"></el-input>
              </el-form-item>
              <el-form-item label="性 别" prop="gender">
                <el-radio-group v-model="userInfo.gender">
                  <el-radio label="男"></el-radio>
                  <el-radio label="女"></el-radio>
                </el-radio-group>
              </el-form-item>
              <el-form-item label="出生日期" prop="birthday" label-width="80px" value-format="yyyy-MM-dd">
                <el-date-picker type="date" placeholder="选择日期" v-model="userInfo.birthday" style="width: 100%;"></el-date-picker>
              </el-form-item>
              <el-form-item label="个性签名" prop="signature" label-width="80px">
                <el-input type="textarea" v-model="userInfo.signature"></el-input>
              </el-form-item>
            </el-form>
          </div>
          <div class="col-md-1"></div>
        </div>
      </div>
    </el-card>
  </div>
</template>

<script>
import { mapState, mapActions } from 'vuex'

export default {
  name: 'Information',
  data () {
    return {
      cdVisible: false,
      avatarUrl: '',
      rules: {
        userName: [
          { required: true, message: '请输入姓名', trigger: 'change' },
          { min: 2, max: 6, message: '长度在 2 到 6 个字符', trigger: 'change' }
        ],
        nickname: [
          { required: true, message: '请输入昵称', trigger: 'change' },
          { min: 2, max: 10, message: '长度在 2 到 10 个字符', trigger: 'change' }
        ],
        stuNum: [
          { required: true, message: '请学号', trigger: 'change' },
          { min: 6, max: 10, message: '长度在 6 到 10 个字符', trigger: 'change' }
        ],
        gender: [
          { required: true, message: '请选择性别', trigger: 'change' }
        ],
        signature: [
          { max: 100, message: '长度不超过100字符', trigger: 'change' }
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
    ...mapActions(['setUserInfo']),
    submitForm (formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$log.info('info/form', this.userInfo)
          this.$store.dispatch('user/updateInfo', this.userInfo).then(res => {
            this.$notify({ title: '成功', message: '更新信息成功', type: 'success' })
          })
        } else {
          this.$log.info('info/form', 'error submit!!')
          return false
        }
      })
    },
    beforeAvatarUpload (file) {
      const isLt2M = file.size / 1024 / 1024 < 2
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!')
      }
      this.cdVisible = true
      this.avatarUrl = window.URL.createObjectURL(file)
      return false // 取消默认上传
    },
    confirmUpload (file) {
      let data = new FormData()
      data.append('file', file)
      this.$store.dispatch('user/uploadAvatar', {id: this.userInfo.userId, data: data}).then(res => {
        this.$notify({ title: '成功', message: '更新头像成功', type: 'success' })
      })
      this.cdVisible = false
    },
    cancelUpload () {
      this.cdVisible = false
    }
  }
}
</script>

<style lang="scss" scoped>

</style>
