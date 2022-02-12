// ----------------- 부모 홈 페이지 라우팅 -------------------------
// 헤더 컴포넌트
import GreetingHeader from "../../components/common/headers/GreetingHeader";
import BackHeader from "../../components/common/headers/BackHeader";

// 바텀 네비게이션
import BottomNav from "../../components/common/BottomNav";

// 홈
import ChildActivity from "../../views/child/Activity/Main.vue";

// 경제 개념 사전 컴포넌트
import ChildStudy from "../../views/child/Activity/Study.vue";

// 적금 컴포넌트
import ChildSaving from "../../views/child/Activity/Saving.vue";

// 투자 컴포넌트
import ChildInvest from "../../views/child/Activity/Invest.vue";

export default [
  {
    path: "/child/activity",
    name: "ChildActivity",
    components: {
      default: ChildActivity,
      header: GreetingHeader,
      footer: BottomNav,
    },
    props: {
      footer: {
        isParent: false,
      },
    },
    meta: {
      isParentPage: false,
    },
  },

  // 예금 상세
  {
    path: "/child/activity/study",
    name: "ChildStudy",
    components: {
      default: ChildStudy,
      header: BackHeader,
      footer: BottomNav,
    },
    props: {
      header: {
        pageTitle: "경제 개념 사전",
      },
      footer: {
        isParent: false,
      },
    },
    meta: {
      isParentPage: false,
    },
  },
  {
    path: "/child/activity/saving",
    name: "ChildSaving",
    components: {
      default: ChildSaving,
      header: BackHeader,
      footer: BottomNav,
    },
    props: {
      header: {
        pageTitle: "적금 통장 개설",
      },
      footer: {
        isParent: false,
      },
    },
    meta: {
      isParentPage: false,
    },
  },
  {
    path: "/child/activity/invest",
    name: "ChildInvest",
    components: {
      default: ChildInvest,
      header: BackHeader,
      footer: BottomNav,
    },
    props: {
      header: {
        pageTitle: "투자",
      },
      footer: {
        isParent: false,
      },
    },
    meta: {
      isParentPage: false,
    },
  },
];
