import request from '@/utils/request'

export function getAllCourse (userId) {
  return request({
    url: `v1/students/${userId}/courses`,
    method: 'get'
  })
}

export function joinCourse (joinClazzForm) {
  return request({
    url: 'v1/students/courses',
    method: 'post',
    data: JSON.stringify(joinClazzForm)
  })
}

export function quitCourse (clazzId, userId) {
  return request({
    url: 'v1/students/courses',
    method: 'delete',
    params: {
      clazzId: clazzId,
      userId: userId
    }
  })
}

export function createCourse (clazzForm) {
  return request({
    url: 'v1/courses',
    method: 'post',
    data: JSON.stringify(clazzForm)
  })
}

export function searchCourse (clazzNum) {
  return request({
    url: `v1/courses/${clazzNum}`,
    method: 'get'
  })
}

export function dissolveCourse (clazzId) {
  return request({
    url: `v1/courses/${clazzId}`,
    method: 'delete'
  })
}
