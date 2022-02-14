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
        <h2 class="text-center">{{ getDefaultBalance }} 단추</h2>

        <!-- 환전 요청 버튼 & 바텀시트 -->
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
      <div class="pa-5">
        <h3>적금 만기까지 {{ calcDDay }}일!</h3>
        <v-progress-linear
          class="mt-4"
          color="child04"
          rounded
          height="6"
          :value="calcProgress"
        ></v-progress-linear>
        <div class="d-flex justify-space-between mt-1">
          <div>{{ savingInfo.startDate }}</div>
          <div>{{ savingInfo.endDate }}</div>
        </div>
        <div v-if="getDefaultBalance">
          <v-divider class="my-3"></v-divider>
          <div class="d-flex justify-space-between">
            <div>만기 시 이자</div>
            <div class="red--text font-weight-bold">
              + {{ Math.ceil(getDefaultBalance * 0.05) }} 단추
            </div>
          </div>
        </div>
      </div>
    </v-sheet>

    <!-- 거래내역 리스트 -->
    <AccountHistoryList
      :isDeposit="isDeposit"
      :accountHistories="accountHistories"
    ></AccountHistoryList>
  </div>
</template>

<script>
import AccountHistoryBtmSheet from "@/components/child/home/AccountHistoryBtmSheet.vue";
import AccountHistoryList from "@/components/child/home/AccountHistoryList.vue";
import { mapActions, mapGetters } from "vuex";

export default {
  name: "AccountHistory",
  components: {
    AccountHistoryBtmSheet,
    AccountHistoryList,
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
      // 적금 정보
      savingInfo: {
        startDate: "2022-02-14",
        endDate: "2022-05-15",
      },
    };
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
  computed: {
    ...mapGetters("accountStore", ["getDefaultBalance", "getAccountList"]),
    ...mapGetters("userStore", ["checkUserInfo"]),
    accountColor() {
      return this.isDeposit ? "child01" : "child04";
    },
    // 적금 만기일까지 남은 날짜
    calcDDay() {
      const startDate = new Date(this.savingInfo.startDate);
      const endDate = new Date(this.savingInfo.endDate);
      const dateDiff = Math.ceil((endDate.getTime() - startDate.getTime()) / (1000 * 3600 * 24));
      return dateDiff;
    },
    // 오늘 기준 적금 만기일까지 진행 상황
    calcProgress() {
      return ((90 - this.calcDDay) / 90) * 100;
    },
  },
  mounted() {
    this.$store.dispatch("accountStore/vuexUpdateDefaultBalance", this.checkUserInfo.seq);
    this.$store.dispatch("accountStore/vuexFetchAccountHistory", this.checkUserInfo.seq);
    this.accountHistories = this.getAccountList;
  },
  methods: {
    ...mapActions({ accountStore: ["vuexUpdateDefaultBalance", "vuexFetchAccountHistory"] }),
    // 필터 적용(전체, 입금, 출금)
    onChange(event) {
      this.filter = event;
      this.sortByFilter();
    },
    sortByFilter() {
      if (this.filter === "전체") this.accountHistories = this.getAccountList;
      else
        this.accountHistories = this.getAccountList.filter(
          (account) => account.type === this.filter
        );
    },
  },
};
</script>

<style></style>
