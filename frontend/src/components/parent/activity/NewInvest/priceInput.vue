<!--
    author: 김지언
-->
<template>
  <v-list-item class="justify-space-between align-baseline px-0" cols="12">
    <v-text-field
      cols="10"
      :rules="rules"
      hide-details="auto"
      v-model="price"
      color="parent01"
      :placeholder="placeholder"
      outlined
      dense
    ></v-text-field>
    <v-col cols="2" class="pl-2">{{ selectPreset.unit }} </v-col>
  </v-list-item>
</template>

<script>
export default {
  name: "priceInput",
  props: {
    selectPreset: {
      type: Object,
    },
    savedPrice: {},
  },
  data() {
    return {
      price: null,
      placeholder: this.selectPreset.name + " 입력해주세요",
      rules: [
        (value) => !!value || "입력해주세요.",
        (value) => /^[0-9]*$/.test(value) || "숫자를 입력해주세요",
      ],
    };
  },
  mounted() {
    this.price = this.savedPrice;
  },
  updated() {
    this.$emit("setPrice", this.price);
  },
};
</script>

<style></style>
