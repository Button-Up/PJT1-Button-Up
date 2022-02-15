/**
 * author : 황승연
 */

import { apiInstance } from "./index.js";

const api = apiInstance();

//경제 주제 조회
function apiGetEconomyTopic(success, fail) {
  api.get(`/economy-topics`).then(success).catch(fail);
}

//경제 주제별 내용 조회
function apiGetContentOfTopic(topicSeq, success, fail) {
  api.get(`/economy-posts/${topicSeq}`).then(success).catch(fail);
}

export { apiGetEconomyTopic, apiGetContentOfTopic };
