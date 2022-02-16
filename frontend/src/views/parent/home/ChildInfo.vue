<!--
  author : 정은이

  modified: 김지언 - 자녀 단추 잔액 api 연결
-->
<template>
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
        >
          <template v-slot:body>
            <Deposit :childSeq="child.seq"></Deposit>
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
      <JobWithTodoListCard :isParent="true" :job="job" :child="child"></JobWithTodoListCard>
    </div>
  </div>
</template>

<script>
import BottomSheet from '@/components/common/BottomSheet';
import JobWithTodoListCard from '../../../components/common/JobWithTodoListCard.vue';
import ChildAccount from '../../../components/parent/home/ChildAccount.vue';
import Deposit from '../../../components/parent/home/Deposit.vue';
import { mapGetters } from 'vuex';
import { apiGetChildsJob } from '@/api/jobsAPI.js';
import { apiGetSavingsBalance } from '@/api/savingsAPI.js';

export default {
  name: 'ChildInfo',
  components: { BottomSheet, JobWithTodoListCard, ChildAccount, Deposit },
  props: {
    child: Object,
  },
  data() {
    return {
      job: {},
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
  },
  computed: {
    ...mapGetters('accountStore', ['getDefaultBalance']),

    accounts() {
      var accounts = [];
      accounts.push({
        isDeposit: true,
        amount: this.getDefaultBalance,
      });
      return accounts;
    },
  },
  mounted() {
    this.$store.dispatch('accountStore/vuexUpdateDefaultBalance', this.child.seq);
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
            this.accounts.push(saving);
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
};
</script>

<style scoped>
#space-between {
  justify-content: space-between;
}
</style>
