import Vue from 'vue'
import Router from 'vue-router'
import pageLayout from './components/Layout/pageLayout'
import loginPage from './views/Login'
import Dashboard from './views/Dashboard'

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
      ]
    },
    {
      path: '/login',
      component: loginPage
    }
  ]
})
