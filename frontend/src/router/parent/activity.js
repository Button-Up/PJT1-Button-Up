/*
* author : 유현수
* modified : 정은이 - JobManager , AddJob
*/

// ----------------- 부모 활동관리 페이지 라우팅 -------------------------
// 헤더 컴포넌트
import GreetingHeader from '../../components/common/headers/GreetingHeader'
import BackHeader from '../../components/common/headers/BackHeader'

//바텀 네비게이션
import ParentBottomNav from '../../components/parent/BottomNav'

// 페이지
import ParentActivity from '../../views/parent/Activity'
import JobManager from '../../components/parent/activity/JobManage'
import AddJob from '../../views/parent/AddJob.vue'

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
  {
    path: '/parent/activity/job',
    name: 'JobManager',
    components: {
      default: JobManager,
      header: BackHeader,
      footer: ParentBottomNav
    },
    props: {
      header: {
        pageTitle: '직업관리',
        backBtnPath: '/parent/activity'
      },
    }
  },
  {
    path: '/parent/activity/job/add',
    name: 'AddJob',
    components: {
      default: AddJob,
      header: BackHeader,
      footer: ParentBottomNav
    },
    props: {
      header: {
        pageTitle: '직업추가',
        backBtnPath: '/parent/activity/job'
      },
    }
  },
]