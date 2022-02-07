// ----------------- 부모 홈 페이지 라우팅 -------------------------
// 헤더 컴포넌트
import GreetingHeader from "../../components/common/headers/GreetingHeader.vue";
import BackHeader from "../../components/common/headers/BackHeader";

// 바텀 네비게이션
import ChildBottomNav from "../../components/child/BottomNav.vue";

// 홈
import ChildHome from "../../views/child/Home.vue";

// 예금 적금 컴포넌트
import ChildSaving from "../../components/child/Saving.vue";
import ChildInstallmentSaving from "../../components/child/InstallmentSaving.vue";

export default [
  // 홈
  {
    path: "/child/home",
    name: "ChildHome",
    components: {
      default: ChildHome,
      header: GreetingHeader,
      footer: ChildBottomNav,
    },
  },

  // 예금 상세
  {
    path: "/child/saving",
    name: "ChildSaving",
    components: {
      default: ChildSaving,
      header: BackHeader,
    },
    props: {
      header: {
        pageTitle: "예금 거래내역",
        bgColor: "child01"
      },
    },
  },

  // 적금 상세
  {
    path: "/child/installmentsaving",
    name: "ChildInstallmentSaving",
    components: {
      default: ChildInstallmentSaving,
      header: BackHeader,
    },
    props: {
      header: { pageTitle: "적금 상세" },
    },
  },
];
