<!--
  author : 우정연
-->
<template>
  <BottomSheet
    :btnColor="`child01`"
    :btnName="isBuy ? '구매하기' : '판매하기'"
    btnClass="font-weight-bold mt-3"
    sheetHeight="280px"
    :isBlockBtn="true"
  >
    <template v-slot:body>
      <v-card class="rounded-0" :elevation="0">
        <v-toolbar :color="isBuy ? 'child01' : 'child04'" dark :elevation="0" class="black--text">{{
          isBuy ? "주식 구매" : "주식 판매"
        }}</v-toolbar>
        <div class="mx-4">
          <div v-if="isBuy" class="mt-4">
            <h3>{{ name }}에 투자해볼까요?</h3>
            <p>구매할 주식 개수를 입력하세요.</p>
            <v-row>
              <v-col cols="6">
                <v-text-field
                  v-model="investAmount"
                  label="구매할 주식 개수"
                  required
                  :color="`child01`"
                  type="number"
                ></v-text-field>
              </v-col>
              <v-col cols="2" align-self="center">
                <v-icon>mdi-arrow-right</v-icon>
              </v-col>
              <v-col cols="4" align-self="center">
                <h3 class="text-center">{{ totalPrice }}원</h3>
              </v-col>
            </v-row>
          </div>

          <div v-else class="mt-4">
            <h3>{{ name }} 주식을 팔아볼까요?</h3>
            <p>판매할 주식 개수를 입력하세요.</p>
            <v-row>
              <v-col cols="6">
                <v-text-field
                  v-model="investAmount"
                  label="판매할 주식 개수"
                  required
                  :color="`child04`"
                  type="number"
                ></v-text-field>
              </v-col>
              <v-col cols="2" align-self="center">
                <v-icon>mdi-arrow-right</v-icon>
              </v-col>
              <v-col cols="4" align-self="center">
                <h3 class="text-center">{{ totalPrice }}원</h3>
              </v-col>
            </v-row>
          </div>

          <v-btn
            block
            :color="isBuy ? 'child01' : 'child04'"
            class="font-weight-bold"
            @click="requestExchange()"
            >{{ isBuy ? "구매하기" : "판매하기" }}</v-btn
          >
        </div>
      </v-card>
    </template>
  </BottomSheet>
</template>

<script>
import BottomSheet from "@/components/common/BottomSheet.vue";
import { apiPutInvestStatus } from "@/api/investAPI";

export default {
  components: {
    BottomSheet,
  },
  computed: {
    totalPrice: function () {
      return this.investAmount * this.price;
    },
  },
  methods: {
    requestExchange() {
      let info = {
        count: this.isBuy ? this.investAmount : -this.investAmount,
        price: this.price,
        seq: this.investStatusSeq,
      };
      console.log(info);
      apiPutInvestStatus(
        info,
        () => {
          this.$emit("getData", this.investStatusSeq);
        },
        (error) => {
          console.log(error);
          alert(
            this.isBuy ? "잔액이 부족합니다!" : "보유 주식 수보다 초과해서 판매할 수 없습니다!"
          );
        }
      );
    },
  },
  data() {
    return {
      investAmount: 0,
    };
  },
  props: {
    isBuy: {
      type: Boolean,
      default: true,
    },
    price: {
      type: Number,
    },
    name: {
      type: String,
    },
    investStatusSeq: {
      type: Number,
    },
  },
};
</script>

<style></style>
