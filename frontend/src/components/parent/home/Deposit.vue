<!--
  author: Jiun Kim
  created on 2022-02-08
-->
<template>
  <v-card class="rounded-0" :elevation="0">
    <v-toolbar color="parent02" class="font-weight-bold" :elevation="0"> 입금하기 </v-toolbar>
    <v-subheader>입금할 단추 개수를 입력해주세요.</v-subheader>
    <v-form class="mx-4">
      <v-text-field label="단추" suffix="개" color="parent01" v-model="money"></v-text-field>
    </v-form>
    <v-card-actions>
      <v-btn block color="parent01" outlined rounded text @click="addAccount"> 입금 </v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
import { mapActions } from "vuex";
export default {
  name: "Deposit",
  props: {
    childSeq: Number,
  },
  data() {
    return {
      money: null,
    };
  },
  methods: {
    ...mapActions({ accountStore: ["vuexAddAccountHistoryDeposit"] }),
    addAccount() {
      this.$store.dispatch("accountStore/vuexAddAccountHistoryDeposit", {
        childSeq: this.childSeq,
        money: this.money,
      });
      this.money = null;
    },
  },
};
</script>

<style></style>
