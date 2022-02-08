import jwt_decode from "jwt-decode";
import { login } from "@/api/userAPI.js";
import { userInfo } from "@/api/userAPI.js";

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
    async userLogin({ commit }, user) {
      await login(user, (res) => {
        if (res.data.message === "success") {
          let token = res.data["access-token"];
          commit("SET_IS_LOGIN", true);
          commit("SET_IS_LOGIN_ERROR", false);
          sessionStorage.setItem("access-token", token);
        } else {
          commit("SET_IS_LOGIN", false);
          commit("SET_IS_LOGIN_ERROR", true);
        }
      });
    },
    getUserInfo({ commit }, token) {
      let decode_token = jwt_decode(token);
      userInfo(
        decode_token.userSeq,
        (res) => {
          if (response.data.message === "success") {
            commit("SET_USER_INFO", res.data.userInfo);
          } else {
            console.log("유저 정보 없음!!");
          }
        },
        (err) => {
          console.log(err);
        }
      );
    },
  },
};

export default userStore;
