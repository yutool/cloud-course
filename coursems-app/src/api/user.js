import request from '@/utils/request'

export function getCurrentUser () {
  return request({
    url: 'v1/users/current',
    method: 'get'
  })
}

export function resetPassWord (userForm) {
  return request({
    url: 'v1/users/password',
    method: 'put',
    data: JSON.stringify(userForm)
  })
}

export function updateUserInfo (userInfo) {
  return request({
    url: 'v1/users',
    method: 'put',
    data: JSON.stringify(userInfo)
  })
}

export function bindEmail (emailForm) {
  return request({
    url: 'v1/users/email',
    method: 'put',
    data: JSON.stringify(emailForm)
  })
}

export function bindPhone (phoneForm) {
  return request({
    url: 'v1/users/phone',
    method: 'put',
    data: JSON.stringify(phoneForm)
  })
}
