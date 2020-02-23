import request from '@/utils/request'

export function getClazzDetail (clazzId) {
  return request({
    url: `v1/classes/${clazzId}`,
    method: 'get'
  })
}

export function addMember (member) {
  return request({
    url: 'v1/classes/members',
    method: 'post',
    data: JSON.stringify(member)
  })
}

export function deleteMember (member) {
  return request({
    url: 'v1/classes/members',
    method: 'delete',
    data: JSON.stringify(member)
  })
}

export function sendNotice (messageForm) {
  return request({
    url: 'v1/classes/notices',
    method: 'post',
    data: JSON.stringify(messageForm)
  })
}

export function deleteNotice (noticeId) {
  return request({
    url: `v1/classes/notices/${noticeId}`,
    method: 'delete'
  })
}

export function uploadResource (data) {
  return request({
    url: 'v1/classes/resources',
    header: {
      'Content-Type': 'multipart/form-data'
    },
    method: 'post',
    data
  })
}

export function deleteResource (resId) {
  return request({
    url: `v1/classes/resources/${resId}`,
    method: 'delete'
  })
}

export function gradeStudent (scoreForm) {
  return request({
    url: 'v1/classes/grades',
    method: 'put',
    data: JSON.stringify(scoreForm)
  })
}

export function dissolveCourse (clazzId) {
  return request({
    url: `v1/classes/${clazzId}`,
    method: 'delete'
  })
}
