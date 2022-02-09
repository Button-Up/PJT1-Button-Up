// ----------------- 부모 홈 페이지 라우팅 -------------------------
// 헤더 컴포넌트
import GreetingHeader from '../../components/common/headers/GreetingHeader'
import BackHeader from '../../components/common/headers/BackHeader'

// 바텀 네비게이션
import ParentBottomNav from '../../components/parent/BottomNav'

// 페이지
import ParentHome from '../../views/parent/home/Home'
import ChlidInfo from '../../views/parent/home/ChildInfo'

export default [
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
    path: '/parent/home/child-info/:name',
    name: 'ChlidInfo',
    components: {
      default: ChlidInfo,
      header: BackHeader,
      footer: ParentBottomNav
    },
    props:{
      header: {
        backBtnPath: '/parent/home' 
      }
    }
  },
]