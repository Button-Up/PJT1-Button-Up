<!--
    author: 김지언
-->
<template>
  <v-list-item class="justify-space-between align-baseline" cols="12">
    <v-text-field
      cols="10"
      hide-details
      required
      v-model="price"
      color="parent01"
      :placeholder="placeholder"
      outlined
      dense
    ></v-text-field>
    <v-col cols="2" class="pl-2">{{ preset.unit }} </v-col>
  </v-list-item>
</template>

<script>
export default {
  name: "priceInput",
  props: {
    preset: {
      type: Object,
    },
  },
  data() {
    return {
      price: null,
      placeholder: this.preset.name + " 입력해주세요",
    };
  },
  updated() {
    this.$emit("setPrice", this.price);
  },
};
</script>

<style></style>
