/**
 * author : 김응철
 */

import { apiInstance } from "./index.js";

const api = apiInstance();

async function parentSignup(formData, success, fail) {
  await api
    .post(`/parents/join`, JSON.stringify(formData))
    .then(success)
    .catch(fail);
}

async function parentLogin(formData, success, fail) {
  await api
    .post(`/parents/login`, JSON.stringify(formData))
    .then(success)
    .catch(fail);
}

async function parentDetail(parentSeq, success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await api.get(`/parents/${parentSeq}`).then(success).catch(fail);
}

export { parentSignup, parentLogin, parentDetail };
