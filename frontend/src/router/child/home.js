// ----------------- 부모 홈 페이지 라우팅 -------------------------
// 헤더 컴포넌트
import GreetingHeader from "../../components/common/headers/GreetingHeader.vue";
import BackHeader from "../../components/common/headers/BackHeader";

// 바텀 네비게이션
import BottomNav from "../../components/common/BottomNav";
// 홈
import ChildHomeMain from "../../views/child/home/Main.vue";

// 예금 적금 컴포넌트
import ChildAccountHistory from "../../views/child/home/AccountHistory.vue";

export default [
  // 홈
  {
    path: "/child/home",
    name: "ChildHome",
    components: {
      default: ChildHomeMain,
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
    path: "/child/account-history/deposit",
    name: "ChildAccountHistoryDeposit",
    components: {
      default: ChildAccountHistory,
      header: BackHeader,
    },
    props: {
      header: {
        pageTitle: "예금 거래내역",
        bgColor: "child01",
      },
      default: {
        isDeposit: true,
      },
    },
    meta: {
      isParentPage: false,
    },
  },

  // 적금 상세
  {
    path: "/child/account-history/installment",
    name: "ChildAccountHistoryInstallment",
    components: {
      default: ChildAccountHistory,
      header: BackHeader,
    },
    props: {
      header: {
        pageTitle: "적금 거래내역",
        bgColor: "child04",
      },
      default: {
        isDeposit: false,
      },
    },
    meta: {
      isParentPage: false,
    },
  },
];
