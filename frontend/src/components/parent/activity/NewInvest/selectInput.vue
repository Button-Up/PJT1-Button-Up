<!--
    author: 김지언
-->
<template>
  <v-list-item class="justify-space-between align-baseline px-0" cols="12">
    <v-text-field
      cols="5"
      v-model="targetData"
      :rules="targetRules"
      hide-details="auto"
      color="parent01"
      placeholder="ex) 어머니"
      outlined
      dense
    ></v-text-field>

    <v-col cols="2" class="pl-2">의 </v-col>
    <v-select
      cols="5"
      class="ma-0 pa-0"
      v-model="preset"
      :items="getPresetList"
      item-value="seq"
      item-text="name"
      :color="`parent01`"
      :item-color="`parent01`"
      :rules="selectRules"
      hide-details="auto"
      outlined
      solo
      align-center
      single-line
      disable-lookup
      dense
      return-object
      width="500px"
      ref="select"
    ></v-select>
  </v-list-item>
</template>

<script>
import { mapActions, mapGetters } from "vuex";

export default {
  name: "selectInput",
  props: {
    selectPreset: {},
    target: {},
  },
  data() {
    return {
      preset: null,
      targetData: null,
      targetRules: [(value) => !!value || "입력해주세요."],
      selectRules: [(value) => !!value || "선택해주세요."],
    };
  },
  computed: {
    ...mapGetters("investStore", ["getPresetList"]),
  },
  mounted() {
    this.updatePreset();
    this.preset = this.selectPreset;
    this.targetData = this.target;
    // console.log(this.preset, this.targetData);
  },
  updated() {
    this.$emit("setPreset", this.preset, this.targetData);
    // console.log(this.preset, this.targetData);
  },
  methods: {
    ...mapActions("investStore", ["vuexUpdatePreset"]),

    updatePreset() {
      this.vuexUpdatePreset();
    },
  },
};
</script>

<style></style>
