// ----------------- 아이 홈 페이지 라우팅 -------------------------
// 헤더 컴포넌트
import BackHeader from '../../components/common/headers/BackHeader'

//페이지
import ChildSignup from '../../views/child/Signup'

// 회원가입 컴포넌트
import ChildSignupForm from '../../components/child/signup/SignupForm'
import ChildSyncCode from '../../components/child/signup/SyncCode'


export default [
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
]