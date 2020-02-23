import router from '@/router'
import { getCourses, createCourse } from '@/api/course'
import { addMember } from '@/api/clazz'

const state = {
  joinCourses: [],
  createCourses: []
}

const mutations = {
  SET_JOIN_COURSES (state, courses) {
    state.joinCourses = courses
  },
  SET_CREATE_COURSES (state, courses) {
    state.createCourses = courses
  },
  ADD_JOIN_COURSE (state, course) {
    state.joinCourses.splice(0, 0, course)
  },
  ADD_CREATE_COURSE (state, course) {
    state.createCourses.splice(0, 0, course)
  }
}

const actions = {
  getCourses ({ commit }, userId) {
    getCourses(userId).then(res => {
      const { code, data } = res
      console.log('返回的班级', res)
      if (code === 0 && data != null) {
        commit('SET_JOIN_COURSES', data.filter(c => c.teacherId !== userId))
        commit('SET_CREATE_COURSES', data.filter(c => c.teacherId === userId))
      }
    })
  },
  joinCourse ({ commit }, member) {
    addMember(member).then(res => {
      const { code, data } = res
      if (code === 0) { // 班级创建成功
        commit('ADD_JOIN_COURSE', data)
        router.push('/course')
      }
    })
  },
  createCourse ({ commit }, course) {
    createCourse(course).then(res => {
      const { code, data } = res
      if (code === 0) { // 班级创建成功
        commit('ADD_CREATE_COURSE', data)
        router.push('/course')
      }
    })
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}
