<!--
  author : 김지언
-->
<template>
  <v-container>
    <v-row>
      <v-col class="d-flex flex-grow-0 flex-shrink-1" cols="8">
        <v-select
          v-model="selected"
          :items="getInvestList"
          item-value="seq"
          item-text="name"
          class="font-weight-bold text-h5"
          align="center"
          flat
          solo
        ></v-select>
      </v-col>
      <v-col text-right class="flex-grow-1 flex-shrink-0 mt-1 mr-3" align="center" justify="end"
        ><div style="font-size: 13px" class="d-flex flex-column">
          <div class="align-end" align="end" :class="color">{{ getPriceList[0].price }} 단추</div>
          <div class="align-start" align="end" :class="color">
            <span v-if="getPriceList.length > 1 && getPriceList[0].price > getPriceList[1].price"
              >▲</span
            ><span
              v-else-if="getPriceList.length > 1 && getPriceList[0].price < getPriceList[1].price"
              >▼</span
            ><span v-else>-</span>
            <span
              v-text="
                getPriceList.length > 1 ? getPriceList[0].price - getPriceList[1].price : null
              "
            ></span>
          </div></div
      ></v-col>
    </v-row>

    <!-- 그래프 -->
    <invest-graph :prices="getPriceList"></invest-graph>
    <br />

    <div class="px-0 pb-16">
      <h3>가격 업데이트</h3>
      <p>매일 가격을 업데이트 해주세요!</p>
      <v-card class="fill-height d-flex flex-column justify-center px-2">
        <v-list-item class="justify-space-between align-baseline px-0" cols="12">
          <v-text-field
            cols="5"
            v-model="price"
            hide-details
            required
            label="가격"
            color="parent01"
            outlined
            dense
          ></v-text-field>

          <v-col cols="2" class="pl-2">{{ unit }}</v-col>
          <v-col>
            <v-btn
              block
              color="parent01"
              class="font-weight-bold white--text"
              @click.native="updatePrice"
              >업데이트</v-btn
            >
          </v-col>
        </v-list-item>
      </v-card>
    </div>
  </v-container>
</template>

<script>
import { apiPostNewPrice } from "@/api/investAPI.js";
import InvestGraph from "@/components/child/activity/InvestGraph";
import { mapActions, mapGetters } from "vuex";

export default {
  components: { InvestGraph },
  mounted() {
    this.$store.dispatch("investStore/vuexGetPriceList", this.$route.params.seq);
    console.log(this.getPriceList);
    for (let i = 0; i < this.getInvestList.length; i++) {
      if (this.getInvestList[i].seq == this.$route.params.seq) {
        this.selected = this.getInvestList[i];
        for (let j = 0; j < this.getPresetList.length; j++) {
          if (this.selected.name.indexOf(this.getPresetList[j].name) != -1) {
            this.unit = this.getPresetList[j].unit;
            break;
          }
        }
        break;
        // console.log(this.selected);
      }
    }
    if (this.getPriceList.length > 1) {
      if (this.getPriceList[0].price > this.getPriceList[1].price) {
        this.color = "red--text";
      } else if (this.getPriceList[0].price === this.getPriceList[1].price) {
        this.color = "grey--text";
      } else {
        this.color = "blue--text";
      }
    } else {
      this.color = "grey--text";
    }
  },
  data() {
    return {
      price: null,
      unit: null,
      colorList: ["red--text", "grey--text", "blue--text"],
      selected: null,
      color: "red--text",
    };
  },
  computed: {
    ...mapGetters("investStore", ["getInvestList", "getPriceList", "getPresetList"]),
  },
  //   mounted() {
  //     this.$store.dispatch("investStore/vuexGetPriceList", this.$route.params.seq);
  //     console.log(this.getPriceList);
  //   },
  methods: {
    ...mapActions("investStore", ["vuexUpdateInvestStatus", "vuexGetPriceList"]),
    updatePrice() {
      console.log("lkdjladkjdskjlgsd");
      apiPostNewPrice(
        {
          investSeq: this.selected.seq,
          price: this.price,
        },
        (resp) => {
          this.$store.dispatch("investStore/vuexGetPriceList", this.$route.params.seq);
          console.log(resp, "새로운 가격 정보 추가 완료");
          // this.vuexGetPriceList(priceInfo.investSeq);
        }
      );
    },
  },
};
</script>

<style scope>
.v-select__selections {
  line-height: 30px;
}
</style>
