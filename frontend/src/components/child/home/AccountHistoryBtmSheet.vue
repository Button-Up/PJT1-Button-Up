<!--
author: 유현수
-->

<template>
  <BottomSheet
    :btnColor="isDeposit ? 'child02' : 'child06'"
    :btnName="isDeposit? '환전 요청하기' : '적금 해지하기'"
    btnClass="font-weight-bold mt-3"
    sheetHeight="280px"
  >
    <template v-slot:body>
      <v-card
        class="rounded-0"
        :elevation="0"
      >
        <v-toolbar
          :color="isDeposit ? 'child01' : 'child04'"
          dark
          :elevation="0"
          class="black--text"
        >{{ isDeposit ? '환전 요청' : '적금 해지' }}</v-toolbar>
        <div class="mx-4">
          <div
            v-if="isDeposit"
            class="mt-4"
          >
            <h3>단추를 현금으로 바꿔볼까요?</h3>
            <p>환전 요청할 금액을 입력해주세요.</p>
            <v-row>
              <v-col cols="6">
                <v-text-field
                  v-model="exchangeAmount"
                  label="환전할 단추 개수"
                  required
                  :color="isDeposit ? 'child01' : 'child04'"
                ></v-text-field>
              </v-col>
              <v-col cols="2" align-self="center">
                <v-icon>mdi-arrow-right</v-icon>
              </v-col>
              <v-col cols="4" align-self="center">
                <h3 class="text-center">{{ exchangeAmount }}원</h3>
              </v-col>
            </v-row>
          </div>
          <div
            v-else
            class="mt-4"
          >
            <h3>정말 해지하시겠어요?</h3>
            <p>지금까지 쌓인 이자를 받을 수 없게 됩니다.</p>
          </div>
          <v-btn
            block
            :color="isDeposit ? 'child01' : 'child04'"
            class="font-weight-bold"
          >{{ isDeposit ? '환전 요청하기' : '적금 해지하기' }}</v-btn>
        </div>
      </v-card>
    </template>
  </BottomSheet>
</template>

<script>
import BottomSheet from "@/components/common/BottomSheet.vue";

export default {
  name: 'AccountHistoryBtmSheet',
  components: {
    BottomSheet
  },
  props: {
    isDeposit: {
      type: Boolean,
      default: true
    }
  },
  data() {
    return {
      exchangeAmount: null
    }
  }
}
</script>

<style>

</style>