import { request } from '@/utils/request'

export function login (loginForm) {
  return request({
    url: '/login',
    method: 'post',
    data: JSON.stringify(loginForm)
  })
}

export function register (registerForm) {
  return request({
    url: '/register',
    method: 'post',
    data: JSON.stringify(registerForm)
  })
}

export function resetPassWord (userForm) {
  return request({
    url: '/resetPassWord',
    method: 'put',
    data: JSON.stringify(userForm)
  })
}

export function updateUserInfo (userInfo) {
  return request({
    url: '/userInfo',
    method: 'put',
    data: JSON.stringify(userInfo)
  })
}

export function bindEmail (emailForm) {
  return request({
    url: '/bindEmail',
    method: 'put',
    data: JSON.stringify(emailForm)
  })
}

export function bindPhone (phoneForm) {
  return request({
    url: '/bindPhone',
    method: 'put',
    data: JSON.stringify(phoneForm)
  })
}
