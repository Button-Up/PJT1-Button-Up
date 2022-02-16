/**
 * @author Eunee Chung
 * created on 2022-02-16
 */

import { apiInstance } from "./index.js";

const api = apiInstance();

// 적금 잔액 조회
async function apiGetSavingsBalance(childSeq, success, fail) {
  await api.get(`/savings/balance/${childSeq}`).then(success).catch(fail);
}
// 적금 통장 해지
async function apiCloseSavings(childSeq, success, fail) {
  await api.delete(`/savings/close/${childSeq}`).then(success).catch(fail);
}
// 적금 상세 정보 조회
async function apiGetSavingsDetails(childSeq, success, fail) {
  await api.get(`/savings/detail/${childSeq}`).then(success).catch(fail);
}
// 적금 입금 내역 추가
async function apiPostAddSavingsHistory(formData, success, fail) {
  await api.post(`/savings/histories/deposit`, JSON.stringify(formData)).then(success).catch(fail);
}
// 적급 통장 개설
async function apiPostOpenSavings(childSeq, success, fail) {
  await api.post(`/savings/open/${childSeq}`).then(success).catch(fail);
}

export {
  apiGetSavingsBalance,
  apiCloseSavings,
  apiGetSavingsDetails,
  apiPostAddSavingsHistory,
  apiPostOpenSavings,
};
