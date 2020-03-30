<template>
  <ul class="list-group">
    <li v-for="member in members" :key="member.userId" class="list-group-item list-group-item-action pointer" @click="selectMember(member)">
      <img :src="member.avatar" class="wpx-30 mr-2 rounded-circle" alt="">
      <span>{{member.stuName}}</span> <span>{{member.stuNum}}</span>
      <span class="float-right">{{member.experience}}</span>
      <!-- <span class="float-right">{{member.score!==-1 ? member.score : '未评分'}}</span> -->
    </li>
  </ul>
</template>

<script>
export default {
  name: 'MemberList',
  props: ['members'],
  data: () => ({

  }),
  methods: {
    selectMember (member) { // 切换成员
      this.$store.dispatch('clazz/setSelectedMember', member)
    }
  }
}
</script>

<style lang="scss" scoped>

</style>
