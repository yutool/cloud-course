<template>
  <el-dialog
    :title="title"
    :visible.sync="visible"
    :width="dialogWidth"
    :before-close="close"
    :close-on-click-modal="false"
  >
    <slot></slot>
    <slot name="footer"></slot>
  </el-dialog>
</template>

<script>
export default {
  name: 'AutoDialog',
  props: ['title', 'visible', 'width'],
  data: () => ({
    dialogWidth: ''
  }),
  methods: {
    close () {
      this.$emit('close')
    }
  },
  mounted () {
    this.dialogWidth = `${this.width}px`
    const that = this
    // 注意，使用的组件不可以再定义 window.onresize，否则这里的失效
    window.onresize = () => {
      that.dialogWidth = document.documentElement.clientWidth > this.width ? `${this.width}px` : '100%'
    }
  },
  watch: {
    dialogWidth (val) {
      // 为了避免频繁触发resize函数导致页面卡顿，使用定时器
      if (!this.timer) {
        this.dialogWidth = val
        this.timer = true
        let that = this
        setTimeout(() => {
          that.timer = false
        }, 300)
      }
    }
  }
}
</script>

<style lang="scss" scoped>

</style>
