<!--
    author: 김지언
-->
<template>
  <v-list-item class="justify-space-between align-baseline" cols="12">
    <v-text-field
      cols="5"
      v-model="target"
      hide-details
      required
      color="parent01"
      placeholder="ex) 어머니"
      outlined
      dense
    ></v-text-field>

    <v-col cols="2" class="pl-2">의 </v-col>
    <v-select
      cols="5"
      class="ma-0 pa-0"
      v-model="selectPreset"
      :items="getPresetList"
      item-value="seq"
      item-text="name"
      :color="`parent01`"
      :item-color="`parent01`"
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
  data() {
    return {
      selectPreset: null,
      target: null,
    };
  },
  computed: {
    ...mapGetters("investStore", ["getPresetList"]),
  },
  mounted() {
    this.updatePreset();
  },
  updated() {
    this.$emit("setPreset", this.selectPreset, this.target);
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
