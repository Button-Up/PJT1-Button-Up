import { apiInstance } from "./index.js";

const api = apiInstance();

function addJob(formData, success, fail) {
  api.post(`/jobs`, JSON.stringify(formData)).then(success).catch(fail);
}

function getJobDetail(job_seq, success, fail) {
  api.get(`/jobs/${job_seq}`).then(success).catch(fail);
}

function getChildsJob(job_seq, success, fail) {
  api.get(`/jobs/children/${job_seq}`).then(success).catch(fail);
}

function modifyJob(formData, success, fail) {
  api
    .post(`/jobs/histories`, JSON.stringify(formData))
    .then(success)
    .catch(fail);
}

function getJobsList(parent_seq, success, fail) {
  api.get(`/jobs/children/${parent_seq}`).then(success).catch(fail);
}

export { addJob, getJobDetail, getChildsJob, modifyJob, getJobsList };
