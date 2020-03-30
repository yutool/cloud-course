<template>
  <div>
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>账号安全</span>
      </div>
      <!-- 绑定邮箱 -->
      <email-card />
      <!-- 绑定手机 -->
      <mobile-card />
      <!-- 修改密码 -->
      <password-card />
    </el-card>
  </div>
</template>

<script>
import EmailCard from './components/EmailCard'
import MobileCard from './components/MobileCard'
import PasswordCard from './components/PasswordCard'

export default {
  name: 'Security',
  components: {
    EmailCard, MobileCard, PasswordCard
  }
}
</script>

<style lang="scss" scoped>
  .card-body {
    padding: 2rem 0;
  }
</style>
