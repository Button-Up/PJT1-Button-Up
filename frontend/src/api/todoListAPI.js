/**
 * author : 김응철
 */

import { apiInstance } from "./index.js";

const api = apiInstance();

function getTodoList(jobSeq, success, fail) {
  api.get(`/todo-list/${jobSeq}`).then(success).catch(fail);
}

export { getTodoList };
