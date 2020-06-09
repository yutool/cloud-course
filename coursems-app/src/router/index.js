import Vue from 'vue'
import Router from 'vue-router'
import store from '@/store'
import NProgress from 'nprogress'
import 'nprogress/nprogress.css'
// import { Message } from 'element-ui'

// 导入组件
import Login from '@/pages/login/Login.vue'
import Course from '@/pages/course/index.vue'
import About from '@/pages/about/About.vue'

NProgress.configure({
  showSpinner: false // 是否显示加载ico
  // easing: 'ease', // 动画方式
  // speed: 500, // 递增速度
  // trickleSpeed: 200, // 自动递增间隔
  // minimum: 0.0 // 初始化时的最小百分比
})

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
      children: [
        { path: 'information', component: () => import('@/pages/account/Information.vue') },
        { path: 'security', component: () => import('@/pages/account/Security.vue') },
        { path: 'experience', component: () => import('@/pages/account/Experience.vue') },
        { path: '/account', redirect: 'information' }
      ],
      meta: { requireAuth: true }
    },
    {
      path: '/clazz/:id',
      component: () => import('@/pages/clazz/index.vue'),
      children: [
        { path: 'member', component: () => import('@/pages/clazz/Member.vue') },
        { path: 'resource', component: () => import('@/pages/clazz/Resource.vue') },
        { path: 'notice', component: () => import('@/pages/clazz/Notice.vue') },
        { path: 'detail', component: () => import('@/pages/clazz/Detail.vue') },
        { path: 'appraise', component: () => import('@/pages/clazz/Appraise.vue') }
      ],
      redirect: '/clazz/:id/member',
      meta: { requireAuth: true }
    },
    {
      path: '/404',
      component: () => import('@/pages/about/About.vue')
    },
    {
      path: '*',
      redirect: '/404'
    }
  ]
})

router.beforeEach(async (to, from, next) => {
  NProgress.start()
  const { data } = await store.dispatch('user/getCurrentUser')
  if (data) {
    if (to.path === '/login') {
      next({ path: '/' })
    } else {
      next()
    }
  } else {
    if (to.matched.some(r => r.meta.requireAuth)) {
      if (to.path === '/login') {
        next()
      } else {
        next(`/login?redirect=${to.path}`)
      }
    } else {
      next()
    }
  }
})

router.afterEach(() => {
  NProgress.done()
})

export default router
