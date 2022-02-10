<template>
  <v-container class="fill-height">
    <v-row class="fill-height flex-column">
      <!-- 프로그레스 바 -->
      <v-col class="flex-grow-0">
        <v-progress-linear :value="($route.params.id / 3) * 100" color="parent01" round="6"></v-progress-linear>
      </v-col>

      <!-- 페이지 1 -->
      <v-col v-if="$route.params.id == 1">
        <v-card class="fill-height text-center">
          <div class="py-6">
            <h1 class="text-h5 font-weight-bold">아이가 사용할 화폐 단추!</h1>
            <p class="pt-4">
              첫단추에서는 '단추'라는 화폐를 사용해 <br />
              용돈을 지급합니다.
            </p>
          </div>
        </v-card>
      </v-col>

      <!-- 페이지 2 -->
      <v-col v-else-if="$route.params.id == 2">
        <v-card class="fill-height text-center">
          <div class="py-6">
            <h1 class="text-h5 font-weight-bold">용돈으로 환전 가능한 '단추'</h1>
            <p class="pt-4">
              아이는 부모님께 '환전 요청'을 할 수 있습니다. <br />
              아이가 요청한 금액만큼 <br />
              단추를 현금으로 교환해주세요.
            </p>
          </div>
        </v-card>
      </v-col>

      <!-- 페이지 3 -->
      <v-col v-else>
        <v-card class="fill-height text-center">
          <div class="py-6">
            <h1 class="text-h5 font-weight-bold">다양하게 소비할 수 있는 '단추'</h1>
            <p class="pt-4">
              아이는 단추를 소비해 소원권을 구매하거나 <br />
              사고 싶었던 물건을 골라 <br />
              '구매 요청'을 보낼 수 있습니다.
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
            <v-btn
              block
              color="parent01"
              class="white--text"
              @click.native="[addTutorialStep(), $router.push('/parent/home')]"
              >잘 이해했어요!</v-btn
            >
          </v-col>
        </v-row>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
//import { mapActions } from 'vuex'

export default {
  name: "TutorialDanchoo",
  methods: {
    //...mapActions('tempAccountStore', ['addTutorialStep'])
  },
};
</script>

<style></style>
