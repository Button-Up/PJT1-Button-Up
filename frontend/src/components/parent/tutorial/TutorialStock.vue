<template>
  <v-container class="fill-height">
    <v-row class="fill-height flex-column">
      <!-- 프로그레스 바 -->
      <v-col class="flex-grow-0">
        <v-progress-linear
          :value="($route.params.id / 2) * 100"
          color="parent01"
          round="6"
        ></v-progress-linear>
      </v-col>

      <!-- 페이지 1 -->
      <v-col v-if="$route.params.id == 1">
        <v-card class="fill-height text-center">
          <div class="py-6">
            <h1 class="text-h5 font-weight-bold">투자 활동이란?</h1>
            <p class="pt-4">
              부모님의 몸무게, 퇴근 시간 등 <br />
              아이들이 이해하기 쉬운 수치를 사용해 <br />
              단추를 투자할 수 있어요 <br />
            </p>
          </div>
        </v-card>
      </v-col>

      <!-- 페이지 2 -->
      <v-col v-else-if="$route.params.id == 2">
        <v-card class="fill-height text-center">
          <div class="py-6">
            <h1 class="text-h5 font-weight-bold">몇 살부터 추천하나요?</h1>
            <p class="pt-4">
              꺾은선 그래프를 배우는 6학년부터는<br />
              무리 없이 가능해요.<br />
              <br />
              수학, 경제 개념 이해가 빠르다면<br />
              5학년부터도 권장드려요.<br />
              <br />
              4학년까지는 투자보다 적금을 활용한<br />
              저축 습관 형성을 권장드려요.<br />
            </p>
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
              @click.native="[putTutorialStage(), $router.push('/parent/activity')]"
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
  computed: {
    ...mapGetters("userStore", ["checkUserInfo"]),
  },
  methods: {
    ...mapActions("parentStore", ["vuexPutTutorialStage", "vuexGetTutorialStage"]),
    async putTutorialStage() {
      const tutorialInfo = {
        parentSeq: this.checkUserInfo.seq,
        stage: 3,
      };
      await this.vuexPutTutorialStage(tutorialInfo);
      await this.vuexGetTutorialStage(this.checkUserInfo.seq);
    },
  },
};
</script>

<style></style>
