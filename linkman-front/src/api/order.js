import request from "@/utils/request";
const api_name = "/baoXiu";
export default {
  getPageList(page, limit, searchObj) {
    return request({
      url: `${api_name}/${page}/${limit}`,
      method: "get",
      params: searchObj
    });
  },

  getById(id) {
    return request({
      url: `${api_name}/show/${id}`,
      method: "get"
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
  }
};
