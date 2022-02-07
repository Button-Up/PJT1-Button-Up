import { apiInstance } from "./index.js";

const api = apiInstance();

function getTodoList(job_seq, success, fail) {
  api.get(`/todo-list/${job_seq}`).then(success).catch(fail);
}

export { getTodoList };
