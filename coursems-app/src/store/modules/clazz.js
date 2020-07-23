import { getClazzDetail, deleteMember, uploadResource,
  deleteResource, sendNotice, deleteNotice,
  gradeStudent } from '@/api/clazz'
import { toggleAppraise, uploadPhoto } from '@/api/course'

const state = {
  course: {},
  members: [],
  resources: [],
  notices: [],
  selectedMember: {}
}

const mutations = {
  SET_COURSE (state, course) {
    state.course = course
  },
  SET_MEMBERS (state, members) {
    state.members = members
  },
  SET_RESOURCES (state, resources) {
    state.resources = resources
  },
  SET_NOTICES (state, notices) {
    state.notices = notices
  },
  SET_SELECTED_MEMBER (state, member) {
    state.selectedMember = member
  },
  REMOVE_MEMBER (state, index) {
    state.members.splice(index, 1)
  },
  ADD_RESOURCE (state, resource) {
    state.resources.splice(0, 0, resource)
  },
  REMOVE_RESOURCE (state, index) {
    state.resources.splice(index, 1)
  },
  ADD_NOTICE (state, notice) {
    state.notices.splice(0, 0, notice)
  },
  REMOVE_NOTICE (state, index) {
    state.notices.splice(index, 1)
  },
  SET_APPRAISE (state, boolean) {
    state.course.appraise = boolean
  },
  SET_MEMBER (state, index, member) {
    state.notices.splice(index, 1, member)
  },
  SET_COURSE_PHOTO (state, path) {
    state.course.coursePic = path
  }
}

const actions = {
  // get clazz detail
  getClazz ({ commit }, clazzId) {
    getClazzDetail(clazzId).then(res => {
      console.log(res)
      const { members, resources, notices } = res.data
      commit('SET_COURSE', res.data)
      commit('SET_MEMBERS', members)
      commit('SET_RESOURCES', resources)
      commit('SET_NOTICES', notices)
      commit('SET_SELECTED_MEMBER', members[0])
    })
  },
  // set selected member
  setSelectedMember ({ commit }, member) {
    commit('SET_SELECTED_MEMBER', member)
  },
  // delete selected member
  deleteMember ({ commit, state }, member) {
    const tmp = state.members.filter(item => item.userId === member.userId)[0]
    return new Promise((resolve, reject) => {
      deleteMember(tmp.id).then(res => {
        for (let i = 0; i < state.members.length; i++) {
          if (state.members[i] === member) {
            commit('REMOVE_MEMBER', i)
            commit('SET_SELECTED_MEMBER', state.members[0])
            break
          }
        }
        resolve(res)
      }).catch(error => {
        reject(error)
      })
    })
  },
  // upload resource
  uploadResource ({ commit }, resource) {
    return new Promise((resolve, reject) => {
      uploadResource(resource).then(res => {
        commit('ADD_RESOURCE', res.data)
        resolve(res)
      }).catch(error => {
        reject(error)
      })
    })
  },
  // delete resource
  deleteResource ({ commit, state }, resource) {
    return new Promise((resolve, reject) => {
      deleteResource(resource.resId).then(res => {
        for (let i = 0; i < state.resources.length; i++) {
          if (resource === state.resources[i]) {
            commit('REMOVE_RESOURCE', i)
            break
          }
        }
        resolve(res)
      }).catch(error => {
        reject(error)
      })
    })
  },
  // send notice
  sendNotice ({ commit }, notice) {
    return new Promise((resolve, reject) => {
      sendNotice(notice).then(res => { // 发送通知
        commit('ADD_NOTICE', res.data)
        resolve(res)
      }).catch(error => {
        reject(error)
      })
    })
  },
  // delete notice
  deleteNotice ({ commit, state }, notice) {
    return new Promise((resolve, reject) => {
      deleteNotice(notice.noticeId).then(res => {
        for (let i = 0; i < state.notices.length; i++) {
          if (notice === state.notices[i]) {
            commit('REMOVE_NOTICE', i)
            break
          }
        }
        resolve(res)
      }).catch(error => {
        reject(error)
      })
    })
  },
  // open class grading
  toggleAppraise ({ commit }, id) {
    return new Promise((resolve, reject) => {
      toggleAppraise(id).then(res => {
        commit('SET_APPRAISE', res.data)
        resolve(res)
      }).catch(error => {
        reject(error)
      })
    })
  },
  // grade a student
  grade ({ commit, state }, member) {
    return new Promise((resolve, reject) => {
      gradeStudent(member).then(res => {
        for (let i = 0; i < state.members.length; i++) {
          if (member === state.members[i]) {
            commit('SET_MEMBER', i, res.data)
            break
          }
        }
        resolve(res)
      }).catch(error => {
        reject(error)
      })
    })
  },
  // upload clazz photo
  uploadPhoto ({ commit }, {id, data}) {
    return new Promise((resolve, reject) => {
      uploadPhoto(id, data).then(res => {
        commit('SET_COURSE_PHOTO', res.data)
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
