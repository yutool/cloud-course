import router from '@/router'
import { login, logout, visitorsLogin } from '@/api/account'
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
      login({ account: account.trim(), password: password }).then(res => {
        commit('SET_USERINFO', res.data)
        router.push('/course')
        resolve(res)
      }).catch(error => {
        reject(error)
      })
    })
  },
  // user logout
  visitorsLogin ({ commit }) {
    return new Promise((resolve, reject) => {
      visitorsLogin().then(res => {
        commit('SET_USERINFO', res.data)
        router.push('/course')
        resolve(res)
      }).catch(error => {
        reject(error)
      })
    })
  },
  // get current user info
  getCurrentUser ({ commit }) {
    return new Promise((resolve, reject) => {
      getCurrentUser().then(res => {
        const { data } = res
        if (data !== null) commit('SET_USERINFO', data)
        resolve(res)
      }).catch(error => {
        reject(error)
      })
    })
  },
  // user logout
  logout ({ commit }) {
    return new Promise((resolve, reject) => {
      logout().then(res => {
        router.push('/login')
        commit('SET_USERINFO', {})
        resolve(res)
      }).catch(error => {
        reject(error)
      })
    })
  },
  // update user info
  updateInfo ({ commit }, user) {
    return new Promise((resolve, reject) => {
      updateUserInfo(user).then(res => {
        commit('SET_USERINFO', res.data)
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
        commit('SET_USERINFO', res.data)
        resolve(res)
      }).catch(error => {
        reject(error)
      })
    })
  },
  // user bind email
  bindEmail ({ commit }, user) {
    return new Promise((resolve, reject) => {
      bindEmail(user).then(res => {
        commit('SET_USERINFO', res.data)
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
        commit('SET_USERINFO', res.data)
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
