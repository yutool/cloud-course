<template>
  <div class="shadow p-3 rounded mb-5">
    <div class="clearfix p-2">
      <el-switch v-model="unread" active-text="只看未读"></el-switch>
      <el-button v-if="isTeacher" type="primary" class="float-right btn-flat" @click="dialogVisible=true">发布资源</el-button>
    </div>
    <!-- 暂无资源 -->
    <div v-if="!resources.length" class="text-center p-5">
      暂未上传资源
    </div>
    <resource-list v-else :resources="resources" />
    <!-- 新建资源弹窗 -->
    <auto-dialog :title="'上传资源'" :visible="dialogVisible" :width="600" @close="closeDialog" >
      <el-form :model="resourceForm" :rules="rules" ref="resourceForm" label-width="80px">
        <el-form-item label="资源名称" prop="resName">
          <el-input v-model.trim="resourceForm.resName"></el-input>
        </el-form-item>
        <el-form-item label="经验值" prop="experience">
          <el-select v-model="resourceForm.experience" placeholder="请选择活动区域" class="w-100">
            <el-option v-for="o in 10" :key="o" :label="o-1+' 经验值'" :value="o-1"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="资源形式" prop="uploadType">
          <el-radio-group v-model="uploadType" class="pt-2">
            <el-radio label="网页链接" disabled=""></el-radio>
            <el-radio label="本地上传"></el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item v-if="uploadType === '网页链接'" label="网页链接" prop="resPath">
          <el-input v-model.trim="resourceForm.resPath" disabled=""></el-input>
        </el-form-item>
        <el-form-item label="本地上传" prop="">
          <el-upload class="upload-demo" multiple action="https://jsonplaceholder.typicode.com/posts/"
              :before-upload="beforeUpload"
              :file-list="fileList"
              :limit="1">
              <el-button size="small" type="primary">点击上传</el-button>
              <div slot="tip" class="el-upload__tip">一次只能上传一个文件</div>
            </el-upload>
        </el-form-item>
        <div class="form-group-btn">
          <el-button type="primary" @click="submitForm('resourceForm')">发布资源</el-button>
          <el-button @click="resetForm('resourceForm')">重置</el-button>
        </div>
      </el-form>
    </auto-dialog>
  </div>
</template>

<script>
import { mapState, mapGetters } from 'vuex'
import ResourceList from './components/ResourceList'

export default {
  name: 'Resource',
  components: {
    ResourceList
  },
  data: () => ({
    unread: false,
    dialogVisible: false,
    fileList: [],
    file: null,
    uploadType: '本地上传',
    resourceForm: {
      resName: '',
      experience: 0
    },
    rules: {
      resName: [
        { required: true, message: '请输入资源名称', trigger: 'change' },
        { max: 15, message: '名称不多于 15 个字符', trigger: 'change' }
      ],
      experience: [
        { required: true, message: '请选择经验值', trigger: 'change' }
      ],
      type: [
        { required: true, message: '请选择活动资源', trigger: 'change' }
      ]
    }
  }),
  computed: {
    ...mapState({
      resources: state => state.clazz.resources
    }),
    ...mapGetters(['isTeacher', 'getCourseId'])
  },
  methods: {
    submitForm (formName) {
      this.$refs[formName].validate((valid) => {
        if (valid && this.file != null) {
          this.resourceForm['courseId'] = this.getCourseId
          const data = new FormData()
          data.append('info', JSON.stringify(this.resourceForm))
          data.append('file', this.file)
          this.$store.dispatch('clazz/uploadResource', data).then(res => {
            this.resetForm(formName)
            this.file = null
            this.fileList = []
            this.dialogVisible = false
          })
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    beforeUpload (file) {
      this.file = file
      return false
    },
    resetForm (formName) {
      this.$refs[formName].resetFields()
    },
    closeDialog () {
      this.dialogVisible = false
    }
  }
}
</script>

<style lang="scss" scoped>
  .res-name {
    font-size: 15px;
    padding: 5px 0;
  }
  .res-describe {
    font-size: 14px;
    padding: 5px 0;
  }
</style>
