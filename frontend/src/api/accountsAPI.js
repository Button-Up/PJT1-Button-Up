/**
 * author : 김응철
 */

import { apiInstance } from "./index.js";

const api = apiInstance();

// 단추 입출금 계좌 잔액 조회
function getAccountBalance(childSeq, success, fail) {
  api.get(`/accounts/balance/${childSeq}`).then(success).catch(fail);
}

// 단추 입출금 목록 조회
function getAccountList(childSeq, success, fail) {
  api.get(`/accounts/histories/${childSeq}`).then(success).catch(fail);
}

// 입금 내역 추가
function addAccountDeposit(formData, success, fail) {
  api.post(`/accounts/histories/deposit`, JSON.stringify(formData)).then(success).catch(fail);
}

// 출금 내역 추가
function addAccountWithdraw(formData, success, fail) {
  api.post(`/accounts/histories/withdraw`, JSON.stringify(formData)).then(success).catch(fail);
}

export { getAccountBalance, getAccountList, addAccountDeposit, addAccountWithdraw };
