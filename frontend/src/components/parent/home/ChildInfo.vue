<!--
  author : 정은이
-->
<template>
  <div class="mx-6 mt-6">
    <!-- 아이의 단추 계좌 START -->
    <div>
      <v-row id="space-between" class="ma-0 align-center">
        <h2>아이의 단추 계좌</h2>
        <bottom-sheet
          btnColor="parent01"
          btnName="입금하기"
          btnClass="white--text"
          :isSmallBtn="true"
        >
          <template v-slot:body>
            <deposit></deposit>
          </template>
        </bottom-sheet>
      </v-row>
      <v-row>
        <v-col v-for="(account, a) in accounts" :key="a" class="mt-2 pa-1 align-center">
          <child-account :account="account"></child-account>
        </v-col>
      </v-row>
      <v-list-item> </v-list-item>
    </div>
    <!-- 아이의 단추 계좌 END -->
    <div>
      <h2>아이의 할 일 진행 상황</h2>
      <job-with-todo-list-card
        :isParent="true"
        :job="job"
        :checkboxOff="false"
      ></job-with-todo-list-card>
    </div>
  </div>
</template>

<script>
import BottomSheet from "@/components/common/BottomSheet";
import JobWithTodoListCard from "../../common/JobWithTodoListCard.vue";
import ChildAccount from "./ChildAccount.vue";
import Deposit from "./Deposit.vue";

export default {
  name: "ChildInfo",
  components: { BottomSheet, JobWithTodoListCard, ChildAccount, Deposit },
  data() {
    return {
      accounts: [
        {
          isDeposit: false,
          amount: "25,000",
        },
        // {
        //   isDeposit:false,
        //   amount:"15,000"
        // },
      ],
      job: {
        name: "청소부",
        image: "https://cdn.vuetifyjs.com/images/john.jpg",
        TodoList: [
          {
            done: false,
            task: "투자 가격 업데이트",
            url: "/parent/activity",
          },
          {
            done: true,
            task: "투자 가격 업데이트",
            url: "/parent/userinfo",
          },
          {
            done: true,
            task: "투자 가격 업데이트",
            url: "/parent/userinfo",
          },
        ],
      },
    };
  },
};
</script>

<style scoped>
#space-between {
  justify-content: space-between;
}
</style>
