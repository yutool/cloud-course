import types from './mutation-types'
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
  clearState ({ commit }) { // 清除状态
    commit(types.SET_USER_INFO, null)
    commit(types.SET_USER_COURSE, null)
    commit(types.SET_CLAZZ_DETAIL, null)
  }
}
