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
  store.dispatch('app/setLoading', true)
  return config
}, error => {
  Promise.reject(error)
})

// 拦截响应
instance.interceptors.response.use(response => {
  store.dispatch('app/setLoading', false)
  // 全局统一处理 Session超时
  if (response.headers['session_time_out'] === 'timeout') {
    Message({ type: 'error', message: '会话超时：session_time_out' })
  }
  const { code, message } = response.data
  // 处理请求错误
  if (code !== 0) {
    if (message) Message({ type: 'error', message: `错误请求：${message}` })
    return Promise.reject(message)
  }
  return response.data
}, error => {
  // 请求被拦截被跳到这里
  store.dispatch('app/setLoading', false)
  Message({ type: 'error', message: `被拦截请求：${error.message}` })
  return Promise.reject(error)
})

export default instance
