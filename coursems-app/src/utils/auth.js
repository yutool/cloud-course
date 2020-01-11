import store from '@/store'
export default {
  getToken () {
    return localStorage.getItem('token')
  },
  setToken (token) {
    localStorage.setItem('token', token)
  },
  clearToken () {
    localStorage.removeItem('token')
  },
  login (userInfo, token) {
    store.dispatch('setUserInfo', userInfo)
    localStorage.setItem('token', token)
  },
  logout () {
    localStorage.removeItem('token')
    store.dispatch('clearState')
  },
  addAuthorization (token) {
    return 'Bearer ' + token
  }
}
