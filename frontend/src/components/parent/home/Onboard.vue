<template>
  <div>
    <h1>첫단추 튜토리얼</h1>
    <p class="mt-4">튜토리얼은 꼭 아이와 함께 진행해주세요!</p>
    <v-stepper v-model="tutorialStep" vertical flat class="mb-16">
      <v-stepper-step :complete="tutorialStep > 1" step="1" color="parent01">
        첫단추의 화폐, 단추
      </v-stepper-step>

      <v-stepper-content step="1">
        <v-card>
          <v-card-subtitle class="black--text"
            >단추로 용돈을 받고, 투자도 한다고? <br />
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

      <v-stepper-step :complete="tutorialStep > 2" step="2" color="parent01">
        아이에게 직업을 정해주세요!
      </v-stepper-step>

      <v-stepper-content step="2">
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

      <v-stepper-step :complete="tutorialStep > 3" step="3" color="parent01">
        아이들을 위한 모의투자
      </v-stepper-step>

      <v-stepper-content step="3">
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
  data() {
    return {
      tutorialStep: this.checkTutorialStage + 1,
    };
  },
  props: {
    checkTutorialStage: {
      type: Number,
      default: 0,
    },
  },
};
</script>

<style></style>
