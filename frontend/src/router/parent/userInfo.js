// ----------------- 부모 내 정보 페이지 라우팅 -------------------------
// 헤더 컴포넌트
import GreetingHeader from '../../components/common/headers/GreetingHeader'

// 바텀 네비게이션
import ParentBottomNav from '../../components/parent/BottomNav'

// 페이지
import ParentUserInfo from '../../views/parent/UserInfo'


export default [
  {
    path: '/parent/userinfo',
    name: 'ParentUserInfo',
    components: {
      default: ParentUserInfo,
      header: GreetingHeader,
      footer: ParentBottomNav
    }
  },
]