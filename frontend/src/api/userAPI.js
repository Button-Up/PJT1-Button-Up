/**
 * author : 김응철
 */

import { apiInstance } from "./index.js";

const api = apiInstance();

// // 부모 회원가입
// async function parentSignup(formData, success, fail) {
//   await api
//     .post(`/parents/join`, JSON.stringify(formData))
//     .then(success)
//     .catch(fail);
// }

// // 부모 로그인
// async function parentLogin(formData, success, fail) {
//   await api
//     .post(`/parents/login`, JSON.stringify(formData))
//     .then(success)
//     .catch(fail);
// }

// //부모 상세 조회
// async function parentDetail(parentSeq, success, fail) {
//   api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
//   await api.get(`/parents/${parentSeq}`).then(success).catch(fail);
// }

//회원가입
async function signup(isParent, formData, success, fail) {
  await api
    .post(
      isParent ? "/parent/join" : "/children/join",
      JSON.stringify(formData)
    )
    .then(success)
    .catch(fail);
}

//로그인
async function login(isParent, formData, success, fail) {
  await api
    .post(
      isParent ? "/parent/login" : "/children/login",
      JSON.stringify(formData)
    )
    .then(success)
    .catch(fail);
}

//상세조회
async function userInfo(isParent, userSeq, success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await api
    .get(isParent ? `/parent/${userSeq}` : `/children/${userSeq}`)
    .then(success)
    .catch(fail);
}

export { signup, login, userInfo };
