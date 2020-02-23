<template>
  <el-form ref="scoreForm" :model="member" :rules="rules" label-width="50px" class="p-3">
    <h5 class="text-center pb-3">给 {{member.stuName}} 评分</h5>
    <el-form-item label="分数" prop="score">
      <el-input oninput="value=value.replace(/[^\d]/g,'')" maxlength="3" v-model.trim="member.score" ></el-input>
    </el-form-item>
    <el-form-item label="评语" prop="remark">
      <el-input type="textarea" v-model="member.remark" :rows="6"></el-input>
    </el-form-item>
    <el-form-item class="float-right">
      <el-button type="primary" @click="submitForm('scoreForm')">立即评分</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
export default {
  name: 'GradeForm',
  props: ['member'],
  data () {
    return {
      rules: {
        score: [
          { required: true, message: '请输入分数', trigger: 'change' }
        ],
        remark: [
          { required: true, message: '请输入评价', trigger: 'blur' },
          { max: 100, message: '长度在小于100字符', trigger: 'change' }
        ]
      }
    }
  },
  components: {

  }
}
</script>

<style lang="scss" scoped>

</style>
