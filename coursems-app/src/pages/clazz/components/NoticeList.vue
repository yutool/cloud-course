<template>
  <div class="notice-card">
    <el-card v-for="notice in notices" :key="notice.noticeId" class="mt-2" :body-style="{padding:'0px'}">
      <Popconfirm v-if="isTeacher" title="确定删除通知吗？" width="200" @confirm="removeNotice(notice)" class="float-right">
        <i v-if="isTeacher" class="el-icon-delete float-right pr-4 pt-3 pointer"></i>
      </Popconfirm>

      <div class="card-content">
        <span>{{notice.content}}</span>
        <div class="hpx-10"></div>
        <small>{{notice.releaseTime}}<span style="margin:0 5px;">|</span>{{notice.author}}</small>
      </div>
    </el-card>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'

export default {
  name: 'NoticeList',
  props: ['notices'],
  computed: {
    ...mapGetters(['isTeacher'])
  },
  methods: {
    removeNotice (notice) { // 删除通知
      this.$store.dispatch('clazz/deleteNotice', notice)
    }
  }
}
</script>

<style lang="scss" scoped>
.notice-card {
  padding-left: 2rem;
  padding-right: 1rem;
  .card-content {
    padding: 20px;
  }
  .card-content:hover {
    background: rgb(229, 248, 252);
  }
}
</style>
