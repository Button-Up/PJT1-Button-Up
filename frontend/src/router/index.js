import Vue from 'vue'
import VueRouter from 'vue-router'

// 공통 라우트 모듈
import common from './common/common.js'

// 부모 라우트 모듈
import parentSignup from './parent/signup'
import parentHome from './parent/home'
import parentActivity from './parent/activity'
import parentUserInfo from './parent/userInfo'

// 자녀 라우트 모듈
import childSignup from './child/signup'
import childHome from './child/home'
import childActivity from './child/activity'
import childShop from './child/shop'

Vue.use(VueRouter)

const routes = [
  ...common,
  ...parentSignup,
  ...parentHome,
  ...parentActivity,
  ...parentUserInfo,
  ...childSignup,
  ...childHome,
  ...childActivity,
  ...childShop
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
