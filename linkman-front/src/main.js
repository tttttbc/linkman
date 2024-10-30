import Vue from 'vue'

import 'normalize.css/normalize.css' // A modern alternative to CSS resets

import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import locale from 'element-ui/lib/locale/lang/en' // lang i18n

import '@/styles/index.scss' // global css
import '@/styles/show.css'

import App from './App'
import router from './router'
import store from './store'

import '@/icons' // icon
import '@/permission' // permission control
import zhLocale from 'element-ui/lib/locale/lang/zh-CN'

Vue.use(ElementUI, { locale: zhLocale })
zhLocale.el.pagination = {
  goto: '跳至',
  pagesize: '条/页',
  total: `共计 {total} 条`,
  pageClassifier: '页'
}

Vue.config.productionTip = false

new Vue({
  el: '#app',
  router,
  store,
  render: h => h(App)
})
