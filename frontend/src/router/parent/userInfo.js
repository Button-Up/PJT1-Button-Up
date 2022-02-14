// ----------------- 부모 내 정보 페이지 라우팅 -------------------------
// 바텀 네비게이션
import BottomNav from "../../components/common/BottomNav";

// 페이지
import UserInfo from "../../views/common/UserInfo";

export default [
  {
    path: "/parent/userinfo",
    name: "ParentUserInfo",
    components: {
      default: UserInfo,
      footer: BottomNav,
    },
    meta: {
      isParentPage: true,
    },
  },
];
