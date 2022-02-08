/**
 * author : 김응철
 */

import { apiInstance } from "./index.js";

const api = apiInstance();

// 아이 회원가입
async function childSignup(formData, success, fail) {
  await api
    .post(`/children/join`, JSON.stringify(formData))
    .then(success)
    .catch(fail);
}

// 아이 로그인
async function childLogin(formData, success, fail) {
  await api
    .post(`/children/login`, JSON.stringify(formData))
    .then(success)
    .catch(fail);
}

// 아이 상세조회
async function childDetail(childSeq, success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await api.get(`/children/${childSeq}`).then(success).catch(fail);
}

// 해당 부모의 아이들을 조회
async function childrenList(parentSeq, success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await api.get(`/children/parent/${parentSeq}`).then(success).catch(fail);
}

// 회원가입시 아이와 부모 연결
function childConnect(childSeq, success, fail) {
  api
    .put(`/children/connect`, JSON.stringify(childSeq))
    .then(success)
    .catch(fail);
}

export { childSignup, childLogin, childDetail, childConnect, childrenList };
