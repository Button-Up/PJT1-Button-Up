// -----------------------------------부모 & 자녀 유저가 모두 접근하는 페이지의 routing ------------------------------------
// 페이지
import Index from "../../views/common/Index";
import Login from "../../views/common/Login";
import Signup from "../../views/common/Signup";

// 헤더 컴포넌트
import IndexHeader from "../../components/common/headers/IndexHeader";
import GreetingHeader from "../../components/common/headers/GreetingHeader";
import BackHeader from "../../components/common/headers/BackHeader";

export default [
  {
    path: "/",
    name: "Index",
    components: {
      default: Index,
      header: IndexHeader,
    },
    meta: { isAuthFree: true },
  },
  {
    path: "/login",
    name: "Login",
    components: {
      default: Login,
      header: GreetingHeader,
    },
    meta: { isAuthFree: true },
  },
  {
    path: "/parent/signup",
    name: "ParentSignup",
    components: {
      default: Signup,
      header: BackHeader,
    },
    meta: { isAuthFree: true },
    props: {
      default: { isParent: true },
      header: { pageTitle: "부모님 회원가입" },
    },
  },
  {
    path: "/child/signup",
    name: "ChildSignup",
    components: {
      default: Signup,
      header: BackHeader,
    },
    meta: {
      isAuthFree: true,
      isParentPage: false,
    },
    props: {
      default: { isParent: false },
      header: { pageTitle: "자녀 회원가입" },
    },
  },
];
