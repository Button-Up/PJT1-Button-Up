<!--
  author: 유현수
-->

<template>
  <div>
    <h1>첫단추 튜토리얼</h1>
    <v-stepper v-model="tutorialStep" vertical flat class="mb-16">
      <!-- STEP 1 -->
      <v-stepper-step :complete="tutorialStep > 1" step="1" color="parent01">
        첫단추를 소개합니다!
      </v-stepper-step>
      <v-stepper-content step="1">
        <v-card>
          <v-card-subtitle class="black--text"
            >단추로 용돈을 받고, <br />
            투자도 한다고? <br />
            단추가 뭐길래?</v-card-subtitle
          >
          <v-card-actions>
            <v-btn
              block
              color="parent01"
              @click="$router.push('/parent/tutorial/what-is-danchoo/1')"
              class="white--text"
            >
              지금 알아보기
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-stepper-content>

      <!-- STEP 2 -->
      <v-stepper-step :complete="tutorialStep > 2" step="2" color="parent01">
        자녀 계정 연결하기
      </v-stepper-step>
      <v-stepper-content step="2">
        <v-card>
          <v-card-subtitle class="black--text"
            >첫단추를 사용할 자녀 계정을 연결해볼까요?</v-card-subtitle
          >
          <v-card-actions>
            <v-btn
              block
              color="parent01"
              @click="$router.push('/parent/tutorial/child-sync/1')"
              class="white--text"
            >
              지금 알아보기
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-stepper-content>

      <!-- STEP 3 -->
      <v-stepper-step :complete="tutorialStep > 3" step="3" color="parent01">
        아이에게 직업을 정해주세요!
      </v-stepper-step>
      <v-stepper-content step="3">
        <v-card>
          <v-card-subtitle class="black--text"
            >단추를 얻는 첫 번째 방법! <br />
            아이의 직업을 정해볼까요?</v-card-subtitle
          >
          <v-card-actions>
            <v-btn
              block
              color="parent01"
              @click="$router.push('/parent/tutorial/job/1')"
              class="white--text"
            >
              아이 직업 만들기
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-stepper-content>

      <!-- STEP 4 -->
      <v-stepper-step :complete="tutorialStep > 4" step="4" color="parent01">
        아이들을 위한 모의투자
      </v-stepper-step>
      <v-stepper-content step="4">
        <v-card>
          <v-card-subtitle class="black--text"
            >부모님 몸무게에 투자해볼까? <br />
            투자도 쉽고 재미있게!</v-card-subtitle
          >
          <v-card-actions>
            <v-btn
              block
              color="parent01"
              @click="$router.push('/parent/tutorial/stock/1')"
              class="white--text"
            >
              지금 알아보기
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-stepper-content>
    </v-stepper>
  </div>
</template>

<script>
export default {
  name: "ParentTutorial",
  props: {
    checkTutorialStage: {
      type: Number,
      default: 0,
    },
  },
  computed: {
    tutorialStep() {
      return this.checkTutorialStage + 1;
    },
  },
};
</script>

<style></style>
