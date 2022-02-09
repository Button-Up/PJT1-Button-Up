/**
 * author : 김응철
 * modified: 유현수
 */

import { apiInstance } from "./index.js";

const api = apiInstance();

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
async function getUserInfo(isParent, userSeq, success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await api
    .get(isParent ? `/parent/${userSeq}` : `/children/${userSeq}`)
    .then(success)
    .catch(fail);
}

export { signup, login, getUserInfo };
