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
      <v-col v-if="$route.params.id == 1">
        <v-card class="fill-height text-center">
          <div class="py-6">
            <h1 class="text-h5 font-weight-bold">직업이란?</h1>
            <p class="pt-4">
              가정에서 아이에게 정해주는 역할입니다. <br />
              아이는 주어진 책임을 다한 보상으로 <br />
              용돈(단추)을 받게 됩니다.
            </p>
          </div>
        </v-card>
      </v-col>

      <!-- 페이지 2 -->
      <v-col v-else-if="$route.params.id == 2">
        <v-card class="fill-height text-center">
          <div class="py-6">
            <h1 class="text-h5 font-weight-bold">왜 직업이 필요할까요?</h1>
            <p class="pt-4">
              무언가 시킬 때마다 용돈을 주다보면 <br />
              ‘이거 하면 얼마 줄거야?’하는 질문이 <br />
              쉽게 돌아옵니다. <br /><br />
              주어진 역할과 책임을 다 할 때, <br />
              약속한 보상이 돌아온다는 것을 <br />
              익힐 수 있도록 해주세요. <br />
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
              @click.native="[putTutorialStage(), $router.push('/parent/activity/job')]"
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
