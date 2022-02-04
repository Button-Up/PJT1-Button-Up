// ----------------- 부모 홈 페이지 라우팅 -------------------------
// 헤더 컴포넌트
import GreetingHeader from "../../components/common/headers/GreetingHeader";
import InstallmentSavingHeader from "../../components/child/home/headers/InstallmentSavingHeader";
import SavingHeader from "../../components/child/home/headers/SavingHeader";

// 바텀 네비게이션
import ChildBottomNav from "../../components/child/BottomNav";

// 페이지
import Main from "../../views/child/Main";

// 홈
import ChildHome from "../../components/child/home/Home.vue";

// 예금 적금 컴포넌트
import ChildSaving from "../../components/child/home/Saving";
import ChildInstallmentSaving from "../../components/child/home/InstallmentSaving";

export default [
  {
    path: "/child",
    name: "Main",
    components: {
      Main,
      default: ChildHome,
      header: GreetingHeader,
      footer: ChildBottomNav,
    },

    children: [
      // 홈
      {
        path: "home",
        name: "ChildHome",
        components: {
          default: ChildHome,
          header: GreetingHeader,
          footer: ChildBottomNav,
        },
      },

      // 예금 상세
      {
        path: "saving",
        name: "ChildSaving",
        components: {
          default: ChildSaving,
          header: SavingHeader,
        },
      },

      // 적금 상세
      {
        path: "installmentsaving",
        name: "ChildInstallmentSaving",
        component: {
          default: ChildInstallmentSaving,
          header: InstallmentSavingHeader,
        },
      },
    ],
  },
];
