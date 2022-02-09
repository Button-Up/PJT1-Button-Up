import { login } from "@/api/userAPI.js";
// import jwt_decode from "jwt-decode";
// import { getUserInfo } from "@/api/userAPI.js";

const userStore = {
  namespaced: true,
  state: {
    isLogin: false,
    isLoginError: false,
    userInfo: null,
  },
  getters: {
    checkUserInfo: function (state) {
      return state.userInfo;
    },
  },
  mutations: {
    SET_IS_LOGIN: (state, isLogin) => {
      state.isLogin = isLogin;
    },
    SET_IS_LOGIN_ERROR: (state, isLoginError) => {
      state.isLoginError = isLoginError;
    },
    SET_USER_INFO: (state, userInfo) => {
      state.isLogin = true;
      state.userInfo = userInfo;
    },
  },
  actions: {
    async vuexUserLogin({ commit }, userInfo) {
      await login(userInfo.isParent, userInfo.credentials,
        (res) => {
          let token = res.data;
          commit("SET_IS_LOGIN", true);
          sessionStorage.setItem("access-token", token);
          console.log('로그인 성공!')
        },
        (err) => {
          console.log(err)
          commit("SET_IS_LOGIN_ERROR", true)
          console.log('로그인 실패!')
        });
    },
    // vuexGetUserInfo(context, token) {
    //   let decodeToken = jwt_decode(token);
    //   console.log(decodeToken)
    //   getUserInfo(
    //     decodeToken.userSeq,
    //     (res) => {
    //       if (response.data.message === "success") {
    //         commit("SET_USER_INFO", res.data.userInfo);
    //       } else {
    //         console.log("유저 정보 없음!!");
    //       }
    //     },
    //     (err) => {
    //       console.log(err);
    //     }
    //   );
    // },
  },
};

export default userStore;
