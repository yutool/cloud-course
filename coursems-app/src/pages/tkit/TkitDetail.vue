<template>
  <div class="container">
    <!-- 导航栏 -->
    <nav aria-label="breadcrumb">
      <ol class="breadcrumb bg-white">
        <li class="breadcrumb-item"><router-link to="/tkit">教学包</router-link></li>
        <li class="breadcrumb-item"><router-link to="/tkit/search">查找教学包</router-link></li>
        <li class="breadcrumb-item active" aria-current="page">课程包详情</li>
      </ol>
    </nav>
    <!-- 内容 -->
    <div class="card">
      <!-- 卡片标题 -->
      <div class="card-header mb-2">
        <div class="row">
          <div class="col-md-7">
          <img src="@/assets/1.jpeg" class="wpx-90 float-left" alt="">
          <div class="plpx-115">
            <h5 class="mt-3">课程名：xxx</h5>
            <span>xxx发布</span>
            <span class="ml-2 mr-2">|</span>
            <span>xx引用</span>
          </div>
          </div>
          <div class="col-md-5">
            <router-link to="/creclazz" class="btn btn-primary float-right">使用教学包创建课程</router-link>
          </div>
        </div>
      </div>
      <!-- 卡片内容 -->
      <div class="row p-3">
        <div class="col-md-3">
          <div class="list-group" id="list-tab" role="tablist">
          <a class="list-group-item list-group-item-action active" id="list-home-list" data-toggle="list" href="#list-home" role="tab" aria-controls="home">基本信息</a>
          <a class="list-group-item list-group-item-action" id="list-profile-list" data-toggle="list" href="#list-profile" role="tab" aria-controls="profile">资源列表</a>
          <a class="list-group-item list-group-item-action" id="list-messages-list" data-toggle="list" href="#list-messages" role="tab" aria-controls="messages">教学包描述</a>
        </div>
        </div>
        <div class="col-md-9">
          <div class="tab-content" id="nav-tabContent">
            <div class="tab-pane fade show active text-center" id="list-home" role="tabpanel" aria-labelledby="list-home-list">
              <div><span>教程包名：</span><span>xxx</span></div>
              <div><span>教程包名：</span><span>xxx</span></div>
              <div><span>教程包名：</span><span>xxx</span></div>
              <div><span>教程包名：</span><span>xxx</span></div>
              <div><span>教程包名：</span><span>xxx</span></div>
              <div><span>教程包名：</span><span>xxx</span></div>
            </div>
            <div class="tab-pane fade" id="list-profile" role="tabpanel" aria-labelledby="list-profile-list">
              <ul class="list-group list-group-flush">
                <li class="list-group-item clearfix">
                  <img src="@/assets/1.jpeg" class="wpx-40 float-left" alt="">
                  <div class="pl-3 pt-2 float-left">1.资源名xxx</div>
                  <div class="pt-2 float-right">描述</div>
                </li>
                <li class="list-group-item clearfix">
                  <img src="@/assets/1.jpeg" class="wpx-40 float-left" alt="">
                  <div class="pl-3 pt-2 float-left">1.资源名xxx</div>
                  <div class="pt-2 float-right">描述</div>
                </li>
                <li class="list-group-item clearfix">
                  <img src="@/assets/1.jpeg" class="wpx-40 float-left" alt="">
                  <div class="pl-3 pt-2 float-left">1.资源名xxx</div>
                  <div class="pt-2 float-right">描述</div>
                </li>
              </ul>
            </div>
            <div class="tab-pane fade" id="list-messages" role="tabpanel" aria-labelledby="list-messages-list">
              <p class="card-text">
                Lorem ipsum dolor sit amet, consectetur adipisicing elit. Dolores, ducimus quisquam, dolorum quo repellendus voluptatibus eaque tempore praesentium omnis eius vel eos veritatis porro ipsum fugiat deleniti veniam sed sit!
              </p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'TkitDetail',
  data: () => ({

  })
}
</script>

<style lang="scss" scoped>
</style>
