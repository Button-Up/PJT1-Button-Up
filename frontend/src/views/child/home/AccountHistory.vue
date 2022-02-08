<!--
author: 유현수
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
        <h2>{{ balance }} 단추</h2>

        <!-- 환전 요청 버튼 & 바텀시트 -->
        <AccountHistoryBtmSheet
          :isDeposit="isDeposit"
        ></AccountHistoryBtmSheet>
      </div>
    </v-sheet>

    <!-- 필터 -->
    <v-row
      v-if="isDeposit"
      justify="end"
      class="mx-0"
    >
      <v-col cols="4">
        <v-select
          :color="accountColor"
          item-color="child01"
          v-model="filterSelect"
          :items="historyFilter"
        ></v-select>
      </v-col>
    </v-row>

    <!-- 거래내역 리스트 -->
    <AccountHistoryList
      :isDeposit="isDeposit"
      :accountHistories="accountHistories"
    ></AccountHistoryList>
  </div>
</template>

<script>
import AccountHistoryBtmSheet from "@/components/child/home/AccountHistoryBtmSheet.vue"
import AccountHistoryList from "@/components/child/home/AccountHistoryList.vue"

export default {
  name: "AccountHistory",
  components: {
    AccountHistoryBtmSheet,
    AccountHistoryList
  },
  props: {
    isDeposit: {
      type: Boolean,
      default: true
    }
  },
  data() {
    return {
      exchangeAmount: null,
      balance: 25000,
      filterSelect: '전체',
      historyFilter: [ '전체', '입금', '출금' ],
      accountHistories: [
        {
          balance: 34000,
          category: '급여',
          content: '지급 완료',
          date: '22.02.08',
          money: 30000,
          type: '입금'
        },
        {
          balance: 4000,
          category: '환전',
          content: null,
          date: '22.02.08',
          money: 6000,
          type: '출금'
        },
      ],
    }
  },
  computed: {
    accountColor() {
      return this.isDeposit ? 'child01' : 'child04'
    }
  }
};
</script>

<style>

</style>
