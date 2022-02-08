<template>
  <v-app>
    <router-view name="header"></router-view>
    <router-view></router-view><!-- name을 지정하지 않으면, default -->
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
