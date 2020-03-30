import request from '@/utils/request'

export function visitorsLogin () {
  return request({
    url: 'v1/login/visitor',
    method: 'post'
  })
}

export function login (loginForm) {
  return request({
    url: 'v1/login',
    method: 'post',
    data: JSON.stringify(loginForm)
  })
}

export function logout () {
  return request({
    url: 'logout',
    method: 'post'
  })
}

export function register (registerForm) {
  return request({
    url: 'v1/register',
    method: 'post',
    data: JSON.stringify(registerForm)
  })
}

export function forgetPassWord (userForm) {
  return request({
    url: 'v1/reset-password',
    method: 'put',
    data: JSON.stringify(userForm)
  })
}
