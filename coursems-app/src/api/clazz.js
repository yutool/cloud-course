import { request } from '@/utils/request'

export function getClazzDetail (clazzId) {
  return request({
    url: '/clazzDetail',
    method: 'get',
    params: { clazzId: clazzId }
  })
}

export function deleteMember (clazzId, userId) {
  return request({
    url: '/clazzMember',
    method: 'delete',
    params: {
      clazzId: clazzId,
      userId: userId
    }
  })
}

export function sendNotice (messageForm) {
  return request({
    url: '/sendNotice',
    method: 'post',
    data: JSON.stringify(messageForm)
  })
}

export function deleteNotice (noticeId) {
  return request({
    url: '/deleteNotice',
    method: 'delete',
    params: { noticeId: noticeId }
  })
}

export function uploadResource (resourceForm) {
  return request({
    url: '/uploadResource',
    method: 'post',
    data: JSON.stringify(resourceForm)
  })
}

export function deleteResource (resId) {
  return request({
    url: '/deleteResource',
    method: 'delete',
    params: { resId: resId }
  })
}

export function gradeStudent (scoreForm) {
  return request({
    url: '/gradeStudent',
    method: 'put',
    data: JSON.stringify(scoreForm)
  })
}

export function enableAppraise (clazzDetail) {
  return request({
    url: '/enableAppraise',
    method: 'put',
    data: JSON.stringify(clazzDetail)
  })
}
