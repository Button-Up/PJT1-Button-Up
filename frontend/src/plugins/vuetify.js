import Vue from "vue";
import Vuetify from "vuetify/lib/framework";

Vue.use(Vuetify);

export default new Vuetify({
  theme: {
    themes: {
      light: {
        parent01: "#5948AC", // 보라
        parent02: "#AEA4D9", // 살짝 연한 보라
        parent03: "#CCC5EA", // 연보라
        parent04: "#F6F4FF", // 엄청 연한 보라

        child01: "#FFBF43", // 노랑
        child02: "#FDD26C", // 연(투명)노랑
        child03: "#FCF1E2", // 핑크
        child04: "#FE9246", // 오렌지
        child05: "#FFB37D", // 연오렌지
        child06: "#FFCEAC", // 더 연한 오렌지
      },
    },
  },
});
