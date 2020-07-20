<template>
  <div>
    <div v-for="r in resources" :key="r.resId" class="res-card clearfix">
      <img src="@/assets/res-default.png" class="wpx-60 float-left" alt="">
        <div class="plpx-75">
          <div class="res-name">
            <span>{{r.resName}}</span>
            <Popconfirm v-if="isTeacher" title="确定删除资源吗？" width="200" @confirm="removeResource(r)" class="float-right">
              <i v-if="isTeacher" class="el-icon-delete float-right pr-3 pt-1 pointer"></i>
            </Popconfirm>
          </div>
          <div class="res-describe text-truncate">
            <span>{{r.resSize}}</span> <span style="margin:0 5px;">|</span>
            <span>{{r.uploadTime}}</span> <span style="margin:0 5px;">|</span>
            <span>{{r.experience}}经验</span> <span style="margin:0 5px;">|</span>
            <span><a :href="r.downLink">下载资源</a></span>
          </div>
        </div>
    </div>
  </div>
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
.res-card {
  padding: 20px;
  border: 1px solid #fff;
  border-bottom: 1px solid #CCC;
  cursor: pointer;
}
.res-card:hover {
  border: 1px solid #ccc;
}
</style>
