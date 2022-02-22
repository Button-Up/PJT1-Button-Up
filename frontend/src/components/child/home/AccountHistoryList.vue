<!--
author: 유현수
  modified : 우정연 - 환전요청시 입출금 리스트 업데이트되도록 + 필터링 적용
-->

<template>
  <v-list two-line>
    <v-divider></v-divider>
    <template
      v-for="(accountHistory, idx) in isDeposit
        ? accountHistories
        : getSavingAccountDetail.histories"
    >
      <v-list-item :key="`item-${idx}`" three-line>
        <div>
          <div class="d-flex align-center">
            <!-- 카테고리 -->
            <span class="mr-2 gray--text">{{
              isDeposit ? accountHistory.category : accountHistory.catetory
            }}</span>
            <!-- 이벤트(content) -->
            <span v-if="isDeposit" class="text-caption">{{ accountHistory.content }}</span>
          </div>
          <!-- 날짜 -->
          <v-list-item-subtitle>{{ accountHistory.date }}</v-list-item-subtitle>
        </div>
        <v-list-item-content class="text-right">
          <!-- 예금 입출금 금액 -->
          <v-list-item-title
            v-if="isDeposit"
            :class="accountHistory.type === '입금' ? 'blue--text' : 'red--text'"
            class="font-weight-bold"
            >{{
              accountHistory.type === "입금"
                ? accountHistory.money.toLocaleString(locales, options)
                : (-accountHistory.money).toLocaleString(locales, options)
            }}
            단추</v-list-item-title
          >
          <!-- 적금 입금 금액 -->
          <v-list-item-title v-else class="font-weight-bold blue--text"
            >{{ accountHistory.money.toLocaleString(locales, options) }}단추</v-list-item-title
          >
          <!-- 잔액 -->
          <v-list-item-subtitle
            >{{
              accountHistory.balance.toLocaleString(locales, options)
            }}
            단추</v-list-item-subtitle
          >
        </v-list-item-content>
      </v-list-item>
      <v-divider v-if="idx < accountHistories.length" :key="`divider-${idx}`"></v-divider>
    </template>
  </v-list>
</template>

<script>
import { mapGetters } from "vuex";

export default {
  name: "AccountHistoryList",
  props: {
    isDeposit: {
      type: Boolean,
      default: true,
    },
    accountHistories: {
      type: Array,
      default: null,
    },
  },
  computed: {
    ...mapGetters("savingStore", ["getSavingAccountDetail"]),
  },
};
</script>

<style></style>
