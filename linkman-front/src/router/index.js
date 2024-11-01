import Vue from "vue";
import Router from "vue-router";

// in development-env not use lazy-loading, because lazy-loading too many pages will cause webpack hot update too slow. so only in production use lazy-loading;
// detail: https://panjiachen.github.io/vue-element-admin-site/#/lazy-loading

Vue.use(Router);

/* Layout */
import Layout from "../views/layout/Layout";

/**
* hidden: true                   if `hidden:true` will not show in the sidebar(default is false)
* alwaysShow: true               if set true, will always show the root menu, whatever its child routes length
*                                if not set alwaysShow, only more than one route under the children
*                                it will becomes nested mode, otherwise not show the root menu
* redirect: noredirect           if `redirect:noredirect` will no redirect in the breadcrumb
* name:'router-name'             the name is used by <keep-alive> (must set!!!)
* meta : {
    title: 'title'               the name show in submenu and breadcrumb (recommend set)
    icon: 'svg-name'             the icon show in the sidebar,
  }
**/
export const constantRouterMap = [
  {
    path: "/login",
    component: () => import("@/views/login/index"),
    hidden: true
  },
  { path: "/404", component: () => import("@/views/404"), hidden: true },

  {
    path: "/",
    component: Layout,
    redirect: "/dashboard",
    name: "Dashboard",
    hidden: true,
    children: [
      {
        path: "dashboard",
        component: () => import("@/views/dashboard/index")
      }
    ]
  },

  // {
  //   path: "/hospSet",
  //   component: Layout,
  //   redirect: "/hospSet/list",
  //   name: "备件管理",
  //   meta: { title: "备件管理  ", icon: "example" },
  //   children: [
  //     {
  //       path: "list",
  //       name: "医院设置列表",
  //       component: () => import("@/views/hospset/list"),
  //       meta: { title: "医院设置列表", icon: "table" }
  //     },
  //     {
  //       path: "add",
  //       name: "医院设置添加",
  //       component: () => import("@/views/hospset/add"),
  //       meta: { title: "医院设置添加", icon: "tree" }
  //     },
  //     {
  //       path: "edit/:id",
  //       name: "医院设置修改",
  //       component: () => import("@/views/hospset/add"),
  //       meta: { title: "编辑", noCache: true },
  //       hidden: true
  //     },
  //     {
  //       path: "hosp/list",
  //       name: "医院列表",
  //       component: () => import("@/views/hosp/list"),
  //       meta: { title: "医院列表", icon: "table" }
  //     },
  //     {
  //       path: "hospital/show/:id",
  //       name: "查看",
  //       component: () => import("@/views/hosp/show"),
  //       meta: { title: "查看", noCache: true },
  //       hidden: true
  //     },
  //     {
  //       path: "hospital/schedule/:hoscode",
  //       name: "排班",
  //       component: () => import("@/views/hosp/schedule"),
  //       meta: { title: "排班", noCache: true },
  //       hidden: true
  //     }
  //   ]
  // },
  {
    path: "/user",
    component: Layout,
    redirect: "/user/userInfo/list",
    name: "userInfo",
    meta: { title: "联系管理", icon: "table" },
    alwaysShow: true,
    children: [
      {
        path: "userInfo/list",
        name: "联系人列表",
        component: () => import("@/views/user/userInfo/list"),
        meta: { title: "联系人列表", icon: "table" }
      },
      {
        path: "userInfo/show/:id",
        name: "用户查看",
        component: () => import("@/views/user/userInfo/show"),
        meta: { title: "用户查看" },
        hidden: true
      }
    ]
  },
  // {
  //   path: "/order",
  //   component: Layout,
  //   redirect: "/order/list",
  //   name: "BasesInfo",
  //   meta: { title: "报修管理", icon: "table" },
  //   alwaysShow: true,
  //   children: [
  //     {
  //       path: "ist",
  //       name: "报修列表",
  //       component: () => import("@/views/order/list"),
  //       meta: { title: "报修列表" }
  //     },
  //     {
  //       path: "orderInfo/show/:id",
  //       name: "查看",
  //       component: () => import("@/views/order/show"),
  //       meta: { title: "查看", noCache: true },
  //       hidden: true
  //     }
  //   ]
  // },

  // {
  //   path: "/cmn",
  //   component: Layout,
  //   redirect: "/cmn/list",
  //   name: "维修管理",
  //   alwaysShow: true,
  //   meta: { title: "维修列表", icon: "example" },
  //   children: [
  //     {
  //       path: "list",
  //       name: "维修列表",
  //       component: () => import("@/views/dict/list"),
  //       meta: { title: "维修列表", icon: "table" }
  //     }
  //   ]
  // },

  { path: "*", redirect: "/404", hidden: true }
];

export default new Router({
  // mode: 'history', //后端支持可开
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRouterMap
});
