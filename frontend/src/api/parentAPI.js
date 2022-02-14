/**
 * author: 유현수
 */

import { apiInstance } from "./index.js";

const api = apiInstance();

// 부모의 튜토리얼 진행 단계 조회
function apiGetTutorialStep(parentSeq, success, fail) {
  api.get(`/parents/tutorial/${parentSeq}`).then(success).catch(fail);
}

// 부모의 튜토리얼 진행 단계 수정
function apiPutTutorialStep(tutorialInfo, success, fail) {
  api.put("/parents/tutorial", JSON.stringify(tutorialInfo)).then(success).catch(fail);
}

// 아이를 부모의 자녀로 등록
async function apiPutChildrenConnect(nicknameInfo, success, fail) {
  await api.put("/children/connect", JSON.stringify(nicknameInfo)).then(success).catch(fail);
}

export { apiGetTutorialStep, apiPutTutorialStep, apiPutChildrenConnect };
