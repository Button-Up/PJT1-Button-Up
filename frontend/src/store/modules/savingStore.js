/**
 * @author Hyeonsoo Ryu
 * created on 2022-02-16
 */

import {
  apiGetSavingsBalance,
  apiCloseSavings,
  apiGetSavingsDetails,
  apiPostAddSavingsHistory,
  apiPostOpenSavings,
} from "../../api/savingsAPI";

const savingStore = {
  namespaced: true,
  state: {
    isSavingExist: false,
    savingAccountDetail: {},
    balance: 0,
  },
  getters: {
    getSavingStatus(state) {
      return state.isSavingExist;
    },
    getBalance(state) {
      return state.balance;
    },
    getAccountHistory(state) {
      return state.accountHistory;
    },
    getSavingAccountDetail(state) {
      return state.savingAccountDetail;
    },
  },
  mutations: {
    SET_SAVING_BALANCE(state, balance) {
      state.balance = balance;
    },
    SET_IS_SAVING_EXIST(state, savingStatus) {
      state.isSavingExist = savingStatus;
    },
    SET_SAVING_ACCOUNT_DETAIL(state, savingAccountDetail) {
      state.savingAccountDetail = savingAccountDetail;
    },
  },
  actions: {
    async vuexGetSavingBalance({ commit }, childSeq) {
      await apiGetSavingsBalance(
        childSeq,
        (res) => {
          commit("SET_SAVING_BALANCE", res.data.balance);
          commit("SET_IS_SAVING_EXIST", res.data.stateType);
        },
        // (err) => {
        //   console.log(err);
        // }
        () => {}
      );
    },
    async vuexCloseSaving({ commit }, childSeq) {
      await apiCloseSavings(
        childSeq,
        () => {
          // console.log("적금 해지 완료!");
          commit("SET_IS_SAVING_EXIST", false);
          // alert("적금이 해지되었습니다.");
        },
        // (err) => {
        //   console.log(err);
        // }
        () => {}
      );
    },
    async vuexGetSavingDetails({ commit }, childSeq) {
      await apiGetSavingsDetails(childSeq, (res) => {
        commit("SET_SAVING_ACCOUNT_DETAIL", res.data);
      });
    },
    async vuexPostAddSavingHistory(context, formData) {
      await apiPostAddSavingsHistory(
        formData,
        () => {
          // console.log("입금 완료!");
        },
        // (err) => {
        //   console.log(err);
        // }
        () => {}
      );
    },
    async vuexPostOpenSavings({ commit }, childSeq) {
      await apiPostOpenSavings(
        childSeq,
        () => {
          // console.log("적금 개설 완료!");
          commit("SET_IS_SAVING_EXIST", true);
        },
        // (err) => {
        //   console.log(err);
        // }
        () => {}
      );
    },
  },
};

export default savingStore;
