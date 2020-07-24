import { getCourses } from '@/api/course'

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
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}
