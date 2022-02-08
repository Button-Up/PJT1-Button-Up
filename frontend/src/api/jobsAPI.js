/**
 * author : 김응철
 */

import { apiInstance } from "./index.js";

const api = apiInstance();

function addJob(formData, success, fail) {
  api.post(`/jobs`, JSON.stringify(formData)).then(success).catch(fail);
}

function getJobDetail(jobSeq, success, fail) {
  api.get(`/jobs/${jobSeq}`).then(success).catch(fail);
}

function getChildsJob(jobSeq, success, fail) {
  api.get(`/jobs/children/${jobSeq}`).then(success).catch(fail);
}

function modifyJob(formData, success, fail) {
  api
    .post(`/jobs/histories`, JSON.stringify(formData))
    .then(success)
    .catch(fail);
}

function getJobsList(parentSeq, success, fail) {
  api.get(`/jobs/parents/${parentSeq}`).then(success).catch(fail);
}

export { addJob, getJobDetail, getChildsJob, modifyJob, getJobsList };
