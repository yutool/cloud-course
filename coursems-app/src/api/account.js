import { request } from './request'
import api from './url'

export function login (loginForm) {
  return request({
    url: api.url + '/login',
    method: 'post',
    data: JSON.stringify(loginForm)
  })
}

export function register (registerForm) {
  return request({
    url: api.url + '/register',
    method: 'post',
    data: JSON.stringify(registerForm)
  })
}

export function resetPassWord (userForm) {
  return request({
    url: api.url + '/resetPassWord',
    method: 'put',
    data: JSON.stringify(userForm)
  })
}

export function updateUserInfo (userInfo) {
  return request({
    url: api.url + '/userInfo',
    method: 'put',
    data: JSON.stringify(userInfo)
  })
}

export function bindEmail (emailForm) {
  return request({
    url: api.url + '/bindEmail',
    method: 'put',
    data: JSON.stringify(emailForm)
  })
}

export function bindPhone (phoneForm) {
  return request({
    url: api.url + '/bindPhone',
    method: 'put',
    data: JSON.stringify(phoneForm)
  })
}
