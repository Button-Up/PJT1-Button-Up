import { apiLogin } from "@/api/userAPI.js";
import { apiGetUserInfo } from "../../api/userAPI";

const userStore = {
  namespaced: true,
  state: {
    isLogin: false,
    isParent: null,
    userInfo: null,
    userSeq: null,
  },
  getters: {
    checkIsLogin: function (state) {
      return state.isLogin;
    },
    checkUserInfo: function (state) {
      return state.userInfo;
    },
    checkIsParent: function (state) {
      return state.isParent;
    },
    checkNotSync: function (state) {
      return state.userInfo.parentSeq === -1 ? true : false;
    },
  },
  mutations: {
    INIT_STATE: (state) => {
      state.isLogin = false;
      state.isParent = null;
      state.userInfo = null;
      state.userSeq = null;
    },
    SET_IS_PARENT: (state, isParent) => {
      state.isParent = isParent;
    },
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
    SET_USER_SEQ: (state, userSeq) => {
      state.userSeq = userSeq;
    },
  },
  actions: {
    async vuexLogin({ commit, dispatch }, loginInfo) {
      await apiLogin(
        loginInfo.isParent,
        loginInfo.credentials,
        (res) => {
          commit("SET_USER_SEQ", res.data.seq);
          commit("SET_IS_PARENT", loginInfo.isParent);
          sessionStorage.setItem("access-token", res.data.token);
          dispatch("vuexCheckJWT");
          console.log("로그인 성공!");
        },
        (err) => {
          console.log(err);
          console.log("로그인 실패!");
        }
      );
    },
    vuexLogout({ commit }) {
      sessionStorage.removeItem("access-token");
      commit("INIT_STATE");
    },
    async vuexGetUserInfo({ state, commit }, loginInfo) {
      await apiGetUserInfo(
        loginInfo.isParent,
        state.userSeq,
        (res) => {
          commit("SET_USER_INFO", res.data);
          console.log("유저 정보 저장 완료!");
        },
        (err) => {
          console.log(err);
        }
      );
    },
    vuexCheckJWT({ commit }) {
      const JWT = sessionStorage.getItem("access-token");
      JWT ? commit("SET_IS_LOGIN", true) : commit("INIT_STATE");
    },
  },
};

export default userStore;
