/**
 * author : 김응철
 */

import { apiInstance } from "./index.js";

const api = apiInstance();

// 할일 리스트 조회
function apiGetTodoList(jobSeq, success, fail) {
  api.get(`/todo-list/${jobSeq}`).then(success).catch(fail);
}

export { apiGetTodoList };
