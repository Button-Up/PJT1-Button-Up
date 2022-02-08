/**
 * author : 김응철
 */

import { apiInstance } from "./index.js";

const api = apiInstance();

async function childSignup(formData, success, fail) {
  await api
    .post(`/children/join`, JSON.stringify(formData))
    .then(success)
    .catch(fail);
}

async function childLogin(formData, success, fail) {
  await api
    .post(`/children/login`, JSON.stringify(formData))
    .then(success)
    .catch(fail);
}

async function childDetail(childSeq, success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await api.get(`/children/${childSeq}`).then(success).catch(fail);
}

async function childrenList(parentSeq, success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await api.get(`/children/parent/${parentSeq}`).then(success).catch(fail);
}

function childConnect(childSeq, success, fail) {
  api
    .put(`/children/connect`, JSON.stringify(childSeq))
    .then(success)
    .catch(fail);
}

export { childSignup, childLogin, childDetail, childConnect, childrenList };
