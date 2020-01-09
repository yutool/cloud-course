import types from './mutation-types'
export default {
  // 用户
  [types.SET_USER_INFO] (state, userInfo) {
    state.userInfo = userInfo
  },
  [types.SET_USER_EMAIL] (state, email) {
    state.userInfo.email = email
  },
  [types.SET_USER_PHONENUM] (state, phoneNum) {
    state.userInfo.phoneNum = phoneNum
  },
  [types.SET_USER_PWD] (state, userPwd) {
    state.userInfo.userPwd = userPwd
  },
  // 课程
  [types.SET_USER_COURSE] (state, userCourse) {
    state.userCourse = userCourse
  },
  // 班级
  [types.SET_CLAZZ_DETAIL] (state, clazzDetail) {
    state.clazzDetail = clazzDetail
  },
  [types.REMOVE_CLAZZ_MEMBER] (state, index) {
    state.clazzDetail.members.splice(index, 1)
  },
  [types.ADD_CLAZZ_RESOURCE] (state, resource) {
    state.clazzDetail.resources.splice(0, 0, resource)
  },
  [types.REMOVE_CLAZZ_RESOURCE] (state, index) {
    state.clazzDetail.resources.splice(index, 1)
  },
  [types.ADD_CLAZZ_NOTICE] (state, notice) {
    state.clazzDetail.notices.splice(0, 0, notice)
  },
  [types.REMOVE_CLAZZ_NOTICE] (state, index) {
    state.clazzDetail.notices.splice(index, 1)
  },
  [types.SET_CLAZZ_APPRAISE] (state, enAppraise) {
    state.clazzDetail.enAppraise = enAppraise
  },
  [types.SET_SELECTED_MEMBER] (state, member) {
    state.selectedMember = member
  }
}
