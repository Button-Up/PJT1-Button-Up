/**
 * @author Eunee Chung
 * created on 2022-02-16
 */

import { apiInstance } from './index.js';

const api = apiInstance();

// 적금 잔액 조회
function apiGetSavingsBalance(childSeq, success, fail) {
  api.get(`/savings/balance/${childSeq}`).then(success).catch(fail);
}
// 적금 통장 해지
function apiCloseSavings(childSeq, success, fail) {
  api.delete(`/savings/close/${childSeq}`).then(success).catch(fail);
}
// 적금 상세 정보 조회
function apiGetSavingsDetails(childSeq, success, fail) {
  api.get(`/savings/detail/${childSeq}`).then(success).catch(fail);
}
// 적금 입금 내역 추가
function apiPostAddSavingsHistory(formData, success, fail) {
  api.post(`/savings/histories/deposit`, JSON.stringify(formData)).then(success).catch(fail);
}
// 적급 통장 개설
function apiPostOpenSavings(childSeq, formData, success, fail) {
  api.post(`/savings/open/${childSeq}`, JSON.stringify(formData)).then(success).catch(fail);
}

export {
  apiGetSavingsBalance,
  apiCloseSavings,
  apiGetSavingsDetails,
  apiPostAddSavingsHistory,
  apiPostOpenSavings,
};
