import request from "@/utils/request";

const api_name = "/user";

export default {
  getPageList(page, limit, searchObj) {
    return request({
      url: `${api_name}/${page}/${limit}`,
      method: "get",
      params: searchObj
    });
  },
  addUser(data) {
    return request({
      url: `${api_name}/save`,
      method: "post",
      data: data
    });
  },
  lock(id) {
    return request({
      url: `${api_name}/delete/${id}`,
      method: "delete"
    });
  },
  // 用户详情
  show(id) {
    return request({
      url: `${api_name}/show/${id}`,
      method: "get"
    });
  },
  // 认证审批
  approval(id, authStatus) {
    return request({
      url: `${api_name}/approval/${id}/${authStatus}`,
      method: "get"
    });
  }
};
