/**
 * @author Jeongyeon Woo
 * created on 2022-02-09
 */

import { apiInstance } from "./index.js";

const api = apiInstance();

// 요청 내역 상세 조회
function getRequest(requestSeq, success, fail) {
  api.get(`/requests/${requestSeq}`).then(success).catch(fail);
}

// 아이의 요청 내역 목록 조회
function getRequestList(childSeq, success, fail) {
  api.get(`/requests/child/${childSeq}`).then(success).catch(fail);
}

// 환전 요청 추가
function addExchangeRequest(formData, success, fail) {
  api.post(`/requests/exchange`, JSON.stringify(formData)).then(success).catch(fail);
}

// 요청 상태 승인으로 변경
function modifyRequestStatusApprove(requestSeq, success, fail) {
  api.put(`/requests/status/approve`, { requestSeq }).then(success).catch(fail);
}

// 요청 상태 거절로 변경
function modifyRequestStatusReject(requestSeq, success, fail) {
  api.put(`/requests/status/reject`, { requestSeq }).then(success).catch(fail);
}

export {
  getRequest,
  getRequestList,
  addExchangeRequest,
  modifyRequestStatusApprove,
  modifyRequestStatusReject,
};
