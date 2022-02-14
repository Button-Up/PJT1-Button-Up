<!--
  author : 우정연
-->
<template>
  <v-sheet class="v-sheet--offset mx-auto" color="white" elevation="2" max-width="calc(100% - 8px)">
    <v-sparkline
      :labels="labels"
      :value="value"
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
      value: [],
    };
  },
  props: {
    prices: {
      type: Array,
      default: null,
    },
  },
  created() {
    this.prices.forEach((e) => {
      this.labels.push(e.date);
      this.value.push(e.price);
    });
  },
};
</script>

<style></style>
