<template>
  <div>
    <v-card
      class="ma-4"
      height="200"
      width="350"
      :color="item.color"
      @click="goToPage"
    >
      <v-row class="fill-height" align="center" justify="center">
        <v-scale-transition> </v-scale-transition>
      </v-row>
    </v-card>
  </div>
</template>

<script>
export default {
  name: "SavingCardInfo",
  props: {
    item: Object,
  },
  methods: {
    goToPage() {
      this.$router.push(this.item.url);
    },
  },
};
</script>

<style></style>
