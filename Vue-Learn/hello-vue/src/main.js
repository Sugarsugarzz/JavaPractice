import Vue from 'vue'
import App from './App'
import router from './router'
import Element from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import axios from 'axios';
import VueAxios from 'vue-axios'

Vue.use(VueAxios, axios);
Vue.use(router);
Vue.use(Element);

new Vue({
  el: '#app',
  router,
  render: h => h(App)  // ElementUI
})
