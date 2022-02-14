<template>
  <div>
    <!-- 파트 1 -->
    <v-sheet width="100%" height="880px" color="#3dbf69" class="d-flex justify-center align-center">
      <div class="text-center white--text">
        <h1>
          우리 아이 경제 교육 <span class="yellow--text">첫단추</span> <br />
          제대로 끼우기!
        </h1>
        <p class="mt-6">
          첫단추로 기르는 금융 리터러시! <br />
          용돈 관리부터 투자 교육까지 <br />
          첫단추로 해결해보세요.
        </p>
        <img :src="growingSvg" alt="growing" class="my-6" />
        <v-btn
          to="/parent/signup"
          block
          elevation="0"
          class="font-weight-bold"
          style="color: #3dbf69"
          >부모님 회원 시작하기</v-btn
        >
        <v-btn
          to="/child/signup"
          block
          outlined
          elevation="0"
          color="white"
          class="mt-2 font-weight-bold"
          >자녀 회원 시작하기</v-btn
        >
      </div>
    </v-sheet>
    <!-- 파트 2 -->
    <v-sheet width="100%" height="880px" color="#FFD82A" class="d-flex justify-center align-center">
      <div class="text-center d-flex flex-column align-center">
        <h1>쉽고 편한 용돈 관리</h1>
        <p class="mt-6">
          한 번 설정하면 용돈이 알아서 지급되는 편리함!<br />
          자녀는 자신의 역할을 다 하고 받는 용돈이라<br />
          책임감도 기르고 보람도 느낄 수 있어요.
        </p>
        <v-img :src="mockupImg1" max-width="260px" class="mt-6"></v-img>
      </div>
    </v-sheet>
    <!-- 파트 3 -->
    <v-sheet width="100%" height="880px" color="#216BD9" class="d-flex justify-center align-center">
      <div class="text-center d-flex flex-column align-center white--text">
        <h1>직접 해보는 적금, 투자</h1>
        <p class="mt-6">
          이해하기 쉬운, 말랑말랑한 <br />
          적금과 투자 기능이 준비되어 있어요! <br />
          현금으로 하지 않으니 더더욱 안심 :D
        </p>
        <v-img :src="mockupImg2" max-width="260px" class="mt-6"></v-img>
      </div>
    </v-sheet>
    <!-- 파트 4 -->
    <v-sheet width="100%" height="880px" color="black" class="d-flex justify-center align-center">
      <div class="text-center d-flex flex-column align-center white--text">
        <h1>개념, 놓치지 않을 거에요</h1>
        <p class="mt-6">
          낯선 경제 단어들도 차근차근, <br />
          경제 공부방에서 퀴즈로 배울 수 있어요
        </p>
        <v-img :src="mockupImg3" max-width="260px" class="mt-6"></v-img>
      </div>
    </v-sheet>
  </div>
</template>

<script>
export default {
  name: "Index",
  data() {
    return {
      model: null,
      growingSvg: require("../../assets/svg/growing.svg"),
      mockupImg1: require("../../assets/image/mockup-1.png"),
      mockupImg2: require("../../assets/image/mockup-2.png"),
      mockupImg3: require("../../assets/image/mockup-3.png"),
    };
  },
};
</script>

<style lang="scss" scoped>
h1 {
  font-size: 1.8rem;
}
</style>
