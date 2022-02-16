<!--
author: 김응철

modified: 유현수 - 마크업 & 스타일링
modified: 김지언 - 예금 계좌 잔액 표시
modified: 우정연 - 아이 체크리스트 조회 및 직업 조회
-->

<template>
  <div>
    <NotSync v-if="checkNotSync"></NotSync>
    <div v-else>
      <!-- 단추 계좌 슬라이드 -->
      <h2 class="mx-6 mt-4">나의 단추 계좌</h2>
      <v-sheet elevation="">
        <v-slide-group class="mt-2 mx-4" center-active>
          <v-slide-item>
            <MainAccountCard
              :item="{
                isDeposit: true,
                balance: this.getDefaultBalance,
              }"
            ></MainAccountCard>
          </v-slide-item>

          <!-- 적금 계좌 -->
          <v-slide-item v-if="getSavingStatus">
            <MainAccountCard
              :item="{
                isDeposit: false,
                balance: getBalance,
              }"
            ></MainAccountCard>
          </v-slide-item>
        </v-slide-group>
      </v-sheet>

      <!-- 오늘 할 일 -->
      <div class="mx-6 mt-6">
        <v-row class="mb-4" align="center">
          <v-col class="col-8 text-left"><h2>오늘 할 일</h2></v-col>
          <v-col class="col-4 text-center"
            ><h3>{{ job.name }}</h3></v-col
          >
        </v-row>
        <v-list-item
          v-for="(todo, t) in todoList"
          :key="t"
          class="mb-2 py-2"
          style="display: contents"
        >
          <todo-list :todo="todo" :isParent="false"></todo-list>
        </v-list-item>
      </div>
    </div>
  </div>
</template>

<script>
import NotSync from "@/components/child/NotSync";
import MainAccountCard from "@/components/child/home/MainAccountCard";
import TodoList from "@/components/common/TodoList.vue";
import { apiGetCheckListRow } from "@/api/checkListAPI";
import { apiGetChildsJob } from "@/api/jobsAPI";
import { mapActions, mapGetters } from "vuex";

export default {
  name: "Home",
  components: {
    NotSync,
    MainAccountCard,
    TodoList,
  },
  data() {
    return {
      todoList: [],
      job: {},
    };
  },
  created() {
    this.getList();
    this.getChildJob();
  },
  computed: {
    ...mapGetters("accountStore", ["getDefaultBalance"]),
    ...mapGetters("userStore", ["checkUserInfo", "checkNotSync"]),
    ...mapGetters("savingStore", ["getSavingStatus", "getBalance"]),
  },
  mounted() {
    // 예금 잔액 vuex 업데이트
    this.$store.dispatch("accountStore/vuexUpdateDefaultBalance", this.checkUserInfo.seq);
    // 적금 잔액 & 존재 여부 vuex 업데이트
    this.$store.dispatch("savingStore/vuexGetSavingBalance", this.checkUserInfo.seq);
  },
  methods: {
    ...mapActions({ accountStore: ["updateDefaultBalance", "vuexFetchAccountHistory"] }),
    getList() {
      this.todoList = [];
      apiGetCheckListRow(this.checkUserInfo.seq, ({ data }) => {
        this.todoList = data;
        console.log(this.todoList);
      });
    },
    getChildJob() {
      apiGetChildsJob(this.checkUserInfo.seq, ({ data }) => {
        this.job = data;
      });
    },
  },
};
</script>

<style></style>
