import request from '@/utils/request'

export function getCourses (userId) {
  return request({
    url: `v1/${userId}/courses`,
    method: 'get'
  })
}

export function createCourse (clazzForm) {
  return request({
    url: 'v1/courses',
    method: 'post',
    data: JSON.stringify(clazzForm)
  })
}

export function toggleAppraise (id) {
  return request({
    url: `v1/courses/appraise/${id}`,
    method: 'put'
  })
}

export function searchCourse (clazzNum) {
  return request({
    url: `v1/courses/${clazzNum}`,
    method: 'get'
  })
}

export function uploadPhoto (id, data) {
  return request({
    url: `v1/courses/photo/${id}`,
    header: {
      'Content-Type': 'multipart/form-data'
    },
    method: 'post',
    data
  })
}
