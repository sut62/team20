import Vue from 'vue'
import Router from 'vue-router'
import pageLayout from './components/Layout/pageLayout'
import loginPage from './views/Login'
import Dashboard from './views/Dashboard'
import ShippingState from './views/ShippingState System/ShippingState'
import Cancelsent from './views/Cancelsent System/Cancelsent'
import SentParcel from './views/SentParcel System/SentParcel'
import HistorySentParcel from './views/SentParcel System/HistorySentParcel'
import Packaging from './views/Packaging System/Packaging'
import ConfirmPackage from './views/ConfirmPackage System/ConfirmPackage'
import MemberCustomer from './views/MemberCustomer System/MemberCustomer'
import DisplayShippingState from './views/ShippingState System/DisplayShippingState'
import DisplayMemberCustomer from './views/MemberCustomer System/DisplayMemberCustomer'
import DisplayPackagePayment from './views/Packaging System/DisplayPackagePayment'
import EnterCodeToFindPackagePayment from './views/Packaging System/EnterCodeToFindPackagePayment'
import ShowConfirmPackage from './views/ConfirmPackage System/ShowConfirmPackage'
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
          path: '/displayshippingstate',
          name: 'displayshippingstate',
          component: DisplayShippingState
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
        {
          path: '/historysentparcel',
          name: 'historysentparcel',
          component: HistorySentParcel
        },
        {
          path: '/packaging',
          name: 'packaging',
          component: Packaging
        },
        {
          path: '/confirmpackage',
          name: 'confirmpackage',
          component: ConfirmPackage
        },
        {
          path: '/membercustomer',
          name: 'membercustomer',
          component: MemberCustomer
        },
        {
          path: '/displaymembercustomer',
          name: 'displaymembercustomer',
          component: DisplayMemberCustomer
        },
        {
          path: '/displaypackagepayment',
          name: 'displaypackagepayment',
          component: DisplayPackagePayment
        },
        {
          path: '/entercodetofindpackagepayment',
          name: 'entercodetofindpackagepayment',
          component: EnterCodeToFindPackagePayment
        },
        {
          path: '/showconfirmpackage',
          name: 'showconfirmpackage',
          component: ShowConfirmPackage
        }

      ]
    },
    {
      path: '/login',
      component: loginPage
    }
  ]
})