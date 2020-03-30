<template>
  <el-popover v-bind="$attrs" v-model="sVisible" trigger="manual">
    <div class="title">
      <div class="icon">
        <slot name="icon">
          <i :class="icon" :style="`color: ${iconColor}`"></i>
        </slot>
      </div>
      <slot name="title">
        <p :style="`color: ${color}`">{{ title }}</p>
      </slot>
    </div>
    <div class="operate-btns">
      <div @click="cancel">
        <slot name="cancel">
          <el-button size="mini" :type="cancelType">{{ cancelText }}</el-button>
        </slot>
      </div>
      <div @click="confirm">
        <slot name="ok">
          <el-button size="mini" :type="okType">{{ okText }}</el-button>
        </slot>
      </div>
    </div>
    <span slot="reference" @click.stop="handleClick">
      <slot></slot>
    </span>
  </el-popover>
</template>

<script>

let pre = null

let isBinded = false
function bindEvent () {
  if (!isBinded) {
    document.addEventListener('click', e => {
      pre && pre.setVisible(false, e)
    })
    isBinded = true
  }
}

// 用法参考：https://vue.ant.design/components/popconfirm/
export default {
  name: 'Popconfirm',
  props: {
    visible: { // 是否显示
      type: Boolean,
      default: false
    },
    title: { // 提示文本的内容
      type: String,
      default: '你确定要执行此操作吗？'
    },
    color: { // 提示内容文本的颜色
      type: String,
      default: ''
    },
    okType: { // 确认按钮的类型
      type: String,
      default: 'primary'
    },
    okText: { // 确认按钮的文字
      type: String,
      default: '确定'
    },
    cancelType: { // 取消按钮的类型
      type: String,
      default: 'default'
    },
    cancelText: { // 取消按钮的文字
      type: String,
      default: '取消'
    },
    icon: { // 左上角的图标的 class
      type: String,
      default: 'el-icon-info'
    },
    iconColor: { // 左上角的图标的颜色
      type: String,
      default: ''
    },
    check: { // 显示前校验，校验失败不显示，可以抛错误中断，也可以返回Boolean(false以外的都认为通过)
      type: Function,
      default () {
        return () => true
      }
    }
  },
  data () {
    return {
      sVisible: this.visible
    }
  },
  model: {
    prop: 'visible',
    event: 'visibleChange'
  },
  watch: {
    visible (newValue) {
      this.setVisible(newValue)
    }
  },
  mounted () {
    bindEvent()
  },
  methods: {
    confirm (e) {
      this.setVisible(false, e)
      this.$emit('confirm', e)
    },
    cancel (e) {
      this.setVisible(false, e)
      this.$emit('cancel', e)
    },
    setVisible (visible, e) {
      this.sVisible = visible
      this.$emit('visibleChange', visible, e)
    },
    handleClick (e) {
      if (pre && (pre !== this)) {
        pre.setVisible(false, e)
      }
      pre = this

      const v = this.check()
      if (v === false) {
        return false
      }
      if (!('visible' in this.$options.propsData)) {
        this.setVisible(!this.sVisible, e)
      }
    }
  }
}
</script>

<style lang="scss" scoped>
  .operate-btns {
    display: flex;
    justify-content: flex-end;
    > div {
      + div {
        margin-left: 10px;
      }
    }
  }
  .title {
    .icon {
      float: left;
      font-size: 1rem;
      line-height: 1;
      margin-right: 10px;
      .el-icon-error {
        color: #fe6666;
      }
      .el-icon-info {
        color: #1890ff;
      }
    }
  }
</style>
