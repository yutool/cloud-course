export default {
  getUserInfo: state => state.userInfo,
  getUserId: state => state.userInfo.userId,
  getUserCourse: state => state.userCourse,
  getClazzDetail: state => state.clazzDetail,
  getClazzId: state => state.clazzDetail.clazzId,
  getClazzMember: state => state.clazzDetail.members,
  getClazzNotice: state => state.clazzDetail.notices,
  getClazzResource: state => state.clazzDetail.resources,
  getIsTeacher: state => state.userInfo.userId === state.clazzDetail.teaId
}
