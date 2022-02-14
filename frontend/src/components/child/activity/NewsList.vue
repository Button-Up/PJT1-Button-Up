<template>
  <div>
    <v-divider></v-divider>
    <v-row v-for="(news, idx) in newsList" :key="idx" class="mt-0">
      <v-col cols="3">
        <small>{{ news.date }}</small>
      </v-col>
      <v-col cols="9">{{ news.content }}</v-col>
      <v-divider :key="`divider-${idx}`"></v-divider>
    </v-row>
  </div>
</template>

<script>
export default {
  props: {
    newsList: {
      type: Array,
    },
  },
};
</script>

<style scope>
.v-list-item .v-list-item-content .v-list-subtitle .v-list__tile {
  height: auto;
}
</style>
