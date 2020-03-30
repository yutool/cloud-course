<template>
  <div>
    <!-- 幻灯片 -->
    <div id="carouselExampleCaptions" class="carousel slide" data-ride="carousel">
      <ol class="carousel-indicators">
        <li data-target="#carouselExampleCaptions" data-slide-to="0" class="active"></li>
        <li data-target="#carouselExampleCaptions" data-slide-to="1"></li>
        <li data-target="#carouselExampleCaptions" data-slide-to="2"></li>
      </ol>
      <div class="carousel-inner">
        <div class="carousel-item active">
          <img src="@/assets/images/ccp-banner.jpg" class="d-block w-100" alt="...">
          <div class="carousel-caption d-none d-md-block">
            <h5>First slide label</h5>
            <p>Nulla vitae elit libero, a pharetra augue mollis interdum.</p>
          </div>
        </div>
        <div class="carousel-item">
          <img src="@/assets/images/ccp-banner.jpg" class="d-block w-100" alt="...">
          <div class="carousel-caption d-none d-md-block">
            <h5>Second slide label</h5>
            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
          </div>
        </div>
        <div class="carousel-item">
          <img src="@/assets/images/ccp-banner.jpg" class="d-block w-100" alt="...">
          <div class="carousel-caption d-none d-md-block">
            <h5>Third slide label</h5>
            <p>Praesent commodo cursus magna, vel scelerisque nisl consectetur.</p>
          </div>
        </div>
      </div>
      <a class="carousel-control-prev" href="#carouselExampleCaptions" role="button" data-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="sr-only">Previous</span>
      </a>
      <a class="carousel-control-next" href="#carouselExampleCaptions" role="button" data-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="sr-only">Next</span>
      </a>
    </div>
    <!-- 内容 -->
    <div class="container">
      <!-- 搜索栏 -->
      <div class="package-search mt-3 row">
        <div class="col-md-9">
          <div class="input-group">
            <input type="text" class="form-control" placeholder="Recipient's username" aria-label="Recipient's username" aria-describedby="button-addon2">
            <div class="input-group-append">
              <button class="btn btn-outline-secondary" type="button" id="button-addon2">搜索</button>
            </div>
          </div>
        </div>
        <div class="col-md-3">
          <router-link to="/tkit/manage" class="btn btn-primary float-right">教学包管理</router-link>
          <router-link to="/tkit/search" class="btn btn-primary float-right">a</router-link>
        </div>
      </div>
      <!-- 教学包 -->
      <div class="package-box">
        <!-- 官方教学包 -->
        <div class="package">
           <div class="package-title border-bottom clearfix mt-3 mb-3">
              <h5 class="float-left">官方精选教学包</h5>
              <a href="#" class="float-right">更多</a>
          </div>
          <div class="package-content row">
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
        <!-- 用户教学包 -->
          <div class="package">
            <div class="package-title border-bottom clearfix mt-3 mb-3">
              <h5 class="float-left">用户教学包</h5>
              <a href="#" class="float-right">更多</a>
            </div>
            <div class="package-content row">
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
    </div>
  </div>
</template>

<script>
export default {
  name: 'TeachingKit',
  data: () => ({

  })
}
</script>

<style lang="scss" scoped>
  .carousel {
    margin-top: -1rem;
  }
</style>
