// -----------------------------------부모 & 자녀 유저가 모두 접근하는 페이지의 routing ------------------------------------
// 페이지
import Index from '../../views/common/Index'
import Login from '../../views/common/Login'

// 헤더 컴포넌트
import IndexHeader from '../../components/common/headers/IndexHeader'
import GreetingHeader from '../../components/common/headers/GreetingHeader'

export default [
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
]