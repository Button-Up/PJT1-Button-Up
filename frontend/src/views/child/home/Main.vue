<!--
author: 김응철

modified: 유현수 - 마크업 & 스타일링
-->

<template>
  <div>
    <!-- 단추 계좌 슬라이드 -->
    <h2 class="mx-6 mt-4">나의 단추 계좌</h2>
    <v-sheet elevation="">
      <v-slide-group class="mt-2 mx-4" center-active show-arrows="false">
        <v-slide-item v-for="(item, i) in items" :key="i">
          <MainAccountCard :item="item"></MainAccountCard>
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

export default {
  name: "Home",
  components: {
    MainAccountCard,
    TodoList
  },
  data() {
    return {
      items: [
        {
          isDeposit: true,
          balance: 40000
        },
        {
          isDeposit: false,
          balance: 6000
        },
      ],
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
};
</script>

<style>

</style>
