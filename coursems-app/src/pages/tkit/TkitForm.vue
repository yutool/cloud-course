<template>
  <div class="container">
    <!-- 导航栏 -->
    <nav aria-label="breadcrumb">
      <ol class="breadcrumb bg-white">
        <li class="breadcrumb-item"><router-link to="/tkit">教学包</router-link></li>
        <li class="breadcrumb-item"><router-link to="/tkit/manage">教学包管理</router-link></li>
        <li class="breadcrumb-item active" aria-current="page">创建教学包</li>
      </ol>
    </nav>
    <!-- 内容 -->
    <form class="w-75 mg-auto shadow rounded-lg p-5">
      <h5 class="text-center">创建教学包</h5>
      <div class="form-group">
        <label for="exampleInputEmail1">Email address</label>
        <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp">
      </div>
      <div class="form-group">
        <label for="exampleInputPassword1">Password</label>
        <input type="password" class="form-control" id="exampleInputPassword1">
      </div>
      <button type="submit" class="btn btn-primary btn-block">提交</button>
    </form>
  </div>
</template>

<script>
export default {
  name: 'TkitForm',
  data: () => ({

  })
}
</script>

<style lang="scss" scoped>

</style>
