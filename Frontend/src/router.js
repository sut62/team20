import Vue from 'vue'
import Router from 'vue-router'
import pageLayout from './components/Layout/pageLayout'
import loginPage from './views/Login'
import Dashboard from './views/Dashboard'
import ShippingState from './views/ShippingState System/ShippingState'
import Cancelsent from './views/Cancelsent System/Cancelsent'
import SentParcel from './views/SentParcel System/SentParcel'
Vue.use(Router)

export default new Router({
  linkExactActiveClass: 'active',
  routes: [
    {
      path: '/',
      redirect: 'dashboard',
      component: pageLayout,
      children: [
        {
          path: '/dashboard',
          name: 'dashboard',
          component: Dashboard
        },
        {
          path: '/shippingstate',
          name: 'shippingstate',
          component: ShippingState
        },
        {
          path: '/cancelsent',
          name: 'cancelsent',
          component: Cancelsent
        },
        {
          path: '/sentparcel',
          name: 'sentparcel',
          component: SentParcel
        },
      ]
    },
    {
      path: '/login',
      component: loginPage
    }
  ]
})
