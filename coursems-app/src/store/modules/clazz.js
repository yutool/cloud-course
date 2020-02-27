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
  SET_COURSE_PHOTO (state, photo) {
    state.course.clazzPhoto = photo
  }
}

const actions = {
  // get clazz detail
  getClazz ({ commit }, clazzId) {
    getClazzDetail(clazzId).then(res => {
      const { members, resources, notices } = res.data
      console.log('查询班级', res)
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
    deleteMember(member).then(res => {
      if (res.code === 0) {
        for (let i = 0; i < state.members.length; i++) {
          if (state.members[i] === member) {
            commit('REMOVE_MEMBER', i)
            break
          }
        }
        commit('SET_SELECTED_MEMBER', state.members[0])
      }
    })
  },
  // upload resource
  uploadResource ({ commit }, resource) {
    uploadResource(resource).then(res => {
      if (res.code === 0) {
        commit('ADD_RESOURCE', res.data)
      }
    })
  },
  // delete resource
  deleteResource ({ commit, state }, resource) {
    deleteResource(resource.resId).then(res => {
      if (res.code === 0) {
        for (let i = 0; i < state.resources.length; i++) {
          if (resource === state.resources[i]) {
            commit('REMOVE_RESOURCE', i)
            break
          }
        }
      }
    })
  },
  // send notice
  sendNotice ({ commit }, notice) {
    sendNotice(notice).then(res => { // 发送通知
      if (res.code === 0) {
        commit('ADD_NOTICE', res.data)
      }
    })
  },
  // delete notice
  deleteNotice ({ commit, state }, notice) {
    deleteNotice(notice.noticeId).then(res => {
      if (res.code === 0) {
        for (let i = 0; i < state.notices.length; i++) {
          if (notice === state.notices[i]) {
            commit('REMOVE_NOTICE', i)
            break
          }
        }
      }
    })
  },
  // open class grading
  toggleAppraise ({ commit }, id) {
    toggleAppraise(id).then(res => {
      const { code, data } = res
      if (code === 0) {
        commit('SET_APPRAISE', data)
      }
    })
  },
  // grade a student
  grade ({ commit, state }, member) {
    gradeStudent(member).then(res => {
      const { code, data } = res
      if (code === 0) {
        for (let i = 0; i < state.members.length; i++) {
          if (member === state.members[i]) {
            commit('SET_MEMBER', i, data)
            break
          }
        }
      }
    })
  },
  // upload clazz photo
  uploadPhoto ({ commit }, {id, data}) {
    uploadPhoto(id, data).then(res => {
      const { code, data } = res
      if (code === 0) {
        commit('SET_COURSE_PHOTO', data)
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
