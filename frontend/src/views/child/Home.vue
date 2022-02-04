<template>
  <div>
    <div id="wallet">
      <h1>내 지갑</h1>
    </div>
    <v-sheet class="mx-auto" elevation="" max-width="800">
      <v-slide-group class="pa-4" center-active>
        <v-slide-item v-for="(item, i) in items" :key="i">
          <saving-card-info :item="item"></saving-card-info>
        </v-slide-item>
      </v-slide-group>
    </v-sheet>
    <!--잔액 표시-->

    <h1>오늘 할 일</h1>
    <div>
      <v-list-item
        v-for="(todo, t) in TodoList"
        :key="t"
        class="mb-2 pa-2"
        style="display: contents"
      >
        <todo-list :todo="todo" :isParent="false"></todo-list>
      </v-list-item>
    </div>
  </div>
</template>

<script>
import SavingCardInfo from "@/components/child/home/SavingCardInfo";
import TodoList from "@/components/common/TodoList.vue";

export default {
  name: "Home",
  components: { SavingCardInfo, TodoList },
  data() {
    return {
      items: [
        { color: "child01", url: "saving" },
        { color: "child04", url: "installmentsaving" },
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

<style scoped>
h1 {
  margin-left: 33px;
  height: 28px;
  left: 24px;
  top: 72px;
  font-family: Roboto;
  font-style: normal;
  font-weight: bold;
  font-size: 24px;
}
#wallet {
  margin-top: 20px;
}
</style>
