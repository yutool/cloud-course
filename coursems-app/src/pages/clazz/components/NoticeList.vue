<template>
  <div>
    <el-card v-for="notice in notices" :key="notice.noticeId" class="box-card mt-2" :body-style="{padding:'0px'}">
      <div class="border-bottom p-1 text-center">
        <span>班级通知</span>
        <Popconfirm v-if="isTeacher" title="确定删除通知吗？" width="200" @confirm="removeNotice(notice)" class="float-right">
          <i v-if="isTeacher" class="el-icon-delete float-right pr-3 pt-1 pointer"></i>
        </Popconfirm>
      </div>
      <div class="card-body ">
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
  }
}
</script>

<style lang="scss" scoped>

</style>
