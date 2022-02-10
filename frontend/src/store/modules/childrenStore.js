import { childrenList } from "@/api/childrenAPI.js";

const childrenStore = {
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

export default childrenStore;
