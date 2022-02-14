<template>
  <div>
    <!-- 투자 종목 리스트 -->
    <div class="mt-2">
      <h2>투자 종목</h2>
    </div>
    <v-list-item v-for="(todo, t) in TodoList" :key="t" class="mb-2 pa-2" style="display: contents">
      <TodoList :todo="todo" :isParent="true"></TodoList>
    </v-list-item>
  </div>
</template>

<script>
import TodoList from "../../../components/common/TodoList.vue";

export default {
  name: "InvestManage",
  components: {
    TodoList,
  },
  data() {
    return {
      TodoList: [
        {
          done: false,
          content: "환전/결제 요청 확인",
          url: "/parent/request-list",
        },
        {
          done: true,
          content: "투자 가격 업데이트",
          url: "/parent/userinfo",
        },
        {
          done: true,
          content: "투자 가격 업데이트",
          url: "/parent/userinfo",
        },
      ],
    };
  },
};
</script>

<style></style>
