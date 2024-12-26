import request from '@/utils/request'

// 查询楼层管理列表
export function listPbuilding(query) {
  return request({
    url: '/pbuild/pbuilding/list',
    method: 'get',
    params: query
  })
}

// 查询楼层管理详细
export function getPbuilding(buildingId) {
  return request({
    url: '/pbuild/pbuilding/' + buildingId,
    method: 'get'
  })
}

// 新增楼层管理
export function addPbuilding(data) {
  return request({
    url: '/pbuild/pbuilding',
    method: 'post',
    data: data
  })
}

// 修改楼层管理
export function updatePbuilding(data) {
  return request({
    url: '/pbuild/pbuilding',
    method: 'put',
    data: data
  })
}

// 删除楼层管理
export function delPbuilding(buildingId) {
  return request({
    url: '/pbuild/pbuilding/' + buildingId,
    method: 'delete'
  })
}
