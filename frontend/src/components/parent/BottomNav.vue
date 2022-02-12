<template>
  <v-bottom-navigation app color="parent01" grow>
    <v-btn :exact="true" to="/parent/home" height="100%" style="background-color: #ffffff">
      <v-icon>mdi-home</v-icon>
    </v-btn>

    <v-btn :exact="true" to="/parent/activity" height="100%" style="background-color: #ffffff">
      <v-icon>mdi-view-grid</v-icon>
    </v-btn>

    <v-btn :exact="true" to="/parent/userinfo" height="100%" style="background-color: #ffffff">
      <v-icon>mdi-account</v-icon>
    </v-btn>
  </v-bottom-navigation>
</template>

<script>
export default {
  name: "ParentBottomNav",
};
</script>

<style></style>
