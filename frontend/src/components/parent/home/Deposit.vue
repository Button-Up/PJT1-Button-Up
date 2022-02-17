<!--
  author: Jiun Kim
  created on 2022-02-08
  modified : 우정연 - 입금시 type, content 추가
-->
<template>
  <div>
    <v-card class="rounded-0" :elevation="0">
      <v-toolbar color="parent01" class="white--text" :elevation="0"> 입금하기 </v-toolbar>
      <div class="pa-3">
        <v-subheader>입금할 단추 개수를 입력해주세요.</v-subheader>
        <v-form class="mx-4">
          <v-text-field label="단추" suffix="개" color="parent01" v-model="money"></v-text-field>
        </v-form>
        <v-card-actions>
          <v-btn
            block
            color="parent01"
            @click="[addAccount(), $emit('clickDeposit')]"
            class="white--text"
          >
            입금
          </v-btn>
        </v-card-actions>
      </div>
    </v-card>
  </div>
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
        category: "입금",
        childSeq: this.childSeq,
        content: "입금 완료",
        money: this.money,
      });
      this.money = null;
    },
  },
};
</script>

<style></style>
