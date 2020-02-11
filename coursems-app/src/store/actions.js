import types from './mutation-types'
import { getCurrentUser } from '@/api/user'
import { getAllCourse } from '@/api/course'
import { getClazzDetail } from '@/api/clazz'

export default {
  // 用户信息
  setUserInfo: ({ commit }, userInfo) => commit(types.SET_USER_INFO, userInfo),
  setUserEmail: ({ commit }, email) => commit(types.SET_USER_EMAIL, email),
  setUserPhoneNum: ({ commit }, phoneNum) => commit(types.SET_USER_PHONENUM, phoneNum),
  setUserPwd: ({ commit }, userPwd) => commit(types.SET_USER_PWD, userPwd),
  // 课程
  setUserCourse: ({ commit }, userCourse) => commit(types.SET_USER_COURSE, userCourse),
  // 班级
  setClazzDetail: ({ commit }, clazzDetail) => commit(types.SET_CLAZZ_DETAIL, clazzDetail),
  removeClazzMember: ({ commit }, index) => commit(types.REMOVE_CLAZZ_MEMBER, index),
  addClazzResource: ({ commit }, resource) => commit(types.ADD_CLAZZ_RESOURCE, resource),
  removeClazzResource: ({ commit }, index) => commit(types.REMOVE_CLAZZ_RESOURCE, index),
  addClazzNotice: ({ commit }, notice) => commit(types.ADD_CLAZZ_NOTICE, notice),
  removeClazzNotice: ({ commit }, index) => commit(types.REMOVE_CLAZZ_NOTICE, index),
  setClazzAppraise: ({ commit }, enAppraise) => commit(types.SET_CLAZZ_APPRAISE, enAppraise),
  setSelectedMember: ({ commit }, member) => commit(types.SET_SELECTED_MEMBER, member),
  setFullscreenLoading: ({ commit }, loading) => commit(types.SET_FULLSCREEN_LOADING, loading),
  getCurrentUser ({ commit }) { // 获取当前用户
    return new Promise((resolve, reject) => {
      getCurrentUser().then(res => {
        if (res.code === 0) {
          commit(types.SET_USER_INFO, res.data)
        } else {
          commit(types.SET_USER_INFO, null)
          commit(types.SET_USER_COURSE, null)
          commit(types.SET_CLAZZ_DETAIL, null)
        }
        resolve(res)
      }).catch(error => {
        commit(types.SET_USER_INFO, null)
        commit(types.SET_USER_COURSE, null)
        commit(types.SET_CLAZZ_DETAIL, null)
        reject(error)
      })
    })
  },
  getAllCourse ({ commit, state }) {
    getAllCourse(state.userInfo.userId).then(res => {
      if (res.code === 0) {
        commit(types.SET_USER_COURSE, res.data)
      }
    })
  },
  getClazz ({ commit }, clazzId) {
    getClazzDetail(clazzId).then(res => {
      commit(types.SET_CLAZZ_DETAIL, res.data)
      commit(types.SET_SELECTED_MEMBER, res.data.members[0])
    })
  },
  clearState ({ commit }) { // 清除状态
    commit(types.SET_USER_INFO, null)
    commit(types.SET_USER_COURSE, null)
    commit(types.SET_CLAZZ_DETAIL, null)
  }
}
