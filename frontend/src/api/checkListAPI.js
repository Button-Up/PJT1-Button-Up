/**
 * author : 우정연
 */

import { apiInstance } from "./index.js";

const api = apiInstance();

// 체크리스트 조회
function apiGetCheckListRow(childSeq, success, fail) {
  api.get(`/check-list/${childSeq}`).then(success).catch(fail);
}

// 체크리스트 완료 여부 수정
function apiPutCheckListRow(toDoCheckRequest, success, fail) {
  api.put(`/check-list/flag`, JSON.stringify(toDoCheckRequest)).then(success).catch(fail);
}

export { apiGetCheckListRow, apiPutCheckListRow };
