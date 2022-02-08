// ----------------- 부모 회원가입 라우팅 -------------------------
// 헤더 컴포넌트
import BackHeader from '../../components/common/headers/BackHeader'

// 페이지
import ParentSignup from '../../views/parent/Signup'

// 회원가입 컴포넌트
import ParentSignupNotice1 from '../../components/parent/signup/Notice1'
import ParentSignupNotice2 from '../../components/parent/signup/Notice2'
import ParentSignupChildrenNumForm from '../../components/parent/signup/ChildrenNumForm'
import ParentSignupSyncCodeForm from '../../components/parent/signup/SyncCodeForm'
import ParentSignupTypeSelect from '../../components/parent/signup/TypeSelect'
import ParentSignupForm from '../../components/parent/signup/SignupForm'


export default [
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
]