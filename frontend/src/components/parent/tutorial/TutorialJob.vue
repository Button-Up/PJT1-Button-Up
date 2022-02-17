<!--
  author: 유현수

  직업 관리 페이지로 이동만 해도 튜토리얼 단계가 1 올라가게 되어 있습니다.
  직업을 생성해야 튜토리얼 단계가 올라가도록 수정 필요
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
        <v-card class="fill-height text-center d-flex flex-column justify-center">
          <div class="d-flex flex-column align-center">
            <h1 class="text-h5 font-weight-bold">직업과 보상</h1>
            <p class="pt-4">
              아이는 청소, 식물 가꾸기 등 가사에 참여하고 <br />
              보상으로 단추를 받아요. <br />
            </p>
            <v-img :src="svg1" alt="" width="300px" height="300px"></v-img>
          </div>
        </v-card>
      </v-col>

      <!-- 페이지 2 -->
      <v-col v-else class="flex-shrink-1">
        <v-card class="fill-height text-center d-flex flex-column justify-center">
          <div class="d-flex flex-column align-center">
            <h1 class="text-h5 font-weight-bold">직업과 활동 정하기</h1>
            <p class="pt-4">
              아이가 어떤 활동을 할지, <br />
              직업 이름은 무엇으로 할지 설정할 수 있어요. <br />
              아이와 함께 논의해서 정해보세요!
            </p>
            <v-img :src="img1" alt="" width="190px" height="310px"></v-img>
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
              :to="`/parent/tutorial/job/${-1 + +$route.params.id}`"
              >이전</v-btn
            >
          </v-col>
          <v-col v-if="$route.params.id != 2">
            <v-btn
              block
              color="parent01"
              class="white--text"
              :to="`/parent/tutorial/job/${1 + +$route.params.id}`"
              >다음</v-btn
            >
          </v-col>
          <v-col v-else>
            <v-btn
              block
              color="parent01"
              class="white--text"
              @click.native="[putTutorialStage(), $router.push('/parent/home/job')]"
              >직업 만들러 가기</v-btn
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
  name: "TutorialJob",
  data() {
    return {
      svg1: require("../../../assets/svg/child-work.svg"),
      img1: require("../../../assets/image/mockup-parent-job-crop.png"),
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
        stage: 3,
      };
      await this.vuexPutTutorialStage(tutorialInfo);
      await this.vuexGetTutorialStage(this.checkUserInfo.seq);
    },
  },
};
</script>

<style></style>
