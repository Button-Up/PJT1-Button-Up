// ----------------- 부모 환전/결제요청 내역 페이지 라우팅 -------------------------
// 헤더 컴포넌트
import BackHeader from '../../components/common/headers/BackHeader'

// 바텀 네비게이션
import ParentBottomNav from '../../components/parent/BottomNav'

// 페이지
import ParentRequestList from '../../views/parent/RequestList'

export default [
  {
    path: '/parent/request-list',
    components: {
      default: ParentRequestList,
      header: BackHeader,
      footer: ParentBottomNav
    },
    props: {
      header: {
        pageTitle: '환전/결제 요청 내역',
        backBtnPath: '/parent/home'
      },
    },
    meta: {
      isParentPage: true
    }
  }
]