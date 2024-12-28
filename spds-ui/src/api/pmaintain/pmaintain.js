import request from '@/utils/request'

// 查询维修管理列表
export function listPmaintain(query) {
  return request({
    url: '/pmaintain/pmaintain/list',
    method: 'get',
    params: query
  })
}

// 查询维修管理详细
export function getPmaintain(id) {
  return request({
    url: '/pmaintain/pmaintain/' + id,
    method: 'get'
  })
}

// 新增维修管理
export function addPmaintain(data) {
  return request({
    url: '/pmaintain/pmaintain',
    method: 'post',
    data: data
  })
}

// 修改维修管理
export function updatePmaintain(data) {
  return request({
    url: '/pmaintain/pmaintain',
    method: 'put',
    data: data
  })
}

// 删除维修管理
export function delPmaintain(id) {
  return request({
    url: '/pmaintain/pmaintain/' + id,
    method: 'delete'
  })
}
