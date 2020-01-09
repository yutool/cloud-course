import axios from 'axios'
import auth from '@/api/auth'
import store from '@/store'

export function request (options) {
  return new Promise((resolve, reject) => {
    // instance创建一个axios实例，可以自定义配置，可在 axios文档中查看详情
    const instance = axios.create({
      // 所有的请求都会带上这些配置，比如全局都要用的身份信息等。
      headers: {
        'Content-Type': 'application/json'
      },
      timeout: 30 * 1000 // 30秒超时
    })
    // 拦截请求
    instance.interceptors.request.use(config => {
      const token = auth.getToken()
      if (token) { // 如果本地存在token，请求时带上
        config.headers.Authorization = auth.addAuthorization(token)
      }
      return config
    }, error => {
      reject(error)
    })
    var isRefreshing = false
    var requests = []
    function refreshToken () { // 获取token，待修改
      return instance.post('/refreshtoken', { 'userInfo': JSON.stringify(store.state.userInfo) })
      // .then(res => res.data)
    }
    // 拦截相应
    instance.interceptors.response.use(response => {
      /*
        当服务器返回相应吗status吗1000说明token失效
        1 设置标记，说明正在刷新token
        2 如果这时有请求，将其添加到requests
        3 刷新token后，requests中的请求发至服务器
      */
      if (response.data.status === 1000) {
        alert('fsdfdsfds')
        const config = response.config
        if (!isRefreshing) {
          isRefreshing = true
          return refreshToken().then(res => {
            var token = res.data.token
            auth.setToken(token)
            config.headers.Authorization = auth.addAuthorization(token)
            // 已经刷新了token，将所有队列中的请求进行重试
            requests.forEach(cb => cb(token))
            requests = []
            return instance(config)
          }).catch(res => {
            console.error('refreshtoken error =>', res)
            window.location.href = '/'
          }).finally(() => {
            isRefreshing = false
          })
        } else { // 正在刷新token
          return new Promise((resolve) => {
            // 将resolve放进队列，用一个函数形式来保存，等token刷新后直接执行
            requests.push((token) => {
              config.headers.Authorization = auth.addAuthorization(token)
              resolve(instance(config))
            })
          })
        }
      }
      return response.data
    }, error => {
      // 请求被拦截被跳到这里
      reject(error)
      // alert('fdsfa')
      // if (error.response.status === 401 || error.response.status === 403) {
      //   // return Promise.reject(error)
      //   reject(error)
      // }
    })
    // 执行的请求
    instance(options).then(response => {
      resolve(response) // 把请求到的数据发到引用请求的地方
    }).catch(error => {
      console.log('请求异常信息：' + error)
      reject(error)
    })
  })
}
