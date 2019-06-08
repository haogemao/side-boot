// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router/index'
import axios from './router/http' 
import store from './store/store'
import VueMaterial from 'vue-material'
import $ from 'jquery'
import qs from 'qs'
//import '../static/css/bootstrap/bootstrap.min.css'
import '../static/js/bootstrap/bootstrap.js'
import '../static/js/template/js/jquery.min.js'

import VueAlertify from 'vue-alertify'

Vue.use(VueAlertify);
Vue.use(VueMaterial);

//Vue.prototype.HOST='/api'
Vue.prototype.axios = axios;
Vue.prototype.router = router;
Vue.prototype.store = store;

Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  axios,
  router,
  store,
  render: h => h(App)
}).$mount('#app');

