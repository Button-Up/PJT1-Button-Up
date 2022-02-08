/**
 * author : 김응철
 */

import { apiInstance } from "./index.js";

const api = apiInstance();

// 새로운 직업 추가
function addJob(formData, success, fail) {
  api.post(`/jobs`, JSON.stringify(formData)).then(success).catch(fail);
}

// 직업 정보 조회
function getJobDetail(jobSeq, success, fail) {
  api.get(`/jobs/${jobSeq}`).then(success).catch(fail);
}

// 아이의 현재 직업 정보 조회
function getChildsJob(jobSeq, success, fail) {
  api.get(`/jobs/children/${jobSeq}`).then(success).catch(fail);
}

// 아이의 직업 내역 추가
function modifyJob(formData, success, fail) {
  api
    .post(`/jobs/histories`, JSON.stringify(formData))
    .then(success)
    .catch(fail);
}

// 직업 목록 조회
function getJobsList(parentSeq, success, fail) {
  api.get(`/jobs/parents/${parentSeq}`).then(success).catch(fail);
}

export { addJob, getJobDetail, getChildsJob, modifyJob, getJobsList };
