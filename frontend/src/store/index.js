import Vue from "vue";
import Vuex from "vuex";
import createPersistedState from "vuex-persistedstate";

import userStore from "./modules/userStore";
import accountStore from "./modules/accountStore";
import savingStore from "./modules/savingStore";
import parentStore from "./modules/parentStore";
import investStore from "./modules/investStore";

Vue.use(Vuex);

export default new Vuex.Store({
  modules: {
    userStore,
    accountStore,
    savingStore,
    parentStore,
    investStore,
  },
  plugins: [createPersistedState()],
});
