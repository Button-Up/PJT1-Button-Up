/**
 * @author Jiun Kim
 * created on 2022-02-08
 */
import {
  getAccountBalance as apiGetAccountBalance,
  addAccountDeposit as apiAddAccountDeposit,
  addAccountWithdraw as apiAddAccountWithdraw,
  getAccountList as apiGetAccountList,
} from "@/api/accountsAPI";

const accountStore = {
  namespaced: true,
  state: {
    default_Balance: 0,
    account_history: [],
  },
  getters: {
    getDefaultBalance: (state) => state.default_Balance,
    getAccountList: (state) => state.account_history,
  },
  mutations: {
    SET_DEFAULT_BALANCE(state, payload) {
      state.default_Balance = payload;
    },
    ADD_ACCOUNT_HISTORY(state, payload) {
      state.account_history.unshift(payload);
    },
    FETCH_ACCOUNT_HISTORY(state, payload) {
      state.account_history = payload;
    },
  },
  actions: {
    /**
     * 입출금 계좌 잔액 업데이트 - 최초만 실행
     *
     * @param {*} param0
     * @param {number} childSeq
     */
    async vuexUpdateDefaultBalance({ commit }, childSeq) {
      await apiGetAccountBalance(childSeq, (resp) => {
        commit("SET_DEFAULT_BALANCE", resp.data);
      });
    },
    /**
     *  입금 내역 추가
     *
     * @param {*} param0
     * @param {*} accountHistory
     */
    async vuexAddAccountHistoryDeposit({ commit }, accountHistory) {
      await apiAddAccountDeposit(accountHistory, (resp) => {
        // console.log(resp);
        commit("ADD_ACCOUNT_HISTORY", resp.data);
        commit("SET_DEFAULT_BALANCE", resp.data.balance);
      });
    },
    /**
     *  출금 내역 추가
     *
     * @param {*} param0
     * @param {*} accountHistory
     */
    async vuexAddAccountHistoryWithdraw({ commit }, accountHistory) {
      await apiAddAccountWithdraw(accountHistory, (resp) => {
        // console.log(resp);
        commit("ADD_ACCOUNT_HISTORY", resp.data);
        commit("SET_DEFAULT_BALANCE", resp.data.balance);
      });
    },
    /**
     * 입출금 내역 전체 조회
     *
     * @param {*} param0
     * @param {number} childSeq
     */
    async vuexFetchAccountHistory({ commit }, childSeq) {
      await apiGetAccountList(childSeq, (resp) => {
        // console.log(resp.data);
        commit("FETCH_ACCOUNT_HISTORY", resp.data);
      });
    },
  },
};

export default accountStore;
