<template>
  <div class="shadow p-3 rounded mb-5">
    <div class="clearfix p-2">
      <el-switch v-model="unread" active-text="只看未读"></el-switch>
      <el-button v-if="getIsTeacher" type="primary" class="float-right btn-flat" @click="dialogFormVisible=true">发布资源</el-button>
    </div>
    <!-- 暂无资源 -->
    <div v-if="!this.getClazzResource || !this.getClazzResource.length" class="text-center p-5">
      暂未上传资源
    </div>
    <ul class="list-group pt-2">
      <li v-for="resource in this.getClazzResource" :key="resource.resId" class="list-group-item list-group-item-action">
        <img src="@/assets/1.jpeg" class="wpx-60 float-left" alt="">
        <div class="plpx-75">
          <div class="res-name">
            <span>{{resource.resName}}</span>
            <Popconfirm v-if="getIsTeacher" title="确定删除资源吗？" width="200" @confirm="removeResource(resource)" class="float-right">
              <i v-if="getIsTeacher" class="el-icon-delete float-right pr-3 pt-1 pointer"></i>
            </Popconfirm>
          </div>
          <div class="res-describe text-truncate">
            <span>{{resource.resSize}}</span> <span style="margin:0 5px;">|</span>
            <span>{{resource.uploadTime}}</span> <span style="margin:0 5px;">|</span>
            <span>{{resource.experience}}经验</span> <span style="margin:0 5px;">|</span>
            <span>{{resource.readed || 0}}人已查看</span>
          </div>
        </div>
      </li>
    </ul>
    <!-- 新建资源弹窗 -->
    <el-dialog title="上传资源" :visible.sync="dialogFormVisible" :width="dialogWidth">
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
            <el-radio label="网页链接"></el-radio>
            <el-radio label="本地上传"></el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="网页链接" prop="resPath">
          <el-input v-model.trim="resourceForm.resPath"></el-input>
        </el-form-item>
        <el-form-item label="本地上传" prop="">
          <el-upload class="upload-demo" multiple action="https://jsonplaceholder.typicode.com/posts/"
              :on-preview="handlePreview"
              :on-remove="handleRemove"
              :before-remove="beforeRemove"
              :limit="1"
              :on-exceed="handleExceed"
              :file-list="fileList">
              <el-button size="small" type="primary">点击上传</el-button>
              <div slot="tip" class="el-upload__tip">一次只能上传一个文件</div>
            </el-upload>
        </el-form-item>
        <div class="form-group-btn">
          <el-button type="primary" @click="submitForm('resourceForm')">发布资源</el-button>
          <el-button @click="resetForm('resourceForm')">重置</el-button>
        </div>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
import { mapGetters, mapActions } from 'vuex'
import { uploadResource, deleteResource } from '@/api/clazz'
export default {
  name: 'Resource',
  data: () => ({
    dialogWidth: document.documentElement.clientWidth > 600 ? '600px' : '100%',
    unread: false,
    dialogFormVisible: false,
    fileList: [],
    uploadType: '本地上传',
    resourceForm: {
      resName: '',
      experience: 0,
      resPath: ''
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
    ...mapGetters(['getIsTeacher', 'getClazzId', 'getClazzResource'])
  },
  methods: {
    ...mapActions(['addClazzResource', 'removeClazzResource']),
    submitForm (formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.resourceForm['uploadTime'] = new Date()
          this.resourceForm['resSize'] = '1.2MB'
          this.resourceForm['clazzId'] = this.getClazzId
          this.$log.info('resourceForm', this.resourceForm)
          uploadResource(this.resourceForm).then(res => {
            if (res.code === 0) {
              this.addClazzResource(res.data)
              this.dialogFormVisible = false
              this.resetForm(formName)
              this.$log.info('uploadResource', res)
            }
          })
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    removeResource (resource) {
      deleteResource(resource.resId).then(res => {
        if (res.code === 0) {
          for (let i = 0; i < this.getClazzResource.length; i++) {
            if (resource === this.getClazzResource[i]) {
              this.removeClazzResource(i)
              this.$log.info('deleteResource', res)
            }
          }
        }
      })
    },
    resetForm (formName) {
      this.$refs[formName].resetFields()
    },
    handleRemove (file, fileList) {
      console.log(file, fileList)
    },
    handlePreview (file) {
      console.log(file)
    },
    handleExceed (files, fileList) {
      this.$message.warning(`当前限制选择 1 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`)
    },
    beforeRemove (file, fileList) {
      return this.$confirm(`确认移除${file.name}?`)
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
  .res-name {
    font-size: 15px;
    padding: 5px 0;
  }
  .res-describe {
    font-size: 14px;
    padding: 5px 0;
  }
</style>
