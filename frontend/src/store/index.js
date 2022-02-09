import Vue from 'vue'
import Vuex from 'vuex'
import createPersistedState from 'vuex-persistedstate'

import userStore from './modules/userStore'

Vue.use(Vuex)

export default new Vuex.Store({
  modules: {
    userStore
  },
  plugins: [
    createPersistedState()
  ]
})
