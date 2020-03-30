import router from '@/router'
import { getCourses, createCourse } from '@/api/course'

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
      const { data } = res
      if (data != null) {
        commit('SET_JOIN_COURSES', data.filter(c => c.teacherId !== userId))
        commit('SET_CREATE_COURSES', data.filter(c => c.teacherId === userId))
      }
    })
  },
  createCourse ({ commit }, course) {
    return new Promise((resolve, reject) => {
      createCourse(course).then(res => {
        commit('ADD_CREATE_COURSE', res.data)
        router.push('/course')
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
