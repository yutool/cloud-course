import request from '@/utils/request'

export function registerEmail (toAddr) {
  return request({
    url: `v1/email/register/${toAddr}`,
    method: 'get'
  })
}

export function updateEmail (toAddr) {
  return request({
    url: `v1/email/update/${toAddr}`,
    method: 'get'
  })
}

export function forgetEmail (toAddr) {
  return request({
    url: `v1/email/forget/${toAddr}`,
    method: 'get'
  })
}
