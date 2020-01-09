import Vue from 'vue'
import Router from 'vue-router'
import { Message } from 'element-ui'

// 导入组件
import Courses from '@/pages/course/Courses.vue'
import CreateCourse from '@/pages/course/CreateCourse.vue'
import JoinCourse from '@/pages/course/JoinCourse.vue'
import Account from '@/pages/account/Account.vue'
import FeedBack from '@/pages/about/FeedBack.vue'
import About from '@/pages/about/About.vue'
import Login from '@/pages/login/Login.vue'
import Register from '@/pages/login/Register.vue'
import ResetPwd from '@/pages/login/ResetPwd.vue'
import Clazz from '@/pages/clazz/Clazz.vue'

// 懒加载组件
const Member = () => import('@/pages/clazz/Member.vue')
const Resource = () => import('@/pages/clazz/Resource.vue')
const Notice = () => import('@/pages/clazz/Notice.vue')
const Detail = () => import('@/pages/clazz/Detail.vue')
const Appraise = () => import('@/pages/clazz/Appraise.vue')
const Information = () => import('@/pages/account/Information.vue')
const Security = () => import('@/pages/account/Security.vue')
const Experience = () => import('@/pages/account/Experience.vue')

Vue.use(Router)
// 解决跳转路由页面路由一致报错
const originalPush = Router.prototype.push
Router.prototype.push = function push (location) {
  return originalPush.call(this, location).catch(err => err)
}

const router = new Router({
  mode: 'history',
  routes: [
    { path: '/login', component: Login },
    { path: '/register', component: Register },
    { path: '/resetpwd', component: ResetPwd },
    { path: '/about', component: About },
    {
      path: '/course',
      component: Courses,
      meta: { requireAuth: true }
    },
    { path: '/course/create', component: CreateCourse },
    { path: '/course/join', component: JoinCourse },
    {
      path: '/feedback',
      component: FeedBack,
      meta: { requireAuth: true }
    },
    {
      path: '/account',
      component: Account,
      meta: { requireAuth: true },
      children: [
        { path: 'information', component: Information },
        { path: 'security', component: Security },
        { path: 'experience', component: Experience },
        { path: '/account', redirect: 'information' }
      ]
    },
    {
      path: '/clazz',
      component: Clazz,
      meta: { requireAuth: true },
      children: [
        { path: 'member', component: Member },
        { path: 'resource', component: Resource },
        { path: 'notice', component: Notice },
        { path: 'detail', component: Detail },
        { path: 'appraise', component: Appraise }
      ]
    },
    { path: '/', redirect: '/login' }
  ]
})

router.beforeEach((to, from, next) => {
  if (to.meta.requireAuth) { // 如果meta中存在requireAuth进行拦截
    if (localStorage.getItem('token')) {
      next()
    } else {
      // 跳转至登录页面，并将页面路径作为参数，完成后跳转回来
      next({ path: '/login', query: {redirect: to.fullPath} })
      Message({ type: 'warning', showClose: true, message: '请先登录哦!' })
    }
  } else {
    next()
  }
})

export default router
