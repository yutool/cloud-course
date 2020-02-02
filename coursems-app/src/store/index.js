import Vue from 'vue'
import Vuex from 'vuex'
import persistedState from 'vuex-persistedstate'
import state from './state'
import getters from './getters'
import mutations from './mutations'
import actions from './actions'
Vue.use(Vuex)

export default new Vuex.Store({
  plugins: [persistedState({ storage: window.sessionStorage })], // 同一页面可刷新
  // plugins: [createPersistedState()], // 同一浏览器可多页面
  state,
  getters,
  mutations,
  actions
})
