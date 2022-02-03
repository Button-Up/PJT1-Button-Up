// ----------------- 부모 활동관리 페이지 라우팅 -------------------------
// 헤더 컴포넌트
import GreetingHeader from '../../components/common/headers/GreetingHeader'

//바텀 네비게이션
import ParentBottomNav from '../../components/parent/BottomNav'

// 페이지
import ParentActivity from '../../views/parent/Activity'


export default [
  {
    path: '/parent/activity',
    name: 'ParentActivity',
    components: {
      default: ParentActivity,
      header: GreetingHeader,
      footer: ParentBottomNav
    }
  },
]