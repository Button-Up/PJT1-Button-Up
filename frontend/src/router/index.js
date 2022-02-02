import Vue from 'vue'
import VueRouter from 'vue-router'

// -------------------------- 공통 ------------------------------------
// 페이지
import Index from '../views/common/Index'
import Login from '../views/common/Login'

// 컴포넌트
import IndexHeader from '../components/common/headers/IndexHeader'
import GreetingHeader from '../components/common/headers/GreetingHeader'
import BackHeader from '../components/common/headers/BackHeader'


// -------------------------- 부모 ------------------------------------
// 페이지
import ParentSignup from '../views/parent/Signup'
import ParentHome from '../views/parent/Home'
import ParentActivity from '../views/parent/Activity'
import ParentUserInfo from '../views/parent/UserInfo'

// 컴포넌트
import ParentBottomNav from '../components/parent/BottomNav'
import ParentSignupNotice1 from '../components/parent/signup/Notice1'
import ParentSignupNotice2 from '../components/parent/signup/Notice2'
import ParentSignupChildrenNumForm from '../components/parent/signup/ChildrenNumForm'
import ParentSignupSyncCodeForm from '../components/parent/signup/SyncCodeForm'
import ParentSignupTypeSelect from '../components/parent/signup/TypeSelect'
import ParentSignupForm from '../components/parent/signup/SignupForm'


// -------------------------- 자녀 ------------------------------------
// 페이지
import ChildSignup from '../views/child/Signup'
import ChildHome from '../views/child/Home'
import ChildActivity from '../views/child/Activity'
import ChildShop from '../views/child/Shop'

// 컴포넌트
import ChildSignupForm from '../components/child/signup/SignupForm'
import ChildSyncCode from '../components/child/signup/SyncCode'
import ChildBottomNav from '../components/child/BottomNav'

Vue.use(VueRouter)

const routes = [
  // -------------------------- 공통 ------------------------------------
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
      header: GreetingHeader
    }
  },

  // -------------------------- 부모 ------------------------------------
  {
    path: '/parent/signup',
    name: 'ParentSignup',
    components: {
      default: ParentSignup,
      header: BackHeader
    },
    props: {
      header: { pageTitle: '부모님 회원가입' }
    },
    children: [
      {
        path: 'notice1',
        name: 'ParentSignupNotice1',
        component: ParentSignupNotice1,
      },
      {
        path: 'notice2',
        name: 'ParentSignupNotice2',
        component: ParentSignupNotice2,
      },
      {
        path: 'children-num-form',
        name: 'ParentSignupChildrenNumForm',
        component: ParentSignupChildrenNumForm,
      },
      {
        path: 'sync-code-form',
        name: 'ParentSignupSyncCodeForm',
        component: ParentSignupSyncCodeForm,
        props: true
      },
      {
        path: 'type-select',
        name: 'ParentSignupTypeSelect',
        component: ParentSignupTypeSelect,
      },
      {
        path: 'form',
        name: 'ParentSignupForm',
        component: ParentSignupForm,
      },
    ]
  },
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
  
  // -------------------------- 자녀 ------------------------------------
  {
    path: '/child/signup',
    name: 'ChildSignup',
    components: {
      default: ChildSignup,
      header: BackHeader
    },
    props: {
      header: { pageTitle: '자녀 회원가입'}
    },
    children: [
      {
        path: 'form',
        name: 'ChildSignupForm',
        component: ChildSignupForm
      },
      {
        path: 'sync-code',
        name: 'ChildSyncCode',
        component: ChildSyncCode
      },
    ]
  },
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
