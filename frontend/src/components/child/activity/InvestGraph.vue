<!--
  author : 우정연
-->
<template>
  <v-sheet class="v-sheet--offset mx-auto" color="white" elevation="2" max-width="calc(100% - 8px)">
    <v-sparkline
      :labels="labels"
      :value="values"
      color="#55C1C4"
      line-width="2"
      padding="16"
      height="120px"
    ></v-sparkline>
  </v-sheet>
</template>

<script>
export default {
  data() {
    return {
      labels: [],
      values: [],
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
        this.values.unshift(0);
      } else if (this.prices != null) {
        this.prices.forEach((e) => {
          this.labels.unshift(e.date.substr(5, 5));
          this.values.unshift(e.price);
        });
        this.labels.unshift(this.prices[this.prices.length - 1].date.substr(5, 5));
        this.values.unshift(0);
      }
    },
  },
};
</script>

<style></style>
