<!--
  author : 정은이

  modified: 김지언 - 자녀 단추 잔액 api 연결
-->
<template>
  <div>
    <div class="mx-6 mt-6">
      <!-- 아이의 단추 계좌 START -->
      <div>
        <v-row id="space-between" class="ma-0 align-center">
          <h2>아이의 단추 계좌</h2>
          <BottomSheet
            btnColor="parent01"
            btnName="입금하기"
            btnClass="white--text"
            :isSmallBtn="true"
            :closeSheet="closeSheet"
            @sheetClosed="closeSheet = false"
          >
            <template v-slot:body>
              <Deposit
                :childSeq="child.seq"
                @clickDeposit="[changeCloseSheet(), openSnackbar()]"
              ></Deposit>
            </template>
          </BottomSheet>
        </v-row>
        <v-row>
          <v-col v-for="(account, a) in accounts" :key="a" class="mt-2 pa-1 align-center">
            <!-- <ChildAccount :account="account"></ChildAccount> -->
            <ChildAccount :isDeposit="account.isDeposit" :amount="account.amount"></ChildAccount>
          </v-col>
        </v-row>
        <v-list-item> </v-list-item>
      </div>
      <!-- 아이의 단추 계좌 END -->
      <div>
        <h2>아이의 할 일 진행 상황</h2>
        <JobWithTodoListCard
          :isParent="true"
          :doList="doList"
          :job="job"
          :child="child"
        ></JobWithTodoListCard>
      </div>
    </div>
    <!-- 스낵바 -->
    <v-snackbar app v-model="snackbar.isOpen" :timeout="snackbar.timeout" color="parent02">
      <span class="black--text font-weight-bold">입금이 완료되었습니다.</span>
      <template v-slot:action="{ attrs }">
        <v-btn
          color="black"
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
import BottomSheet from "@/components/common/BottomSheet";
import JobWithTodoListCard from "../../../components/common/JobWithTodoListCard.vue";
import ChildAccount from "../../../components/parent/home/ChildAccount.vue";
import Deposit from "../../../components/parent/home/Deposit.vue";
import { mapGetters } from "vuex";
import { apiGetChildsJob } from "@/api/jobsAPI.js";
import { apiGetSavingsBalance } from "@/api/savingsAPI.js";
import { apiGetCheckListRow } from "@/api/checkListAPI.js";

export default {
  name: "ChildInfo",
  components: { BottomSheet, JobWithTodoListCard, ChildAccount, Deposit },
  props: {
    child: Object,
  },
  data() {
    return {
      job: {},
      doList: [],
      closeSheet: false,
      snackbar: {
        isOpen: false,
        text: null,
        timeout: 2000,
      },
      saving: null,
    };
  },
  created() {
    apiGetChildsJob(
      this.child.seq,
      (response) => {
        this.job = response.data;
      },
      (error) => {
        console.log(error);
      }
    );
    this.getSaving();
    apiGetCheckListRow(
      this.child.seq,
      (response) => {
        this.doList = response.data;
      },
      (error) => {
        console.log(error);
      }
    );
  },
  computed: {
    ...mapGetters("accountStore", ["getDefaultBalance"]),

    accounts() {
      var accounts = [];
      accounts.push({
        isDeposit: true,
        amount: this.getDefaultBalance,
      });
      if (this.saving != null) accounts.push(this.saving);
      return accounts;
    },
  },
  mounted() {
    this.$store.dispatch("accountStore/vuexUpdateDefaultBalance", this.child.seq);
  },
  methods: {
    getSaving() {
      apiGetSavingsBalance(
        this.child.seq,
        (response) => {
          console.log(response);
          var savingAccount = response.data;
          if (savingAccount.stateType) {
            var saving = {
              isDeposit: false,
              amount: response.data.balance,
            };
            console.log(saving);
            this.accounts.push(saving);
            this.saving = saving;
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },
    openSnackbar() {
      console.log("openSnackbar");
      this.snackbar.isOpen = true;
    },
    changeCloseSheet() {
      console.log("changeCloseSheet");
      this.closeSheet = true;
    },
  },
};
</script>

<style scoped>
#space-between {
  justify-content: space-between;
}
</style>
