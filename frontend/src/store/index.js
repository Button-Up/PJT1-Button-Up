import Vue from 'vue'
import Vuex from 'vuex'
import createPersistedState from 'vuex-persistedstate'

import userStore from './modules/userStore'
import accountStore from './modules/accountStore'

Vue.use(Vuex);

export default new Vuex.Store({
  modules: {
    userStore,
    accountStore
  },
  plugins: [
    createPersistedState()
  ]
})
