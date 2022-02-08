<template>
  <v-card
    :color="customColor"
    :height="customHeight"
  >
  </v-card>
</template>

<script>
export default {
  name: 'CardMenu',
  props: {
    customColor: {
      type: String,
      default: 'child01'
    },
    customHeight: {
      type: String,
      default: '160px'
    }
  }
}
</script>

<style>

</style>