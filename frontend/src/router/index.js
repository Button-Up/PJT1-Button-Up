import Vue from 'vue'
import VueRouter from 'vue-router'

// 공통 컴포넌트
import Index from '../views/Index'
import IndexHeader from '../components/headers/IndexHeader'
import GreetingHeader from '../components/headers/GreetingHeader'
import Login from '../views/account/Login'

// 부모 컴포넌트
import ParentBottomNav from '../components/footers/ParentBottomNav'
import ParentHome from '../views/parent/Home'
import ParentActivity from '../views/parent/Activity'
import ParentUserInfo from '../views/parent/UserInfo'

// 자녀 컴포넌트
import ChildHome from '../views/child/Home'
import ChildActivity from '../views/child/Activity'
import ChildShop from '../views/child/Shop'
import ChildBottomNav from '../components/footers/ChildBottomNav'

Vue.use(VueRouter)

const routes = [
  // 공통
  {
    path: '/',
    name: 'Index',
    components: {
      default: Index,
      header: IndexHeader
    }
  },
  {
    path: '/login',
    name: 'Login',
    components: {
      default: Login,
    }
  },

  // 부모
  {
    path: '/parent/home',
    name: 'ParentHome',
    components: {
      default: ParentHome,
      header: GreetingHeader,
      footer: ParentBottomNav
    }
  },
  {
    path: '/parent/activity',
    name: 'ParentActivity',
    components: {
      default: ParentActivity,
      header: GreetingHeader,
      footer: ParentBottomNav
    }
  },
  {
    path: '/parent/userinfo',
    name: 'ParentUserInfo',
    components: {
      default: ParentUserInfo,
      header: GreetingHeader,
      footer: ParentBottomNav
    }
  },
  
  // 자녀
  {
    path: '/child/home',
    name: 'ChildHome',
    components: {
      default: ChildHome,
      header: GreetingHeader,
      footer: ChildBottomNav
    }
  },
  {
    path: '/child/activity',
    name: 'ChildActivity',
    components: {
      default: ChildActivity,
      header: GreetingHeader,
      footer: ChildBottomNav
    }
  },
  {
    path: '/child/shop',
    name: 'ChildShop',
    components: {
      default: ChildShop,
      header: GreetingHeader,
      footer: ChildBottomNav
    }
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
