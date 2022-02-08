<template>
  <div>
    <!-- 단추 잔액 & 환전 요청 버튼 -->
    <v-sheet
      :color="isDeposit ? 'child01' : 'child04'"
      width="100%"
      height="180"
      class="d-flex justify-center align-center"
    >
      <div>
        <h2>{{ balance }} 단추</h2>
        <bottom-sheet
          :btnColor="isDeposit ? 'child02' : 'child06'"
          btnName="환전 요청하기"
          btnClass="font-weight-bold mt-3"
        ></bottom-sheet>
      </div>
    </v-sheet>

    <!-- 필터 -->
    <v-row
      v-if="isDeposit"
      justify="end"
    >
      <v-col cols="4">
        <v-select
          :color="isDeposit ? 'child01' : 'child04'"
          item-color="child01"
          v-model="filterSelect"
          :items="historyFilter"
          class="mr-4"
        ></v-select>
      </v-col>
    </v-row>

    <!-- 거래내역 리스트 -->
    <v-list two-line>
      <v-divider v-if="isDeposit"></v-divider>
      <template
        v-for="(accountHistory, idx) in accountHistories"
      >
        <v-list-item :key="`item-${idx}`" three-line>
          <div>
            <div class="d-flex align-center">
              <span class="mr-2 gray--text">{{ accountHistory.category }}</span>
              <span class="text-caption">{{ accountHistory.content }}</span>
            </div>
            <v-list-item-subtitle>{{ accountHistory.date }}</v-list-item-subtitle>
          </div>
          <v-list-item-content class="text-right">
            <v-list-item-title
              :class="accountHistory.type === '입금' ? 'blue--text' : 'red--text'"
              class="font-weight-bold"
            >{{ accountHistory.type === '입금' ? accountHistory.money : -accountHistory.money }} 단추</v-list-item-title>
            <v-list-item-subtitle>{{ accountHistory.balance }} 단추</v-list-item-subtitle>
          </v-list-item-content>
        </v-list-item>
        <v-divider
          v-if="idx < accountHistories.length"
          :key="`divider-${idx}`"
        ></v-divider>
      </template>
    </v-list>
  </div>
</template>

<script>
import BottomSheet from "../common/BottomSheet.vue";

export default {
  name: "AccountHistory",
  components: {
    BottomSheet,
  },
  props: {
    isDeposit: {
      type: Boolean,
      default: true
    }
  },
  data() {
    return {
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
};
</script>

<style>

</style>
