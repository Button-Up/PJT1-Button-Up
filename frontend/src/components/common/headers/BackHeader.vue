<template>
  <div>
    <v-app-bar
      dense
      color="white"
      elevation="0"
    >
      <v-icon
        @click="$router.go(-1)"
        class="mr-6 back-btn"
      >mdi-arrow-left</v-icon>
      <v-toolbar-title>
        {{ pageTitle }}
      </v-toolbar-title>
    </v-app-bar>
  </div>
</template>

<script>
export default {
  name: 'BackHeader',
  props: {
    pageTitle: {
      type: String,
      default: ''
    }
  }
}
</script>

<style lang="scss" scoped>
  .back-btn {
    cursor: pointer;
  }
</style>