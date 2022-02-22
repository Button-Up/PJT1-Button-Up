<!--
  author : 우정연
-->
<template>
  <v-container>
    <v-row>
      <v-col class="d-flex flex-grow-0 flex-shrink-1" cols="9">
        <v-select
          v-model="nowInvestment"
          :items="investmentList"
          item-value="seq"
          item-text="name"
          class="font-weight-bold text-h5"
          align="center"
          flat
          solo
          return-object
          @change="setAllNowInvestInfo()"
        ></v-select>
      </v-col>
      <v-col text-right class="flex-grow-1 flex-shrink-0 mt-1 mr-3" align="center" justify="end"
        ><div style="font-size: 13px" class="d-flex flex-column">
          <div class="align-end" align="end" :class="nowInvestment.color">
            {{ nowInvestment.todayPrice }} 단추
          </div>
          <div class="align-start" align="end" :class="nowInvestment.color">
            <span v-if="nowInvestment.changePrice > 0"
              >▲{{ nowInvestment.changePrice.toLocaleString(locales, options) }}</span
            ><span v-else-if="nowInvestment.changePrice < 0"
              >▼{{ (-nowInvestment.changePrice).toLocaleString(locales, options) }}</span
            ><span v-else>-</span>
          </div>
        </div></v-col
      >
    </v-row>

    <!-- 그래프 -->
    <invest-graph :prices="nowInvestment.prices"></invest-graph>
    <br />

    <!-- 테이블 -->
    <invest-table
      :nowPrice="nowInvestment.todayPrice"
      :count="nowCount"
      :averagePrice="nowAveragePrice"
    ></invest-table>

    <v-row dense justify="center">
      <v-col
        ><InvestBtmSheet
          :isBuy="true"
          :price="nowInvestment.todayPrice"
          :name="nowInvestment.name"
          :investStatusSeq="nowInvestment.statusSeq"
          @getData="getTableInfo"
        >
          <!--주식현황키-->
          ></InvestBtmSheet
        ></v-col
      >
      <v-col
        ><InvestBtmSheet
          :isBuy="false"
          :price="nowInvestment.todayPrice"
          :name="nowInvestment.name"
          :investStatusSeq="nowInvestment.statusSeq"
          @getData="getTableInfo"
        ></InvestBtmSheet
      ></v-col>
    </v-row>

    <!-- 오늘의 뉴스 버튼 카드 -->
    <CardMenu
      class="mt-4"
      iconName="mdi-information"
      customColor="child02"
      iconSize="36px"
      title="오늘의 뉴스 보기"
      subtitle="부모님이 보낸 투자 정보, 확인해볼까요?"
      customHeight="120px"
      @click.native="$router.push('/child/activity/invest/news')"
    ></CardMenu>
  </v-container>
</template>

<script>
import InvestGraph from "@/components/child/activity/InvestGraph";
import InvestTable from "@/components/child/activity/InvestTable";
import InvestBtmSheet from "@/components/child/activity/InvestBtmSheet";
import CardMenu from "@/components/common/CardMenu";
import { apiGetInvestStatusByChild, apiGetAllInvest } from "@/api/investAPI";
import { mapGetters } from "vuex";
export default {
  components: { InvestGraph, InvestTable, InvestBtmSheet, CardMenu },
  data() {
    return {
      colorList: ["blue--text", "grey--text", "red--text"],
      averagePrice: 0,
      count: 0,
      nowInvestment: null,
      investmentList: [],
    };
  },
  computed: {
    ...mapGetters("userStore", ["checkUserInfo"]),
    nowAveragePrice() {
      if (this.nowInvestment == null) return 0;
      return this.nowInvestment.averagePrice;
    },
    nowCount() {
      if (this.count == null) return 0;
      return this.nowInvestment.count;
    },
  },
  methods: {
    resetAllData() {
      this.investmentList = [];
      this.nowInvestment = {};
    },
    async setData() {
      this.resetAllData();
      await apiGetAllInvest(this.checkUserInfo.parentSeq, ({ data }) => {
        this.investmentList = data;
        // console.log(this.investmentList);
        this.getInvestStatusByChild();
      });
    },
    // 아이의 주식별 현황 가져옴
    async getInvestStatusByChild() {
      for (let i = 0; i < this.investmentList.length; i++) {
        await apiGetInvestStatusByChild(
          this.investmentList[i].seq,
          this.checkUserInfo.seq,
          ({ data }) => {
            // console.log(data);
            this.investmentList[i].averagePrice = data.averagePrice;
            this.investmentList[i].count = data.count;
            this.investmentList[i].statusSeq = data.seq;
            this.investmentList[i].unit = data.unit;

            this.investmentList[i].prices = data.prices;
            if (i == 0) {
              this.nowInvestment = this.investmentList[0];
              this.setAllNowInvestInfo(); // 모든 주식 관련 정보 셋팅
            }
          }
        );
      }
    },
    // 구매/판매한 경우, 보유주식 수와 평균가격 변경
    async getTableInfo(investStatusSeq) {
      const newInvestment = { ...this.nowInvestment };
      // console.log(newInvestment);
      for (let i = 0; i < this.investmentList.length; i++) {
        if (investStatusSeq == this.investmentList[i].statusSeq) {
          await apiGetInvestStatusByChild(
            this.investmentList[i].seq,
            this.checkUserInfo.seq,
            ({ data }) => {
              this.investmentList[i].averagePrice = data.averagePrice;
              this.investmentList[i].count = data.count;
              newInvestment.averagePrice = data.averagePrice;
              newInvestment.count = data.count;
              this.nowInvestment = newInvestment;
              // console.log("----");
              // console.log(this.nowInvestment);
            }
          );
        }
      }
    },
    // 오늘의 가격, 변동금액, 색깔 셋팅
    setAllNowInvestInfo() {
      this.nowInvestment.todayPrice = this.getTodayPrice();
      this.nowInvestment.changePrice = this.getChangePrice();
      if (this.nowInvestment.changePrice < 0) {
        this.nowInvestment.color = this.colorList[0];
      } else if (this.nowInvestment.changePrice == 0) {
        this.nowInvestment.color = this.colorList[1];
      } else {
        this.nowInvestment.color = this.colorList[2];
      }
    },
    getTodayPrice() {
      return this.nowInvestment.prices[0].price; // 가격 - 최신게 위로 옴!(내림차순)
    },
    getChangePrice() {
      let changePrice = 0;
      let today = new Date();
      let year = String(today.getFullYear());
      let month = String("0" + (today.getMonth() + 1));
      let date = String("0" + today.getDate()).slice(-2);
      let dateString = year + "-" + month + "-" + date;
      if (this.nowInvestment.prices[0].date == dateString) {
        // 오늘 날짜이면 -> 이전값과 비교해 변동 가격 보여줌
        if (this.nowInvestment.prices.length > 1)
          // 이전 값이 있으면 변동사항 표시!
          changePrice = this.nowInvestment.prices[0].price - this.nowInvestment.prices[1].price;
      }
      return changePrice;
    },
  },
  created() {
    this.setData();
  },
};
</script>

<style scoped>
::v-deep .v-select__selections {
  line-height: 30px;
}
</style>
