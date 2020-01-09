<template>
  <div class="container">
    <!-- 导航栏 -->
    <div class="row">
      <div class="col-md-5">
        <nav aria-label="breadcrumb">
          <ol class="breadcrumb bg-white pt-1">
            <li class="breadcrumb-item"><router-link to="/tkit">教学包</router-link></li>
            <li class="breadcrumb-item active" aria-current="page">查找教学包</li>
          </ol>
        </nav>
      </div>
      <div class="col-md-7">
        <div class="input-group mb-3">
          <input type="text" class="form-control" placeholder="Recipient's username" aria-label="Recipient's username" aria-describedby="button-addon2">
          <div class="input-group-append">
            <button class="btn btn-outline-secondary" type="button" id="button-addon2">Button</button>
          </div>
        </div>
      </div>
    </div>
    <!-- 内容 -->
    <div class="shadow rounded-lg p-3">
      <div class="row">
        <!-- 筛选 -->
        <div class="col-md-3">
          <div class="filter-title clearfix">
            <span class="fs-18">筛选</span>
            <button class="btn btn-primary btn-flat float-right">重置</button>
          </div>
          <div class="filter-content">
            <!-- 筛选发布源 -->
            <div class="suit-title border-top border-bottom">
              <span>发布源</span>
            </div>
            <div class="suit-content ml-3">
              <div class="custom-control custom-checkbox pb-3">
                <input type="checkbox" class="custom-control-input" id="customCheckDisabled1">
                <label class="custom-control-label" for="customCheckDisabled1">不看用户发布</label>
              </div>
              <div class="custom-control custom-checkbox pb-3">
                <input type="checkbox" class="custom-control-input" id="customCheckDisabled2">
                <label class="custom-control-label" for="customCheckDisabled2">不看官方发布</label>
              </div>
            </div>
            <!-- 筛选适用层次 -->
            <div class="suit-title border-top border-bottom">
              <span>适用层次</span>
            </div>
            <div class="suit-content ml-3">
              <div class="custom-control custom-checkbox pb-3">
                <input type="checkbox" class="custom-control-input" id="customCheckDisabled1">
                <label class="custom-control-label" for="customCheckDisabled1">Check this</label>
              </div>
              <div class="custom-control custom-checkbox pb-3">
                <input type="checkbox" class="custom-control-input" id="customCheckDisabled2">
                <label class="custom-control-label" for="customCheckDisabled2">Check this</label>
              </div>
              <div class="custom-control custom-checkbox pb-3">
                <input type="checkbox" class="custom-control-input" id="customCheckDisabled3">
                <label class="custom-control-label" for="customCheckDisabled3">Check this</label>
              </div>
            </div>
          </div>
        </div>
        <!-- 教学包列表 -->
        <div class="col-md-9">
          <div class="list-group">
            <router-link to="detail" class="list-group-item list-group-item-action">
              <img src="@/assets/images/1.jpeg" class="wpx-90 float-left" alt="">
              <div class="float-left pt-3 pl-4">
                  <span>课程名：xxx</span>
                  <div class="hpx-10"></div>
                  <span >xxx发布|3使用</span>
              </div>
              <div class="float-right pt-3">
                  <span>adfdasf</span>
                  <div class="hpx-10"></div>
                  <span>fasdfds</span>
              </div>
            </router-link>
            <router-link to="cpdetail" class="list-group-item list-group-item-action">
              <img src="@/assets/images/1.jpeg" class="wpx-90 float-left" alt="">
              <div class="float-left pt-3 pl-4">
                  <span>课程名：xxx</span>
                  <div class="hpx-10"></div>
                  <span >xxx发布|3使用</span>
              </div>
              <div class="float-right pt-3">
                  <span>adfdasf</span>
                  <div class="hpx-10"></div>
                  <span>fasdfds</span>
              </div>
            </router-link>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'TkitSearch',
  data: () => ({

  })
}
</script>

<style lang="scss" scoped>
  .filter-title {
    padding: 10px 0;
  }
  .suit-title {
    font-size: 18px;
    padding: 10px 0;
  }
  .suit-content {
    padding: 10px 0;
  }
  .packs-search {
    width: 60%;
    margin: 1rem auto;
  }
</style>
