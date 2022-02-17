<!--
author: 유현수
-->

<template>
  <div>
    <BottomSheet
      btnColor="child06"
      btnName="입금하기"
      btnClass="font-weight-bold mt-3"
      sheetHeight="260px"
      customBtnWidth="120px"
      :closeSheet="closeSheet"
      @sheetClosed="closeSheet = false"
    >
      <template v-slot:body>
        <v-card class="rounded-0" :elevation="0">
          <v-toolbar color="child04" dark :elevation="0" class="black--text">입금하기</v-toolbar>
          <div class="mx-4">
            <div class="mt-6">
              <h3>적금 통장에 얼마를 입금할까요?</h3>
              <v-row class="mt-1">
                <v-col cols="8">
                  <v-text-field
                    v-model="depositAmount"
                    label="입금할 금액을 입력해주세요."
                    required
                    color="child04"
                    type="number"
                  ></v-text-field>
                </v-col>
                <v-col cols="4" align-self="center">
                  <h3 class="text-center">단추</h3>
                </v-col>
              </v-row>
            </div>
            <v-btn block color="child04" class="font-weight-bold" @click="addSavingHistory"
              >입금하기</v-btn
            >
          </div>
        </v-card>
      </template>
    </BottomSheet>
    <!-- 스낵바 -->
    <v-snackbar app v-model="snackbar.isOpen" :timeout="snackbar.timeout" color="child05">
      <span class="black--text font-weight-bold">입금이 완료되었습니다.</span>
      <template v-slot:action="{ attrs }">
        <v-btn
          color="red"
          text
          v-bind="attrs"
          @click="snackbar.isOpen = false"
          class="font-weight-bold"
        >
          닫기
        </v-btn>
      </template>
    </v-snackbar>
  </div>
</template>

<script>
import BottomSheet from "@/components/common/BottomSheet.vue";
import { addAccountWithdraw } from "../../../api/accountsAPI";
import { mapGetters, mapActions } from "vuex";

export default {
  name: "SavingHistoryBtmSheet",
  components: {
    BottomSheet,
  },
  data() {
    return {
      depositAmount: null,
      isWithDrawDone: false,
      closeSheet: false,
      snackbar: {
        isOpen: false,
        text: null,
        timeout: 2000,
      },
    };
  },
  computed: {
    ...mapGetters("userStore", ["checkUserInfo"]),
  },
  methods: {
    ...mapActions("savingStore", [
      "vuexPostAddSavingHistory",
      "vuexGetSavingDetails",
      "vuexGetSavingBalance",
    ]),
    // 적금 입금
    async addSavingHistory() {
      // 예금 출금 정보
      const formData = {
        category: "적금",
        childSeq: this.checkUserInfo.seq,
        content: "",
        money: this.depositAmount,
      };
      // 예금에서 출금
      await addAccountWithdraw(
        formData,
        async () => {
          console.log("출금 성공");
          const formData = {
            childSeq: this.checkUserInfo.seq,
            transferAmount: this.depositAmount,
          };
          await this.vuexPostAddSavingHistory(formData);
          await this.vuexGetSavingBalance(this.checkUserInfo.seq);
          await this.vuexGetSavingDetails(this.checkUserInfo.seq);
          // alert("적금 통장으로 입금이 완료되었습니다.");
          this.snackbar.isOpen = true;
        },
        (err) => {
          console.log(err);
          alert("잔액이 부족합니다.");
        }
      );
      this.closeSheet = true;
      this.depositAmount = null;
    },
  },
};
</script>
