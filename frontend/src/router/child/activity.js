// ----------------- 부모 홈 페이지 라우팅 -------------------------
// 헤더 컴포넌트
import GreetingHeader from '../../components/common/headers/GreetingHeader'

// 바텀 네비게이션
import ChildBottomNav from '../../components/child/BottomNav'

// 페이지
import ChildActivity from '../../views/child/Activity'



export default [
  {
    path: '/child/activity',
    name: 'ChildActivity',
    components: {
      default: ChildActivity,
      header: GreetingHeader,
      footer: ChildBottomNav
    },
    meta: {
      isParentPage: false
    }
  },
]