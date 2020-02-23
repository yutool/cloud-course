<template>
  <div class="border-bottom clearfix p-1">
    <img :src="member.avatar" class="wpx-75 rounded-circle m-2 float-left" alt="头像">
    <div class="float-left pt-1 pl-2">
        <div class="hpx-10"></div>
        <span class="pr-2">姓名：{{member.stuName}}</span>
        <span class="pr-2">学号：{{member.stuNum}}</span>
        <span>经验：{{member.experience}}</span>
        <div class="hpx-10"></div>
        <small class="align-middle" >
          个性签名: {{member.signature || '这个人很懒，什么都没写。。。'}}
        </small>
    </div>
  </div>
</template>

<script>
export default {
  name: 'MemberInfo',
  props: ['member'],
  data: () => ({

  }),
  components: {

  }
}
</script>

<style lang="scss" scoped>

</style>
