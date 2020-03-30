export default {
  getUserId: state => state.user.userInfo.userId,
  getCourseId: state => state.clazz.course.courseId,
  isTeacher: state => state.user.userInfo.userId === state.clazz.course.teacherId
}
