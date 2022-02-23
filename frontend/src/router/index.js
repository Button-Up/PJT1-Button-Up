import Vue from "vue";
import VueRouter from "vue-router";

// 공통 라우트 모듈
import common from "./common/common.js";

// 부모 라우트 모듈
import parentHome from "./parent/home";
import parentActivity from "./parent/activity";
import parentUserInfo from "./parent/userInfo";
import parentTutorial from "./parent/tutorial";
import parentRequestList from "./parent/requestList";

// 자녀 라우트 모듈
import childHome from "./child/home";
import childActivity from "./child/activity";
import childUserInfo from "./child/userInfo";
import childShop from "./child/shop";

// vuex store
import store from "@/store/index";

Vue.use(VueRouter);

const routes = [
  ...common,
  ...parentHome,
  ...parentActivity,
  ...parentUserInfo,
  ...parentTutorial,
  ...parentRequestList,
  ...childHome,
  ...childActivity,
  ...childUserInfo,
  ...childShop,
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

/**
 * 페이지 접근시 로그인 여부를 확인하는 코드입니다.
 * 아래 코드를 사용하려면 위에서 vuex store import 하는 코드도 주석을 해제해주세요.
 *
 * 1. 로그인 여부 확인
 * 2. 로그인이 안 되어 있다면 alert 후 로그인 페이지로 이동
 * 3. 로그인이 되어 있다면 정상적으로 이동
 */

router.beforeEach((to, from, next) => {
  store.dispatch("userStore/vuexCheckJWT");
  const isAuthFree = to.matched.some((record) => record.meta.isAuthFree); // 인증이 필요 없는 페이지인지
  const isLogin = store.getters["userStore/checkIsLogin"]; // 로그인이 되어있는지
  const isParent = store.getters["userStore/checkIsParent"]; // 부모 유저인지
  const isParentPage = to.matched.some((record) => record.meta.isParentPage); // 페이지 타입

  if (!isAuthFree && !isLogin) {
    alert("로그인이 필요합니다.");
    next("/login");
  } else if (isAuthFree && isLogin) {
    console.log("이미 로그인 된 유저입니다.");
    next(isParent ? "/parent/home" : "/child/home");
  } else if (!isAuthFree && isParent !== isParentPage) {
    console.log("잘못된 접근입니다.");
    next(isParent ? "/parent/home" : "/child/home");
  } else {
    next();
  }
});

export default router;
