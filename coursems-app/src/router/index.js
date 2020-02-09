import Vue from 'vue'
import Router from 'vue-router'
import store from '@/store'
// import { Message } from 'element-ui'

// 导入组件
import Login from '@/pages/login/Login.vue'
import Course from '@/pages/course/index.vue'
import About from '@/pages/about/About.vue'

Vue.use(Router)
// 解决跳转路由页面路由一致报错
const originalPush = Router.prototype.push
Router.prototype.push = function push (location) {
  return originalPush.call(this, location).catch(err => err)
}

const router = new Router({
  mode: 'history',
  routes: [
    { path: '/', component: About },
    { path: '/login', component: Login },
    {
      path: '/course',
      component: Course,
      meta: { requireAuth: true }
    },
    { path: '/register', component: () => import('@/pages/login/Register.vue') },
    { path: '/forget_password', component: () => import('@/pages/login/ForgetPassword.vue') },
    { path: '/course/create', component: () => import('@/pages/course/CreateCourse.vue') },
    { path: '/course/join', component: () => import('@/pages/course/JoinCourse.vue') },
    {
      path: '/feedback',
      component: () => import('@/pages/about/FeedBack.vue'),
      meta: { requireAuth: true }
    },
    {
      path: '/account',
      component: () => import('@/pages/account/index.vue'),
      meta: { requireAuth: true },
      children: [
        { path: 'information', component: () => import('@/pages/account/Information.vue') },
        { path: 'security', component: () => import('@/pages/account/Security.vue') },
        { path: 'experience', component: () => import('@/pages/account/Experience.vue') },
        { path: '/account', redirect: 'information' }
      ]
    },
    {
      path: '/clazz',
      component: () => import('@/pages/clazz/index.vue'),
      meta: { requireAuth: true },
      children: [
        { path: 'member', component: () => import('@/pages/clazz/Member.vue') },
        { path: 'resource', component: () => import('@/pages/clazz/Resource.vue') },
        { path: 'notice', component: () => import('@/pages/clazz/Notice.vue') },
        { path: 'detail', component: () => import('@/pages/clazz/Detail.vue') },
        { path: 'appraise', component: () => import('@/pages/clazz/Appraise.vue') }
      ]
    }
  ]
})

router.beforeEach((to, from, next) => {
  if (to.path === '/login') {
    next()
  } else {
    store.dispatch('getCurrentUser').then(res => {
      next()
    }).catch(() => {
      next({ path: '/' })
    })
  }
  // if (to.meta.requireAuth) { // 如果meta中存在requireAuth进行拦截
  //   // if (localStorage.getItem('token')) {
  //   //   next()
  //   // } else {
  //   //   // 跳转至登录页面，并将页面路径作为参数，完成后跳转回来
  //   //   next({ path: '/login', query: {redirect: to.fullPath} })
  //   //   Message({ type: 'warning', showClose: true, message: '请先登录哦!' })
  //   // }
  //   next()
  // } else {
  //   next()
  // }
})

export default router
