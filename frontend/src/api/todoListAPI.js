/**
 * author : 김응철
 */

import { apiInstance } from "./index.js";

const api = apiInstance();

// 할일 리스트 조회
function getTodoList(jobSeq, success, fail) {
  api.get(`/todo-list/${jobSeq}`).then(success).catch(fail);
}

export { getTodoList };
