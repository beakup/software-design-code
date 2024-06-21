import Vue from 'vue'
import App from './App.vue'
import router from './router'
import ELementUI from 'element-ui'


/*element框架提供页面样式*/
import 'element-ui/lib/theme-chalk/index.css'
import '@/assets/global.css'
/*@表示src这个目录，src是source的意思，源代码*/

Vue.config.productionTip = false
Vue.use(ELementUI,{ size: 'small'});//medium,mini,small

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
