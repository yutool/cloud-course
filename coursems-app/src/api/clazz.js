import request from '@/utils/request'

export function getClazzDetail (clazzId) {
  return request({
    url: `v1/classes/${clazzId}`,
    method: 'get'
  })
}

export function addMember (member) {
  return request({
    url: 'v1/members',
    method: 'post',
    data: JSON.stringify(member)
  })
}

export function deleteMember (member) {
  return request({
    url: 'v1/members',
    method: 'delete',
    data: JSON.stringify(member)
  })
}

export function sendNotice (messageForm) {
  return request({
    url: 'v1/notices',
    method: 'post',
    data: JSON.stringify(messageForm)
  })
}

export function deleteNotice (noticeId) {
  return request({
    url: `v1/notices/${noticeId}`,
    method: 'delete'
  })
}

export function uploadResource (data) {
  return request({
    url: 'v1/resources',
    header: {
      'Content-Type': 'multipart/form-data'
    },
    method: 'post',
    data
  })
}

export function deleteResource (resId) {
  return request({
    url: `v1/resources/${resId}`,
    method: 'delete'
  })
}

export function gradeStudent (scoreForm) {
  return request({
    url: 'v1/members/grade',
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
