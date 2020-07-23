<template>
  <div class="jumbotron container">
    <h1 class="display-4">敬请期待...</h1>
    <p class="lead">更多有趣的功能正在开发中哦！！！</p>
    <hr class="my-4">
    <p>如果你有什么好建议，请点击反馈。</p>
    <!-- Button trigger modal -->
    <button type="button" class="btn btn-primary" @click="dialogFormVisible=true">
      点击反馈
    </button>
    <!-- 弹窗 -->
    <el-dialog title="反馈" :visible.sync="dialogFormVisible" :width="dialogWidth">
      <el-form :model="feedbackForm" ref="feedbackForm" :rules="rules">
        <el-form-item prop="suggest">
          <el-input type="textarea" v-model="feedbackForm.suggest" :rows="5" placeholder="这里写下你的建议吧">
          </el-input>
        </el-form-item>
        <div class="dialog-footer form-group-btn">
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="submitForm('feedbackForm')">确 定</el-button>
        </div>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: 'FeedBack',
  data () {
    return {
      dialogFormVisible: false,
      dialogWidth: document.documentElement.clientWidth > 600 ? '600px' : '100%',
      feedbackForm: {
        suggest: ''
      },
      rules: {
        suggest: [
          { required: true, message: '还没填写建议', trigger: 'change' }
        ]
      }
    }
  },
  methods: {
    submitForm (formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$message({type: 'success', message: '感谢你的反馈'})
          this.dialogFormVisible = false
        } else {
          return false
        }
      })
    }
  },
  mounted () {
    var _this = this
    window.onresize = () => {
      _this.dialogWidth = document.documentElement.clientWidth > 600 ? '600px' : '100%'
    }
  },
  watch: {
    dialogWidth (val) {
      this.dialogWidth = val
    }
  }
}
</script>

<style lang="scss" scoped>

</style>
