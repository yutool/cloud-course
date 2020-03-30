<template>
  <el-dialog title="图片剪裁" :visible.sync="visible" append-to-body
            :close-on-click-modal="false" :width="dialogWidth" :before-close="beforeClose">
    <div class="cropper-content">
      <div class="cropper" style="text-align:center">
        <vueCropper ref="cropper"
            :img="url"
            :outputSize="option.size"
            :outputType="option.outputType"
            :info="true"
            :full="option.full"
            :canMove="option.canMove"
            :canMoveBox="option.canMoveBox"
            :original="option.original"
            :autoCrop="option.autoCrop"
            :fixed="option.fixed"
            :fixedNumber="option.fixedNumber"
            :centerBox="option.centerBox"
            :infoTrue="option.infoTrue"
            :fixedBox="option.fixedBox"
          >
        </vueCropper>
      </div>
    </div>
    <div slot="footer" class="dialog-footer">
      <el-button type="primary" @click="confirmUpload">确认上传</el-button>
    </div>
  </el-dialog>
</template>

<script>

export default {
  name: 'CropperDialog',
  props: ['visible', 'url'],
  data () {
    return {
      dialogWidth: document.documentElement.clientWidth > 600 ? '600px' : '100%',
      // 裁剪组件的基础配置option
      option: {
        img: '', // 裁剪图片的地址
        info: true, // 裁剪框的大小信息
        outputSize: 0.8, // 裁剪生成图片的质量
        outputType: 'jpeg', // 裁剪生成图片的格式
        canScale: false, // 图片是否允许滚轮缩放
        autoCrop: true, // 是否默认生成截图框
        fixedBox: false, // 固定截图框大小 不允许改变
        fixed: true, // 是否开启截图框宽高固定比例
        fixedNumber: [1, 1], // 截图框的宽高比例
        full: false, // 是否输出原图比例的截图
        canMoveBox: true, // 截图框能否拖动
        original: false, // 上传图片按照原始比例渲染
        centerBox: true, // 截图框是否被限制在图片里面
        infoTrue: true // true 为展示真实输出图片宽高 false 展示看到的截图框宽高
      }
    }
  },
  methods: {
    // 关闭按钮
    beforeClose () {
      this.$emit('close')
    },
    // 点击裁剪，这一步是可以拿到处理后的地址
    confirmUpload () {
      this.$refs.cropper.getCropBlob((data) => {
        // 分发一个确认上传事件
        this.$emit('confirm', data)
      })
    }
  }
}
</script>

<style lang="scss" scoped>
.cropper-content {
  .cropper {
    width: auto;
    height: 300px;
  }
}
</style>
