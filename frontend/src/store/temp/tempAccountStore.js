/* 서버 요청 없이 로그인 처리가 되도록 하는 임시 코드입니다. */

const tempAccountStore = {
  namespaced: true,
  state: {
    isLogin: false,
    userInfo: null
  },
  getters: {
  },
  mutations: {
    SET_IS_LOGIN(state, isLogin) {
      state.isLogin = isLogin
    },
    SET_USER_INFO(state, userInfo) {
      state.userInfo = userInfo
    }
  },
  actions: {
    saveUserInfo({ commit }, userInfo) {
      commit('SET_IS_LOGIN', true),
      commit('SET_USER_INFO', userInfo)
    }
  }
};

export default tempAccountStore