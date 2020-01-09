import { request } from './request'
import api from './url'

export function getClazzDetail (clazzId) {
  return request({
    url: api.url + '/clazzDetail',
    method: 'get',
    params: { clazzId: clazzId }
  })
}

export function deleteMember (clazzId, userId) {
  return request({
    url: api.url + '/clazzMember',
    method: 'delete',
    params: {
      clazzId: clazzId,
      userId: userId
    }
  })
}

export function sendNotice (messageForm) {
  return request({
    url: api.url + '/sendNotice',
    method: 'post',
    data: JSON.stringify(messageForm)
  })
}

export function deleteNotice (noticeId) {
  return request({
    url: api.url + '/deleteNotice',
    method: 'delete',
    params: { noticeId: noticeId }
  })
}

export function uploadResource (resourceForm) {
  return request({
    url: api.url + '/uploadResource',
    method: 'post',
    data: JSON.stringify(resourceForm)
  })
}

export function deleteResource (resId) {
  return request({
    url: api.url + '/deleteResource',
    method: 'delete',
    params: { resId: resId }
  })
}

export function gradeStudent (scoreForm) {
  return request({
    url: api.url + '/gradeStudent',
    method: 'put',
    data: JSON.stringify(scoreForm)
  })
}

export function enableAppraise (clazzDetail) {
  return request({
    url: api.url + '/enableAppraise',
    method: 'put',
    data: JSON.stringify(clazzDetail)
  })
}
