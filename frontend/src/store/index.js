import Vue from "vue";
import Vuex from "vuex";

import tempAccountStore from "./temp/tempAccountStore";
import userStore from "./modules/userStore";
import accountStore from "./modules/accountStore";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {},
  mutations: {},
  actions: {},
  modules: {
    tempAccountStore,
    userStore,
    accountStore,
  },
});
