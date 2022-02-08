<!--
author: 유현수
-->

<template>
  <div>
    <v-card
      class="ma-2 d-flex justify-center align-center"
      height="140"
      width="300"
      rounded="lg"
      :color="item.isDeposit ? 'child01' : 'child04'"
      @click="goToPage"
    >
      <div class="text-center">
        <div>예금</div>
        <h1 class="text-h4 black--text font-weight-bold my-1">{{ this.item.balance }}</h1>
        <div>단추</div>
      </div>
    </v-card>
  </div>
</template>

<script>
export default {
  name: "AccountCard",
  props: {
    item: Object,
  },
  methods: {
    goToPage() {
      this.$router.push(this.item.isDeposit ? '/child/account-history/deposit' : '/child/account-history/installment');
    },
  },
};
</script>

<style></style>
