<!--
  author : 정은이

  modified: 김지언 - 자녀 예금 계좌 잔액 표시
-->
<template>
  <v-card
    class="mr-auto ma-0"
    outlined
    rounded="xl"
    elevation="4"
    :color="isDeposit ? 'parent01' : 'parent03'"
  >
    <v-list-item three-line class="text-center justify-center pa-0">
      <v-list-item-content class="pa-2">
        <v-card-text
          v-text="isDeposit ? '예금' : '적금'"
          :class="isDeposit ? 'white--text' : 'black--text'"
          class="pt-3 pb-0"
        ></v-card-text>
        <v-list-item-title
          v-text="amount"
          class="text-h4 ma-1 font-weight-bold"
          :class="isDeposit ? 'white--text' : 'black--text'"
        >
        </v-list-item-title>
        <v-card-text
          v-text="`단추`"
          :class="isDeposit ? 'white--text' : 'black--text'"
          class="pb-3 pt-2"
        ></v-card-text>
      </v-list-item-content>
    </v-list-item>
  </v-card>
</template>

<script>
export default {
  name: "ChildAccount",
  data() {
    return {};
  },
  props: {
    amount: Number,
    isDeposit: Boolean,
  },
};
</script>

<style></style>
