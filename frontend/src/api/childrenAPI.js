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

async function childDetail(child_seq, success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await api.get(`/children/${child_seq}`).then(success).catch(fail);
}

async function childrenList(parent_seq, success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await api.get(`/children/parent/${parent_seq}`).then(success).catch(fail);
}

function childConnect(childSeq, success, fail) {
  api
    .put(`/children/connect`, JSON.stringify(childSeq))
    .then(success)
    .catch(fail);
}

export { childSignup, childLogin, childDetail, childConnect, childrenList };
