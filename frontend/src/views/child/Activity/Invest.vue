<!--
  author : 우정연
-->
<template>
  <v-container>
    <v-row>
      <v-col class="d-flex flex-grow-0 flex-shrink-1" cols="8">
        <v-select
          v-model="selected"
          :items="investNameList"
          class="font-weight-bold text-h5"
          align="center"
          flat
          solo
        ></v-select>
      </v-col>
      <v-col text-right class="flex-grow-1 flex-shrink-0 mt-1 mr-3" align="center" justify="end"
        ><div style="font-size: 13px" class="d-flex flex-column">
          <div class="align-end" align="end" :class="color">
            {{ investInfo[selectedIdx].today_price }} 단추
          </div>
          <div class="align-start" align="end" :class="color">
            <span v-if="investInfo[selectedIdx].change_price > 0">▲</span
            ><span v-else-if="investInfo[selectedIdx].change_price < 0">▼</span
            ><span v-else>-</span>
            <span
              v-text="
                investInfo[selectedIdx].change_price >= 0
                  ? investInfo[selectedIdx].change_price
                  : -investInfo[selectedIdx].change_price
              "
            ></span>
          </div></div
      ></v-col>
    </v-row>

    <!-- 그래프 -->
    <invest-graph :prices="investInfo[selectedIdx].prices"></invest-graph>
    <br />

    <!-- 테이블 -->
    <invest-table></invest-table>

    <v-row dense justify="center">
      <v-col
        ><InvestBtmSheet
          :isBuy="true"
          :price="investInfo[selectedIdx].today_price"
          :name="investInfo[selectedIdx].target + ` ` + investInfo[selectedIdx].name"
        ></InvestBtmSheet
      ></v-col>
      <v-col
        ><InvestBtmSheet
          :isBuy="false"
          :price="investInfo[selectedIdx].today_price"
          :name="investInfo[selectedIdx].target + ` ` + investInfo[selectedIdx].name"
        ></InvestBtmSheet
      ></v-col>
    </v-row>

    <!-- 오늘의 뉴스 버튼 카드 -->
    <card-menu
      class="mt-4"
      iconName="mdi-information"
      customColor="child02"
      iconSize="40px"
      title="오늘의 뉴스 보기"
      subtitle="뉴스를 통해 투자하기 위한 정보를 얻어보자!"
      customHeight="120px"
    ></card-menu>
  </v-container>
</template>

<script>
import InvestGraph from "@/components/child/activity/InvestGraph";
import InvestTable from "@/components/child/activity/InvestTable";
import InvestBtmSheet from "@/components/child/activity/InvestBtmSheet";
import CardMenu from "@/components/common/CardMenu";
export default {
  components: { InvestGraph, InvestTable, InvestBtmSheet, CardMenu },
  created() {
    this.selected = this.investNameList[0];
    this.selectedIdx = 0;
  },
  data() {
    return {
      colorList: ["red--text", "grey--text", "blue--text"],
      selected: "",
      selectedIdx: 0,
      color: "red--text",
      investNameList: ["아빠 몸무게", "엄마 퇴근시간"],
      investInfo: [
        {
          target: "아빠",
          name: "몸무게",
          unit: "kg",
          prices: [
            { date: "2022-02-10", price: 500 },
            { date: "2022-02-11", price: 600 },
            { date: "2022-02-12", price: 550 },
            { date: "2022-02-13", price: 500 },
          ],
          today_price: 500,
          change_price: -50,
          count: 5,
          average_price: 500,
        },
        {
          target: "엄마",
          name: "퇴근시간",
          unit: "시",
          prices: [
            { date: "2022-02-10", price: 5 },
            { date: "2022-02-11", price: 6 },
            { date: "2022-02-12", price: 8 },
            { date: "2022-02-13", price: 9 },
          ],
          today_price: 9,
          change_price: 1,
          count: 5,
          average_price: 6,
        },
      ],
    };
  },
  method: {},
};
</script>

<style scope>
.v-select__selections {
  line-height: 30px;
}
</style>
