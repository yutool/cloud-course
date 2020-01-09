import { request } from './request'
import api from './url'

export function getAllCourse (userId) {
  return request({
    url: api.url + '/allCourse',
    method: 'get',
    params: {userId: userId}
  })
}

export function createCourse (clazzForm) {
  return request({
    url: api.url + '/createCourse',
    method: 'post',
    data: JSON.stringify(clazzForm)
  })
}

export function searchCourse (clazzNum) {
  return request({
    url: api.url + '/searchCourse',
    method: 'get',
    params: { clazzNum: clazzNum }
  })
}

export function joinCourse (joinClazzForm) {
  return request({
    url: api.url + '/joinCourse',
    method: 'post',
    data: JSON.stringify(joinClazzForm)
  })
}

export function quitCourse (clazzId, userId) {
  return request({
    url: api.url + '/quitCourse',
    method: 'delete',
    params: {
      clazzId: clazzId,
      userId: userId
    }
  })
}

export function dissolveCourse (clazzId) {
  return request({
    url: api.url + '/dissolveCourse',
    method: 'delete',
    params: {clazzId: clazzId}
  })
}
