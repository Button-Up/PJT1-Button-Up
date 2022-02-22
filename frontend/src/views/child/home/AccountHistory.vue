<!--
author: 유현수

modified: 김지언 - 계좌 잔액 표시, 계좌 내역 표시
modified: 우정연 - 계좌 내역 정렬 적용
-->

<template>
  <div>
    <!-- 단추 잔액 & 환전 요청 영역 -->
    <v-sheet
      :color="accountColor"
      width="100%"
      height="180"
      class="d-flex justify-center align-center"
    >
      <div>
        <!-- 단추 잔액 -->
        <h2 class="text-center">
          {{
            isDeposit
              ? getDefaultBalance.toLocaleString(locales, options)
              : getBalance.toLocaleString(locales, options)
          }}
          단추
        </h2>

        <!-- 입금하기 버튼 & 바텀시트 -->
        <SavingHistoryBtmSheet
          v-if="!isDeposit"
          @updateSavingHistory="updateSavingHistory"
        ></SavingHistoryBtmSheet>
        <!-- 환전 요청 or 적금 해지 버튼 & 바텀시트 -->
        <AccountHistoryBtmSheet :isDeposit="isDeposit"></AccountHistoryBtmSheet>
      </div>
    </v-sheet>

    <!-- 필터 -->
    <v-row v-if="isDeposit" justify="end" class="mx-0">
      <v-col cols="4">
        <v-select
          :color="accountColor"
          item-color="child01"
          v-model="filterSelect"
          :items="historyFilter"
          @change="onChange($event)"
        ></v-select>
      </v-col>
    </v-row>

    <!-- 적금 만기일 그래프 -->
    <v-sheet v-if="!isDeposit">
      <v-sheet class="mx-5 my-5 pa-5" elevation="1" rounded="lg">
        <h3>적금 만기까지 {{ getSavingAccountDetail.restDate }}일!</h3>
        <v-progress-linear
          class="mt-4"
          color="child04"
          rounded
          height="6"
          :value="calcProgress"
        ></v-progress-linear>
        <div class="d-flex justify-space-between mt-1">
          <div>{{ startDate }}</div>
          <div>{{ endDate }}</div>
        </div>
        <div v-if="getBalance != null">
          <v-divider class="my-3"></v-divider>
          <div class="d-flex justify-space-between">
            <div>만기 시 이자</div>
            <div class="red--text font-weight-bold">+ {{ Math.ceil(getBalance * 0.05) }} 단추</div>
          </div>
        </div>
      </v-sheet>
    </v-sheet>

    <v-subheader v-if="!isDeposit">입금내역</v-subheader>

    <!-- 거래내역 리스트 -->
    <AccountHistoryList
      :isDeposit="isDeposit"
      :accountHistories="accountHistories"
    ></AccountHistoryList>
  </div>
</template>

<script>
import AccountHistoryBtmSheet from "@/components/child/home/AccountHistoryBtmSheet.vue";
import SavingHistoryBtmSheet from "@/components/child/home/SavingHistoryBtmSheet.vue";
import AccountHistoryList from "@/components/child/home/AccountHistoryList.vue";
import { mapActions, mapGetters } from "vuex";

export default {
  name: "AccountHistory",
  components: {
    AccountHistoryBtmSheet,
    AccountHistoryList,
    SavingHistoryBtmSheet,
  },
  props: {
    isDeposit: {
      type: Boolean,
      default: true,
    },
  },
  data() {
    return {
      exchangeAmount: null,
      balance: 25000,
      filterSelect: "전체",
      historyFilter: ["전체", "입금", "출금"],
      accountHistories: [],
      filter: "전체",
    };
  },
  computed: {
    // 예금 getters
    ...mapGetters("accountStore", ["getDefaultBalance", "getAccountList"]),
    // 적금 getters
    ...mapGetters("savingStore", ["getBalance", "getSavingAccountDetail"]),
    ...mapGetters("userStore", ["checkUserInfo"]),
    accountColor() {
      return this.isDeposit ? "child01" : "child04";
    },
    // 오늘 기준 적금 만기일까지 진행 상황
    calcProgress() {
      return ((90 - this.getSavingAccountDetail.restDate) / 90) * 100;
    },
    startDate() {
      const date = new Date(this.getSavingAccountDetail.createdDate);
      return date.toLocaleDateString();
    },
    endDate() {
      const date = new Date(this.getSavingAccountDetail.endDate);
      return date.toLocaleDateString();
    },
  },
  methods: {
    ...mapActions({ accountStore: ["vuexUpdateDefaultBalance", "vuexFetchAccountHistory"] }),
    ...mapActions({ savingStore: ["vuexGetSavingDetails"] }),
    // 필터 적용(전체, 입금, 출금)
    onChange(event) {
      this.filter = event;
      this.sortByFilter();
    },
    // 필터 정렬
    sortByFilter() {
      if (this.filter === "전체") this.accountHistories = this.getAccountList;
      else
        this.accountHistories = this.getAccountList.filter(
          (account) => account.type === this.filter
        );
    },
    updateSavingHistory() {
      this.$store.dispatch("savingStore/vuexGetSavingDetails", this.checkUserInfo.seq);
      this.accountHistories = this.getSavingAccountDetail.histories;
    },
  },
  watch: {
    getAccountList: {
      handler: function () {
        this.accountHistories = this.getAccountList;
        this.sortByFilter();
      },
      deep: true,
    },
  },
  mounted() {
    if (this.isDeposit) {
      this.$store.dispatch("accountStore/vuexUpdateDefaultBalance", this.checkUserInfo.seq);
      this.$store.dispatch("accountStore/vuexFetchAccountHistory", this.checkUserInfo.seq);
      this.accountHistories = this.getAccountList;
    } else {
      this.updateSavingHistory();
    }
  },
};
</script>

<style></style>
