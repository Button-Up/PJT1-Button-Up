/**
 * @author Jiun Kim
 * created on 2022-02-14
 */
import { apiInstance } from "./index.js";

const api = apiInstance();

// 투자 프리셋 목록 조회
function apiGetAllInvestPreset(success, fail) {
  api.get(`/investments`).then(success).catch(fail);
}

// 새로운 투자 추가
function apiPostNewInvest(formData, success, fail) {
  api.post(`/investments`, JSON.stringify(formData)).then(success).catch(fail);
}

// 투자 현황 업데이트
function apiPutInvestStatus(formData, success, fail) {
  api.put(`/investments`, JSON.stringify(formData)).then(success).catch(fail);
}

// 자녀별 투자 종목별 현황 조회
function apiGetInvestStatusByChild(investSeq, childSeq, success, fail) {
  api.get(`/investments/${investSeq}/${childSeq}`).then(success).catch(fail);
}

// 투자 목록 조회
function apiGetAllInvest(parentSeq, success, fail) {
  api.get(`/investments/${parentSeq}`).then(success).catch(fail);
}

// 새로운 투자 가격 등록
function apiPostNewPrice(formData, success, fail) {
  api.post(`/share-prices`, JSON.stringify(formData)).then(success).catch(fail);
}

// 종목별 시세 조회
function apiGetSharePrice(investSeq, success, fail) {
  api.get(`/share-prices/${investSeq}`).then(success).catch(fail);
}

// 새로운 뉴스 등록
function apiPostNews(formData, success, fail) {
  api.post(`/news`, JSON.stringify(formData)).then(success).catch(fail);
}

// 뉴스 목록 조회
function apiGetAllNew(parentSeq, success, fail) {
  api.get(`/news/${parentSeq}`).then(success).catch(fail);
}

export {
  apiGetAllInvestPreset,
  apiPostNewInvest,
  apiPutInvestStatus,
  apiGetInvestStatusByChild,
  apiGetAllInvest,
  apiPostNewPrice,
  apiGetSharePrice,
  apiPostNews,
  apiGetAllNew,
};
