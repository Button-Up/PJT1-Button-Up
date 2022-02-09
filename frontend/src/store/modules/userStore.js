import { apiLogin } from "@/api/userAPI.js";
import { apiGetUserInfo } from "../../api/userAPI";


const userStore = {
  namespaced: true,
  state: {
    isLogin: false,
    isLoginError: false,
    userInfo: null,
    userSeq: null
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
    SET_USER_SEQ: (state, userSeq) => {
      state.userSeq = userSeq
    }
  },
  actions: {
    async vuexLogin({ commit }, loginInfo) {
      await apiLogin(loginInfo.isParent, loginInfo.credentials,
        (res) => {
          commit("SET_USER_SEQ", res.data.seq)
          commit("SET_IS_LOGIN", true);
          sessionStorage.setItem("access-token", res.data.token);
          console.log('로그인 성공!')
        },
        (err) => {
          console.log(err)
          commit("SET_IS_LOGIN_ERROR", true)
          console.log('로그인 실패!')
        });
    },
    vuexGetUserInfo({ state, commit }, loginInfo) {
      apiGetUserInfo(loginInfo.isParent, state.userSeq,
        (res) => {
          commit('SET_USER_INFO', res.data)
          console.log('유저 정보 저장 완료!')
        },
        (err) => {
          console.log(err)
        })
    },
  },
};

export default userStore;
