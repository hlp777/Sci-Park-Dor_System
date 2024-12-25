import request from '@/utils/request'

// 查询用户管理列表
export function listPuser(query) {
  return request({
    url: '/puser/puser/list',
    method: 'get',
    params: query
  })
}

// 查询用户管理详细
export function getPuser(id) {
  return request({
    url: '/puser/puser/' + id,
    method: 'get'
  })
}

// 新增用户管理
export function addPuser(data) {
  return request({
    url: '/puser/puser',
    method: 'post',
    data: data
  })
}

// 修改用户管理
export function updatePuser(data) {
  return request({
    url: '/puser/puser',
    method: 'put',
    data: data
  })
}

// 删除用户管理
export function delPuser(id) {
  return request({
    url: '/puser/puser/' + id,
    method: 'delete'
  })
}
