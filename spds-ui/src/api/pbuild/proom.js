import request from '@/utils/request'

// 查询房间管理列表
export function listProom(query) {
  return request({
    url: '/pbuild/proom/list',
    method: 'get',
    params: query
  })
}

// 查询房间管理详细
export function getProom(roomId) {
  return request({
    url: '/pbuild/proom/' + roomId,
    method: 'get'
  })
}

// 新增房间管理
export function addProom(data) {
  return request({
    url: '/pbuild/proom',
    method: 'post',
    data: data
  })
}

// 修改房间管理
export function updateProom(data) {
  return request({
    url: '/pbuild/proom',
    method: 'put',
    data: data
  })
}

// 删除房间管理
export function delProom(roomId) {
  return request({
    url: '/pbuild/proom/' + roomId,
    method: 'delete'
  })
}
