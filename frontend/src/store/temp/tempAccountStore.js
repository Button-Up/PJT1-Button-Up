/* 서버 요청 없이 로그인 처리가 되도록 하는 임시 코드입니다. */

const tempAccountStore = {
  namespaced: true,
  state: {
    isLogin: false,
    inTutorial: true,
    tutorialStep: 1,
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
    },
    ADD_TUTORIAL_STEP(state) {
      ++state.tutorialStep
    },
    END_TUTORIAL(state) {
      state.inTutorial = false
    }
  },
  actions: {
    saveUserInfo({ commit }, userInfo) {
      commit('SET_IS_LOGIN', true),
      commit('SET_USER_INFO', userInfo)
    },
    addTutorialStep({ commit }) {
      commit('ADD_TUTORIAL_STEP')
    },
    endTutorial({ commit }) {
      commit('END_TUTORIAL')
    }
  }
};

export default tempAccountStore