import router from '@/router'
import { login, logout } from '@/api/account'
import { getCurrentUser, updateUserInfo, uploadAvatar, bindEmail, bindPhone } from '@/api/user'

const state = {
  userInfo: {}
}

const mutations = {
  SET_USERINFO (state, user) {
    state.userInfo = user
  }
}

const actions = {
  // user login
  login ({ commit }, form) {
    const { account, password } = form
    return new Promise((resolve, reject) => {
      login({ account: account.trim(), password: password }).then(response => {
        const { code, data } = response
        if (code === 0 && data !== null) {
          commit('SET_USERINFO', data)
          router.push('/course')
        }
        resolve(response)
      }).catch(error => {
        reject(error)
      })
    })
  },
  // get current user info
  getCurrentUser ({ commit }) {
    return new Promise((resolve, reject) => {
      getCurrentUser().then(response => {
        const { data } = response
        commit('SET_USERINFO', data)
        resolve(response)
      }).catch(error => {
        reject(error)
      })
    })
  },
  // user logout
  logout ({ commit }) {
    return new Promise((resolve, reject) => {
      logout().then(response => {
        router.push('/login')
        commit('SET_USERINFO', {})
        resolve(response)
      }).catch(error => {
        reject(error)
      })
    })
  },
  // update user info
  updateInfo ({ commit }, user) {
    return new Promise((resolve, reject) => {
      updateUserInfo(user).then(res => {
        const { code, data } = res
        if (code === 0) {
          commit('SET_USERINFO', data)
        }
        resolve(res)
      }).catch(error => {
        reject(error)
      })
    })
  },
  // user upload avatar
  uploadAvatar ({ commit }, {id, data}) {
    return new Promise((resolve, reject) => {
      uploadAvatar(id, data).then(res => {
        const { code, data } = res
        if (code === 0) {
          commit('SET_USERINFO', data)
        }
        resolve(res)
      }).catch(error => {
        reject(error)
      })
    })
  },
  // user bind email
  bindEmail ({ commit }, user) {
    console.log(user)
    return new Promise((resolve, reject) => {
      bindEmail(user).then(res => {
        const { code, data } = res
        if (code === 0) {
          commit('SET_USERINFO', data)
        }
        resolve(res)
      }).catch(error => {
        reject(error)
      })
    })
  },
  // user bind phone
  bindPhone ({ commit }, user) {
    return new Promise((resolve, reject) => {
      bindPhone(user).then(res => {
        const { code, data } = res
        if (code === 0) {
          commit('SET_USERINFO', data)
        }
        resolve(res)
      }).catch(error => {
        reject(error)
      })
    })
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}
