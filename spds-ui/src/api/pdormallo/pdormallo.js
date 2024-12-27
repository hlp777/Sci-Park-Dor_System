import request from '@/utils/request'

// 查询宿舍分配列表
export function listPdormallo(query) {
  return request({
    url: '/pdormallo/pdormallo/list',
    method: 'get',
    params: query
  })
}

// 查询宿舍分配详细
export function getPdormallo(id) {
  return request({
    url: '/pdormallo/pdormallo/' + id,
    method: 'get'
  })
}

// 新增宿舍分配
export function addPdormallo(data) {
  return request({
    url: '/pdormallo/pdormallo',
    method: 'post',
    data: data
  })
}

// 修改宿舍分配
export function updatePdormallo(data) {
  return request({
    url: '/pdormallo/pdormallo',
    method: 'put',
    data: data
  })
}

// 删除宿舍分配
export function delPdormallo(id) {
  return request({
    url: '/pdormallo/pdormallo/' + id,
    method: 'delete'
  })
}
