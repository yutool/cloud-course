<template>
  <ul class="list-group pt-2">
    <li v-for="resource in resources" :key="resource.resId" class="list-group-item list-group-item-action">
      <img src="@/assets/1.jpeg" class="wpx-60 float-left" alt="">
      <div class="plpx-75">
        <div class="res-name">
          <span>{{resource.resName}}</span>
          <Popconfirm v-if="isTeacher" title="确定删除资源吗？" width="200" @confirm="removeResource(resource)" class="float-right">
            <i v-if="isTeacher" class="el-icon-delete float-right pr-3 pt-1 pointer"></i>
          </Popconfirm>
        </div>
        <div class="res-describe text-truncate">
          <span>{{resource.resSize}}</span> <span style="margin:0 5px;">|</span>
          <span>{{resource.uploadTime}}</span> <span style="margin:0 5px;">|</span>
          <span>{{resource.experience}}经验</span> <span style="margin:0 5px;">|</span>
          <span><a :href="resource.downLink">下载资源</a></span>
          <!-- <span>{{resource.readed || 0}}人已查看</span> -->
        </div>
      </div>
    </li>
  </ul>
</template>

<script>
import { mapGetters } from 'vuex'

export default {
  name: 'ResourceList',
  props: ['resources'],
  data: () => ({

  }),
  computed: {
    ...mapGetters(['isTeacher'])
  },
  methods: {
    removeResource (resource) {
      this.$store.dispatch('clazz/deleteResource', resource)
    }
  }
}
</script>

<style lang="scss" scoped>

</style>
