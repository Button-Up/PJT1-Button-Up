<template>
  <v-bottom-navigation app :color="isParent ? 'parent01' : 'child01'" grow>
    <v-btn :exact="true" :to="`/${path}/home`" height="100%" style="background-color: #ffffff">
      <v-icon>mdi-home</v-icon>
    </v-btn>

    <v-btn :exact="true" :to="`/${path}/activity`" height="100%" style="background-color: #ffffff">
      <v-icon>mdi-view-grid</v-icon>
    </v-btn>

    <v-btn :exact="true" :to="`/${path}/userinfo`" height="100%" style="background-color: #ffffff">
      <v-icon>mdi-account</v-icon>
    </v-btn>
  </v-bottom-navigation>
</template>

<script>
export default {
  name: "ChildBottomNav",
  props: {
    isParent: {
      type: Boolean,
      default: true,
    },
  },
  computed: {
    path() {
      return this.isParent ? "parent" : "child";
    },
  },
};
</script>

<style></style>
