/**
 * author : 김응철
 */

import { apiInstance } from "./index.js";

const api = apiInstance();

function getAccountBalance(childSeq, success, fail) {
  api.get(`/accounts/balance/${childSeq}`).then(success).catch(fail);
}

function getAccountList(childSeq, success, fail) {
  api.get(`/accounts/histories/${childSeq}`).then(success).catch(fail);
}

function addAccountDeposit(formData, success, fail) {
  api
    .post(`/accounts/histories/deposit`, JSON.stringify(formData))
    .then(success)
    .catch(fail);
}

function addAccountWithdraw(formData, success, fail) {
  api
    .post(`/accounts/histories/withdraw`, JSON.stringify(formData))
    .then(success)
    .catch(fail);
}

export {
  getAccountBalance,
  getAccountList,
  addAccountDeposit,
  addAccountWithdraw,
};
