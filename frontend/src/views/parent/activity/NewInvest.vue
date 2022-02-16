<!--
    author: 김지언
-->
<template>
  <v-container class="fill-height">
    <v-row class="fill-height flex-column">
      <!-- 프로그레스 바 -->
      <v-col class="flex-grow-0">
        <v-progress-linear
          :value="($route.params.seq / postList.length) * 100"
          color="parent01"
          height="6"
          rounded
        ></v-progress-linear>
      </v-col>

      <!-- 본문 -->
      <v-col>
        <v-card class="fill-height d-flex flex-column justify-center">
          <div class="px-6 pb-16">
            <h2>{{ postList[$route.params.seq - 1].title }}</h2>
            <v-subheader class="ps-0">{{ postList[$route.params.seq - 1].content }}</v-subheader>

            <SelectInput v-if="$route.params.seq == 1" @setPreset="setPreset"> </SelectInput>
            <PriceInput v-else :preset="selectPreset" @setPrice="setPrice"></PriceInput>
          </div>
        </v-card>
      </v-col>

      <!-- 하단 버튼 -->
      <v-col class="flex-grow-0">
        <v-row dense>
          <v-col v-if="$route.params.seq != 1">
            <v-btn
              block
              color="parent01"
              class="font-weight-bold white--text"
              :to="`/parent/activity/invest/new/${-1 + +$route.params.seq}`"
              >이전</v-btn
            >
          </v-col>
          <v-col v-if="$route.params.seq != postList.length">
            <v-btn
              block
              color="parent01"
              class="font-weight-bold white--text"
              :to="`/parent/activity/invest/new/${1 + +$route.params.seq}`"
              >다음</v-btn
            >
          </v-col>
          <v-col v-else>
            <v-btn
              block
              color="parent02"
              class="font-weight-bold"
              @click.native="setInvest"
              to="/parent/activity/invest"
              >완료</v-btn
            >
          </v-col>
        </v-row>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import SelectInput from "@/components/parent/activity/NewInvest/selectInput.vue";
import PriceInput from "@/components/parent/activity/NewInvest/priceInput.vue";
import { mapActions, mapGetters } from "vuex";

export default {
  name: "NewInvest",
  components: {
    SelectInput,
    PriceInput,
  },
  data() {
    return {
      selectPreset: null,
      target: null,
      price: null,
      postList: [
        {
          // 포스트 순서
          postSeq: 1,
          title: "투자 종목을 생성합니다.",
          content: "첫 종목은 보호자의 몸무게로 생성하는 것을 추천합니다.",
          imageURL: "",
        },
        {
          postSeq: 2,
          title: "시작 가격을 입력해주세요.",
          content: "현재 몸무게가 시작 가격이 됩니다.",
          imageURL: "",
        },
      ],
    };
  },
  computed: {
    ...mapGetters("userStore", ["checkUserInfo"]),
  },
  methods: {
    ...mapActions("investStore", ["vuexAddNewInvest", "vuexGetInvestList"]),
    setPreset(preset, target) {
      this.selectPreset = preset;
      this.target = target;
    },
    setPrice(value) {
      this.price = value;
      // console.log(this.price);
    },
    setInvest() {
      this.vuexAddNewInvest({
        investPresetSeq: this.selectPreset.seq,
        parentSeq: this.checkUserInfo.seq,
        price: this.price,
        target: this.target,
      });
      this.vuexGetInvestList(this.checkUserInfo.seq);
    },
  },
};
</script>

<style></style>
