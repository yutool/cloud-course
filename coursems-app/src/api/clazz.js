import { request } from '@/utils/request'

export function getClazzDetail (clazzId) {
  return request({
    url: `v1/classes/${clazzId}`,
    method: 'get'
  })
}

export function deleteMember (clazzId, userId) {
  return request({
    url: 'v1/classes/members',
    method: 'delete',
    params: {
      clazzId: clazzId,
      userId: userId
    }
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

export function uploadResource (resourceForm) {
  return request({
    url: 'v1/classes/resources',
    method: 'post',
    data: JSON.stringify(resourceForm)
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

export function enableAppraise (clazzDetail) {
  return request({
    url: 'v1/classes/enableAppraise',
    method: 'put',
    data: JSON.stringify(clazzDetail)
  })
}
