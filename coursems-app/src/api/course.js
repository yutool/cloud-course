import { request } from '@/utils/request'

export function getAllCourse (userId) {
  return request({
    url: '/allCourse',
    method: 'get',
    params: {userId: userId}
  })
}

export function createCourse (clazzForm) {
  return request({
    url: '/createCourse',
    method: 'post',
    data: JSON.stringify(clazzForm)
  })
}

export function searchCourse (clazzNum) {
  return request({
    url: '/searchCourse',
    method: 'get',
    params: { clazzNum: clazzNum }
  })
}

export function joinCourse (joinClazzForm) {
  return request({
    url: '/joinCourse',
    method: 'post',
    data: JSON.stringify(joinClazzForm)
  })
}

export function quitCourse (clazzId, userId) {
  return request({
    url: '/quitCourse',
    method: 'delete',
    params: {
      clazzId: clazzId,
      userId: userId
    }
  })
}

export function dissolveCourse (clazzId) {
  return request({
    url: '/dissolveCourse',
    method: 'delete',
    params: {clazzId: clazzId}
  })
}
