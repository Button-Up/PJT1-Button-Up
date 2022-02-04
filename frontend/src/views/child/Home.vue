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
    <div></div>
  </div>
</template>

<script>
import SavingCardInfo from "@/components/child/home/SavingCardInfo";

export default {
  name: "Home",
  components: { SavingCardInfo },
  data() {
    return {
      items: [{ color: "child01" }, { color: "child04" }],
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
