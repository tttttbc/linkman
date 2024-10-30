import request from '@/utils/request'
export default {
  // 医院列表
  getHospList(page, limit, searchObj) {
    return request({
      url: `/admin/hosp/hospital/list/${page}/${limit}`,
      method: 'get',
      params: searchObj
    })
  },
  // 根据dictcode查询所有子节点
  findByDictCode(dictCode) {
    return request({
      url: `/admin/cmn/dict/findByDictCode/${dictCode}`,
      method: 'get'
    })
  },
  // 根据数据id查询子数据列表
  findChildId(id) {
    return request({
      url: `/admin/cmn/dict/findChildDate/${id}`,
      method: 'get'
    })
  },
  // 更新医院状态
  updateHospStatus(id, status) {
    return request({
      url: `/admin/hosp/hospital/updateHospStatus`,
      method: 'post',
      data: { id: id, status: status }

    })
  },
  // 查看医院详情
  getHospById(id) {
    return request({
      url: `/admin/hosp/hospital/showHospDetail/${id}`,
      method: 'get'

    })
  },
  // 查看医院科室
  getDeptByHoscode(hoscode) {
    return request({
      url: `/admin/hosp/department/getDeptList/${hoscode}`,
      method: 'get'

    })
  },
  // 查询预约规则
  getScheduleRule(page, limit, hoscode, depcode) {
    return request({
      url: `/admin/hosp/schedule/getScheduleRule/${page}/${limit}/${hoscode}/${depcode}`,
      method: 'get'

    })
  },
  // 查询排班详情信息
  getScheduleDetail(hoscode, depcode, workDate) {
    return request({
      url: `/admin/hosp/schedule/getScheduleDetail/${hoscode}/${depcode}/${workDate}`,
      method: 'get'

    })
  }
}
