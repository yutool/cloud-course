import axios from 'axios'
import store from '@/store'
import {Message} from 'element-ui'

// 每次请求携带cookies信息
axios.defaults.withCredentials = true
// 创建axios实例
const instance = axios.create({
  // baseURL: process.env.ADMIN_SERVER,
  baseURL: '/api',
  headers: {
    'Content-Type': 'application/json'
  },
  timeout: 20 * 1000 // 20秒超时
})

// 拦截请求
instance.interceptors.request.use(config => {
  store.dispatch('setFullscreenLoading', true)
  return config
}, error => {
  Promise.reject(error)
})

// 拦截响应
instance.interceptors.response.use(response => {
  store.dispatch('setFullscreenLoading', false)
  return response.data
}, error => {
  // 请求被拦截被跳到这里
  store.dispatch('setFullscreenLoading', false)
  Message({ type: 'error', message: error.message })
  Promise.reject(error)
})

export default instance
