<!--
  author : 우정연
-->
<template>
  <v-sheet class="v-sheet--offset mx-auto" color="white" elevation="2" max-width="calc(100% - 8px)">
    <v-sparkline
      :labels="labels"
      :value="values"
      :smooth="radius || false"
      :gradient="gradients"
      line-width="2"
      padding="16"
      height="120px"
      auto-draw
    ></v-sparkline>
  </v-sheet>
</template>

<script>
export default {
  data() {
    return {
      radius: 10,
      labels: [],
      values: [],
      // values: [0, 2, 5, 9, 5, 10, 3, 5, 0, 0, 1, 8, 2, 9, 0],
      gradients: ["#00c6ff", "#F0F", "#FF0"],
    };
  },
  props: {
    prices: {
      type: Array,
      default: null,
    },
  },
  watch: {
    prices() {
      this.setGraphData();
    },
  },

  created() {
    this.setGraphData();
  },
  methods: {
    setGraphData() {
      this.labels = [];
      this.values = [];
      if (this.prices != null && this.prices.length == 1) {
        // 값 1개일때도 그래프 뜨도록
        this.labels.unshift(this.prices[0].date.substr(5, 5));
        this.values.unshift(this.prices[0].price);
        this.labels.unshift(this.prices[0].date.substr(5, 5));
        this.values.unshift(this.prices[this.prices.length - 1].price);
      } else if (this.prices != null) {
        this.prices.forEach((e) => {
          this.labels.unshift(e.date.substr(5, 5));
          this.values.unshift(e.price);
        });
        // this.labels.unshift(this.prices[this.prices.length - 1].date.substr(5, 5));
        // this.values.unshift(this.prices[this.prices.length - 1].price);
      }
    },
  },
};
</script>

<style></style>
