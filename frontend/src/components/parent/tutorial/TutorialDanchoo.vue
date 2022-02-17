<!--
  author: 유현수
-->

<template>
  <v-container class="fill-height">
    <v-row class="fill-height flex-column">
      <!-- 프로그레스 바 -->
      <v-col class="flex-grow-0">
        <v-progress-linear
          :value="($route.params.id / 3) * 100"
          color="parent01"
          :rounded="true"
          height="6"
        ></v-progress-linear>
      </v-col>

      <!-- 페이지 1 -->
      <v-col v-if="$route.params.id == 1" class="flex-shrink-1">
        <v-card class="fill-height text-center d-flex justify-center align-center">
          <div class="d-flex flex-column align-center">
            <h1 class="text-h5 font-weight-bold">아이가 사용할 화폐 단추!</h1>
            <p class="pt-4">
              첫단추에서는 '단추'라는 화폐를 사용해요. <br />
              아이는 앞으로 용돈 대신 아래 보이는 <br />
              단추 계좌에 단추를 입금받게 될거에요.
            </p>
            <v-img :src="mockupImg1" alt="" max-width="190px" />
          </div>
        </v-card>
      </v-col>

      <!-- 페이지 2 -->
      <v-col v-else-if="$route.params.id == 2" class="flex-shrink-1">
        <v-card class="fill-height text-center d-flex justify-center align-center">
          <div class="d-flex flex-column align-center">
            <h1 class="text-h5 font-weight-bold">단추로 경험하는 적금과 투자</h1>
            <p class="pt-4">
              아이는 단추를 활용해 <br />
              적금과 투자를 경험할 수 있어요. <br />
              현금이 아니니 더욱 안전하겠죠?
            </p>
            <v-img :src="mockupImg2" alt="" max-width="190px" />
          </div>
        </v-card>
      </v-col>

      <!-- 페이지 3 -->
      <v-col v-else class="flex-shrink-1">
        <v-card class="fill-height text-center d-flex justify-center align-center">
          <div class="d-flex flex-column align-center">
            <h1 class="text-h5 font-weight-bold">용돈으로 환전 가능한 '단추'</h1>
            <p class="pt-4">
              현금이 필요할 땐 환전이 가능해요. <br />
              아이가 환전을 요청하면 <br />
              단추를 현금으로 교환해주세요.
            </p>
            <v-img :src="mockupImg3" alt="" max-width="190px" />
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
              :to="`/parent/tutorial/what-is-danchoo/${-1 + +$route.params.id}`"
              >이전</v-btn
            >
          </v-col>
          <v-col v-if="$route.params.id != 3">
            <v-btn
              block
              color="parent01"
              class="white--text"
              :to="`/parent/tutorial/what-is-danchoo/${1 + +$route.params.id}`"
              >다음</v-btn
            >
          </v-col>
          <v-col v-else>
            <v-btn block color="parent01" class="white--text" @click.native="putTutorialStage()"
              >잘 이해했어요!</v-btn
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
  name: "TutorialDanchoo",
  data() {
    return {
      mockupImg1: require("../../../assets/image/mockup-child-home-crop.png"),
      mockupImg2: require("../../../assets/image/mockup-child-invest-crop.png"),
      mockupImg3: require("../../../assets/image/mockup-parent-exchange-crop.png"),
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
        stage: 1,
      };
      await this.vuexPutTutorialStage(tutorialInfo);
      await this.vuexGetTutorialStage(this.checkUserInfo.seq);
      this.$router.push("/parent/home");
    },
  },
};
</script>

<style></style>
