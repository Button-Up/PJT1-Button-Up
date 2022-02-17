<!--
  author: 유현수
-->

<template>
  <v-container class="fill-height">
    <v-row class="fill-height flex-column">
      <!-- 프로그레스 바 -->
      <v-col class="flex-grow-0">
        <v-progress-linear
          :value="($route.params.id / 2) * 100"
          color="parent01"
          :rounded="true"
          height="6"
        ></v-progress-linear>
      </v-col>

      <!-- 페이지 1 -->
      <v-col v-if="$route.params.id == 1" class="flex-shrink-1">
        <v-card class="fill-height text-center d-flex justify-center align-center">
          <div class="d-flex flex-column align-center">
            <h1 class="my-2 text-h5 font-weight-bold">투자 활동이란?</h1>
            <p class="pt-4 mb-8">
              부모님의 몸무게, 퇴근 시간 등 <br />
              아이들이 이해하기 쉬운 수치를 사용해 <br />
              단추를 투자할 수 있어요 <br />
            </p>
            <v-img :src="imagePath1" width="220px"></v-img>
          </div>
        </v-card>
      </v-col>

      <!-- 페이지 2 -->
      <v-col v-else-if="$route.params.id == 2" class="flex-shrink-1">
        <v-card class="fill-height text-center d-flex justify-center align-center">
          <div class="d-flex flex-column align-center">
            <h1 class="text-h5 font-weight-bold">몇 살부터 추천하나요?</h1>
            <p class="pt-4">
              4학년까지는 투자보다 적금을 활용한<br />
              저축 습관 형성을 권장드려요.<br />
            </p>
            <p>
              5학년부터는<br />
              무리 없이 가능해요!<br />
            </p>
            <br />
            <v-img :src="imagePath2" width="200"></v-img>
          </div>
        </v-card>
      </v-col>

      <!-- 하단 버튼 -->
      <v-col class="flex-grow-0">
        <v-row dense>
          <v-col v-if="$route.params.id != 1">
            <v-btn
              block
              color="parent01"
              class="white--text"
              :to="`/parent/tutorial/stock/${-1 + +$route.params.id}`"
              >이전</v-btn
            >
          </v-col>
          <v-col v-if="$route.params.id != 2">
            <v-btn
              block
              color="parent01"
              class="white--text"
              :to="`/parent/tutorial/stock/${1 + +$route.params.id}`"
              >다음</v-btn
            >
          </v-col>
          <v-col v-else>
            <v-btn
              block
              color="parent01"
              class="white--text"
              @click.native="[putTutorialStage(), $router.push('/parent/activity/invest/new/1')]"
              >투자 활동 살펴보기</v-btn
            >
          </v-col>
        </v-row>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import { mapActions, mapGetters } from "vuex";

export default {
  name: "TutorialStock",
  data() {
    return {
      imagePath1: require("../../../assets/svg/piggy-bank-animate.svg"),
      imagePath2: require("../../../assets/svg/finance-app-animate.svg"),
    };
  },
  computed: {
    ...mapGetters("userStore", ["checkUserInfo"]),
  },
  methods: {
    ...mapActions("parentStore", ["vuexPutTutorialStage", "vuexGetTutorialStage"]),
    async putTutorialStage() {
      const tutorialInfo = {
        parentSeq: this.checkUserInfo.seq,
        stage: 4,
      };
      await this.vuexPutTutorialStage(tutorialInfo);
      await this.vuexGetTutorialStage(this.checkUserInfo.seq);
    },
  },
};
</script>

<style scoped>
/* #pageImage1 {
  width: 100%;
  height: 100%;
}
#pageImage2 {
  width: 70%;
  height: 70%;
} */
</style>
