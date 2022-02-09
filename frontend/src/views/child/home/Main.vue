<!--
author: 김응철

modified: 유현수 - 마크업 & 스타일링
modified: 김지언 - 예금 계좌 잔액 표시
-->

<template>
  <div>
    <!-- 단추 계좌 슬라이드 -->
    <h2 class="mx-6 mt-4">나의 단추 계좌</h2>
    <v-sheet elevation="">
      <v-slide-group class="mt-2 mx-4" center-active show-arrows="false">
        <v-slide-item>
          <MainAccountCard
            :item="{
              isDeposit: true,
              balance: this.getDefaultBalance,
            }"
          ></MainAccountCard>
        </v-slide-item>

        <!-- 적금 계좌 -->
        <v-slide-item>
          <MainAccountCard
            :item="{
              isDeposit: false,
              balance: 6000,
            }"
          ></MainAccountCard>
        </v-slide-item>
      </v-slide-group>
    </v-sheet>

    <!-- 오늘 할 일 -->
    <div class="mx-6 mt-6">
      <h2 class="mb-4">오늘 할 일</h2>
      <v-list-item
        v-for="(todo, t) in TodoList"
        :key="t"
        class="mb-2 py-2"
        style="display: contents"
      >
        <todo-list :todo="todo" :isParent="false"></todo-list>
      </v-list-item>
    </div>
  </div>
</template>

<script>
import MainAccountCard from "@/components/child/home/MainAccountCard";
import TodoList from "@/components/common/TodoList.vue";
import { mapActions, mapGetters } from "vuex";

export default {
  name: "Home",
  components: {
    MainAccountCard,
    TodoList,
  },
  data() {
    return {
      TodoList: [
        {
          done: false,
          task: "투자 가격 업데이트 1",
          url: "/parent/activity",
          backgroundColor: "child01",
          checkColor: "parent01",
        },
        {
          done: true,
          task: "투자 가격 업데이트 2",
          url: "/parent/userinfo",
        },
        {
          done: true,
          task: "투자 가격 업데이트 3",
          url: "/parent/userinfo",
        },
      ],
    };
  },
  computed: {
    ...mapGetters("accountStore", ["getDefaultBalance"]),
    ...mapGetters("userStore", ["checkUserInfo"]),
  },
  mounted() {
    // this.$nextTick(function () {
    this.$store.dispatch("accountStore/vuexUpdateDefaultBalance", this.checkUserInfo.seq);
    // this.items[0].balance = this.getDefaultBalance;
    // });
  },
  methods: {
    ...mapActions({ accountStore: ["updateDefaultBalance", "vuexFetchAccountHistory"] }),
  },
};
</script>

<style></style>
