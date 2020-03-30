import request from '@/utils/request'

export function getCurrentUser () {
  return request({
    url: 'v1/users/current',
    method: 'get'
  })
}

export function updateUserInfo (userInfo) {
  return request({
    url: 'v1/users',
    method: 'put',
    data: JSON.stringify(userInfo)
  })
}

export function uploadAvatar (id, data) {
  return request({
    url: `v1/users/avatar/${id}`,
    header: {
      'Content-Type': 'multipart/form-data'
    },
    method: 'post',
    data
  })
}

export function bindEmail (user) {
  return request({
    url: `v1/users/email`,
    method: 'put',
    data: JSON.stringify(user)
  })
}

export function bindPhone (user) {
  return request({
    url: `v1/users/phone`,
    method: 'put',
    data: JSON.stringify(user)
  })
}

export function resetPassWord (userForm) {
  return request({
    url: 'v1/users/password',
    method: 'put',
    data: JSON.stringify(userForm)
  })
}
