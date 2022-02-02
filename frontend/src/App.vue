<template>
  <v-app>
    <router-view name="header" />
    <v-main>
      <router-view/>
    </v-main>
    <router-view name="footer" />
  </v-app>
</template>

<script>

export default {
  name: 'App',

  data: () => ({
    //
  }),
};
</script>
