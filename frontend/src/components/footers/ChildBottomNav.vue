<template>
  <v-bottom-navigation
    :value="value"
    color="child01"
    grow
    fixed
  >
    <v-btn :exact="true" to="/child/home" height="100%" style="background-color: #ffffff">
      <v-icon>mdi-home</v-icon>
    </v-btn>

    <v-btn :exact="true" to="/child/activity" height="100%" style="background-color: #ffffff">
      <v-icon>mdi-view-grid</v-icon>
    </v-btn>

    <v-btn :exact="true" to="/child/shop" height="100%" style="background-color: #ffffff">
      <v-icon>mdi-basket</v-icon>
    </v-btn>
  </v-bottom-navigation>
</template>

<script>
export default {
  name: 'ChildBottomNav',
}
</script>

<style>

</style>