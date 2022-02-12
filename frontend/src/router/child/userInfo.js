import BottomNav from "../../components/common/BottomNav";
import UserInfo from "../../views/common/UserInfo";

export default [
  {
    path: "/child/userinfo",
    name: "ChildUserInfo",
    components: {
      default: UserInfo,
      footer: BottomNav,
    },
    meta: {
      isParentPage: false,
    },
    props: {
      default: {
        isParent: false,
      },
      footer: {
        isParent: false,
      },
    },
  },
];
