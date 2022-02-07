import { apiInstance } from "./index.js";

const api = apiInstance();

function getAccountBalance(child_seq, success, fail) {
  api.get(`/accounts/balance/${child_seq}`).then(success).catch(fail);
}

function getAccountList(child_seq, success, fail) {
  api.get(`/accounts/histories/${child_seq}`).then(success).catch(fail);
}

function addAccountDeposit(formData, success, fail) {
  api
    .post(`/accounts/histories/deposit`, JSON.stringify(formData))
    .then(success)
    .catch(fail);
}

function addAccountwirthdraw(formData, success, fail) {
  api
    .post(`/accounts/histories/withdraw`, JSON.stringify(formData))
    .then(success)
    .catch(fail);
}

export {
  getAccountBalance,
  getAccountList,
  addAccountDeposit,
  addAccountwirthdraw,
};
