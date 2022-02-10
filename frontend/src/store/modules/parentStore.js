/**
 * @author Jeongyeon Woo
 * created on 2022-02-10
 */

import { childrenList } from "@/api/childrenAPI.js";
import { apiGetTutorialStep, apiPutTutorialStep } from "@/api/parentAPI.js";

const parentStore = {
  namespaced: true,
  state: {
    children: [],
    tutorialStage: null,
  },
  getters: {
    childrenInfo: function (state) {
      return state.children;
    },
    checkTutorialStage: function (state) {
      return state.tutorialStage;
    },
    checkInTutorial: function (state) {
      return state.tutorialStage < 3 ? true : false;
    },
  },
  mutations: {
    SET_CHILDREN: (state, children) => {
      state.children = children;
    },
    SET_TUTORIAL_STAGE: (state, stage) => {
      state.tutorialStage = stage;
    },
  },
  actions: {
    async vuexGetChildren({ commit }, parentSeq) {
      await childrenList(parentSeq, (res) => {
        commit("SET_CHILDREN", res.data);
      });
    },
    async vuexGetTutorialStage({ commit }, parentSeq) {
      await apiGetTutorialStep(
        parentSeq,
        (res) => {
          commit("SET_TUTORIAL_STAGE", res.data.stage);
        },
        (err) => {
          console.log(err);
        }
      );
    },
    async vuexPutTutorialStage(context, tutorialInfo) {
      await apiPutTutorialStep(
        tutorialInfo,
        (res) => {
          console.log(res);
          console.log("튜토리얼 단계 수정 완료");
        },
        (err) => {
          console.log(err);
          console.log(tutorialInfo);
        }
      );
    },
  },
};

export default parentStore;
