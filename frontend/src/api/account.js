import { apiInstance } from "./index.js";

const api = apiInstance();

async function childSignup(formData, success, fail) {
  await api.post('/children/join', JSON.stringify(formData)).then(success).catch(fail)
}

export { childSignup };