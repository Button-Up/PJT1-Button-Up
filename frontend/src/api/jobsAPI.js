/**
 * author : 김응철
 * modified : 정은이 - Rename , 2022-02-09 17:15
 */

import { apiInstance } from './index.js';

const api = apiInstance();

// 새로운 직업 추가
function apiAddJob(formData, success, fail) {
  api.post(`/jobs`, JSON.stringify(formData)).then(success).catch(fail);
}

// 직업 정보 조회
function apiGetJobDetail(jobSeq, success, fail) {
  api.get(`/jobs/${jobSeq}`).then(success).catch(fail);
}

// 아이의 현재 직업 정보 조회
function apiGetChildsJob(childSeq, success, fail) {
  api.get(`/jobs/children/${childSeq}`).then(success).catch(fail);
}

// 아이의 직업 내역 추가
function apiModifyJob(formData, success, fail) {
  api.post(`/jobs/histories`, JSON.stringify(formData)).then(success).catch(fail);
}

// 직업 목록 조회
function apiGetJobsList(parentSeq, success, fail) {
  api.get(`/jobs/parents/${parentSeq}`).then(success).catch(fail);
}

export { apiAddJob, apiGetJobDetail, apiGetChildsJob, apiModifyJob, apiGetJobsList };
