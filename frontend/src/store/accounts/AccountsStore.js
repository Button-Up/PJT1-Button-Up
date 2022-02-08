/**
 * @author Jiun Kim
 * created on 2022-02-08
 */
import { getAccountBalance, addAccountDeposit, addAccountWithdraw } from "@/api/accountsAPI";

const AccountsStore = {
  namespaced: true,
  state: {
    default_Balance: 0,
    account_history: [],
  },
  getters: {
    getDefaultBalance: (state) => state.defaultBalance,
  },
  mutations: {
    SET_DEFAULT_BALANCE(state, payload) {
      state.default_Balance = payload;
    },
    ADD_ACCOUNT_HISTORY(state, payload) {
      state.account_history.unshift(payload);
    },
  },
  actions: {
    /**
     * 입출금 계좌 잔액 업데이트
     *
     * @param {*} param0
     * @param {Number} childSeq
     */
    async updateDefaultBalance({ commit }, childSeq) {
      await getAccountBalance(childSeq, (resp) => {
        console.log(resp);
        commit("SET_DEFAULT_BALANCE", resp);
      });
    },
    async addAccountHistoryDeposit({ commit }, accountHistory) {
      await addAccountDeposit(accountHistory, (resp) => {
        console.log(resp);
        commit();
      });
    },
  },
};

export default AccountsStore;
