<template>
  <div class="container">
    <!-- 导航栏 -->
    <div class="row">
      <div class="col-md-9">
        <nav aria-label="breadcrumb">
          <ol class="breadcrumb bg-white">
            <li class="breadcrumb-item"><router-link to="/tkit">教学包</router-link></li>
            <li class="breadcrumb-item active" aria-current="page">教学包管理</li>
          </ol>
        </nav>
      </div>
      <div class="col-md-3">
        <router-link to="form" class="btn btn-primary mt-2 float-right">添加教学包</router-link>
      </div>
    </div>
    <!-- 内容 -->
    <div class="shadow rounded-lg p-3">
      <div class="row">
        <div class="col-md-3">
          <div class="card">
            <img src="@/assets/images/logo.png" class="card-img-top" alt="...">
            <div class="card-body">
              <h5 class="card-title">Card title</h5>
              <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
            </div>
          </div>
        </div>
        <div class="col-md-3">
          <div class="card">
            <img src="@/assets/images/logo.png" class="card-img-top" alt="...">
            <div class="card-body">
              <h5 class="card-title">Card title</h5>
              <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'TkitManage',
  data: () => ({

  })
}
</script>

<style lang="scss" scoped>

</style>
