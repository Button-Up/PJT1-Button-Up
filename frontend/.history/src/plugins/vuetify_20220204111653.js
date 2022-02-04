import Vue from "vue";
import Vuetify from "vuetify/lib/framework";

Vue.use(Vuetify);

export default new Vuetify({
  theme: {
    themes: {
      light: {
        parent01: "#5948AC",
        parent02: "#AEA4D9",
        parent03: "#CCC5EA",
        parent04: "#F6F4FF",

        child01: "#FFBF43", // 노랑
        child02: "#FDD26C", // 연(투명)노랑
        child03: "#FCF1E2", // 핑크
        child04: "#FE9246", // 오렌지
      },
    },
  },
});
