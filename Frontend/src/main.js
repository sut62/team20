import Vue from 'vue'
import App from './App.vue'
import VueLogger from 'vuejs-logger';
import router from './router'
import BootstrapVue from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import LightTimeline from 'vue-light-timeline';

Vue.config.productionTip = false

const options = {
  isEnabled: true,
  logLevel : 'debug',
  stringifyArguments : false,
  showLogLevel : true,
  showMethodName : false,
  separator: '|',
  showConsoleColors: true
};

Vue.use(VueLogger, options);
Vue.use(BootstrapVue);
Vue.use(LightTimeline);

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
