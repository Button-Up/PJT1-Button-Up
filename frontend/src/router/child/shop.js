// ----------------- 부모 홈 페이지 라우팅 -------------------------
// 헤더 컴포넌트
import GreetingHeader from "../../components/common/headers/GreetingHeader";

// 바텀 네비게이션
import BottomNav from "../../components/common/BottomNav";
// 페이지
import ChildShop from "../../views/child/Shop";

export default [
  {
    path: "/child/shop",
    name: "ChildShop",
    components: {
      default: ChildShop,
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
];
