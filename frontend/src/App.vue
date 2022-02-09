<template>
  <v-app>
    <router-view name="header"></router-view>
    <v-main>
      <router-view></router-view><!-- name을 지정하지 않으면, default -->
    </v-main>
    <router-view name="footer"></router-view>
  </v-app>
</template>

<script>
export default {
  name: "App",
  data: () => ({
    //
  }),
};
</script>

<style lang="scss">
  @import "@/assets/scss/style";
</style>