/*
 * author : 유현수
 * modified : 정은이 - JobManager , AddJob
 */

// ----------------- 부모 활동관리 페이지 라우팅 -------------------------
// 헤더 컴포넌트
import GreetingHeader from "../../components/common/headers/GreetingHeader";
import BackHeader from "../../components/common/headers/BackHeader";

//바텀 네비게이션
import BottomNav from "../../components/common/BottomNav";

// 페이지
import Main from "../../views/parent/activity/Main";
import JobManager from "../../views/parent/activity/JobManage";
import AddJob from "../../views/parent/activity/AddJob.vue";

export default [
  {
    path: "/parent/activity",
    name: "ParentActivity",
    components: {
      default: Main,
      header: GreetingHeader,
      footer: BottomNav,
    },
    meta: {
      isParentPage: true,
    },
  },
  {
    path: "/parent/activity/job",
    name: "JobManager",
    components: {
      default: JobManager,
      header: BackHeader,
      footer: BottomNav,
    },
    props: {
      header: {
        pageTitle: "직업관리",
        backBtnPath: "/parent/activity",
      },
    },
    meta: {
      isParentPage: true,
    },
  },
  {
    path: "/parent/activity/job/add",
    name: "AddJob",
    components: {
      default: AddJob,
      header: BackHeader,
      footer: BottomNav,
    },
    props: {
      header: {
        pageTitle: "직업추가",
        backBtnPath: "/parent/activity/job",
      },
    },
    meta: {
      isParentPage: true,
    },
  },
];
