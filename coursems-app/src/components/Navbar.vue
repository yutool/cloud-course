<template>
  <nav class="navbar navbar-expand-lg navbar-light bg-light mb-3">
    <div class="container">
      <router-link to="/course" class="navbar-brand active">
        <i class="el-icon-cloudy"></i>
        云班课
      </router-link>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="nav navbar-nav mr-auto">
          <li class="nav-item">
            <router-link to="/tkit" class="nav-link disabled">教学包</router-link>
          </li>
          <li class="nav-item">
            <a class="nav-link disabled" href="#">任务中心</a>
          </li>
          <li class="nav-item">
            <a class="nav-link disabled" href="#">库管理</a>
          </li>
          <li class="nav-item">
            <router-link to="/" class="nav-link">关于我们</router-link>
          </li>
          <li class="nav-item">
            <router-link to="/feedback" class="nav-link">反馈</router-link>
          </li>
        </ul>
        <!-- 用户信息等 -->
        <ul class="navbar-nav navbar-right">
          <li v-if="userInfo" class="nav-item">
            <router-link class="nav-link user-info" to="/account">
              <img :src="userInfo.avatar" width="30" class="user-photo">
              <span class="user-name">{{userInfo.userName}}</span>
            </router-link>
          </li>
          <li class="nav-item">
            <router-link v-if="!userInfo" class="nav-link" to="/login">登录</router-link>
            <a v-if="userInfo" class="nav-link pointer" @click="logout">退出</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">帮助</a>
          </li>
        </ul>
      </div>
    </div>
  </nav>
</template>

<script>
import { mapState } from 'vuex'

export default {
  name: 'Navbar',
  data: () => ({
  }),
  computed: {
    ...mapState({
      userInfo: state => state.user.userInfo
    })
  },
  methods: {
    logout () {
      this.$store.dispatch('user/logout')
    }
  }
}
</script>

<style lang='scss' scoped>
  .user-info {
    padding: 6px;
    .user-photo {
      border-radius: 100px;
    }
    .user-name {
      padding: 10px;
    }
  }
</style>
