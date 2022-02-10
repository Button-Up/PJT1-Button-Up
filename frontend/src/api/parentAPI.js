import { apiInstance } from "./index.js";

const api = apiInstance();

function apiGetTutorialStep(parentSeq, success, fail) {
  api.get(`/parents/tutorial/${parentSeq}`).then(success).catch(fail);
}

function apiPutTutorialStep(tutorialInfo, success, fail) {
  api.put("/parents/tutorial", JSON.stringify(tutorialInfo)).then(success).catch(fail);
}

export { apiGetTutorialStep, apiPutTutorialStep };
