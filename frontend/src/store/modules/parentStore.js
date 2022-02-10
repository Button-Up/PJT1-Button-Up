/**
 * @author Jeongyeon Woo
 * created on 2022-02-10
 */

import { childrenList } from "@/api/childrenAPI.js";

const parentStore = {
  namespaced: true,
  state: {
    children: [],
  },
  getters: {
    childrenInfo: function (state) {
      return state.children;
    },
  },
  mutations: {
    SET_CHILDREN: (state, children) => {
      state.children = children;
    },
  },
  actions: {
    async vuexGetChildren({ commit }, parentSeq) {
      await childrenList(parentSeq, (res) => {
        commit("SET_CHILDREN", res.data);
      });
    },
  },
};

export default parentStore;
