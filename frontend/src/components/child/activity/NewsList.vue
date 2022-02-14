<template>
  <div>
    <v-divider></v-divider>
    <div v-for="(news, idx) in newsList" :key="idx">
      <v-row class="pa-0 my-1">
        <v-col cols="4">
          <small>{{ news.date }}</small>
        </v-col>
        <v-col cols="8">{{ news.content }}</v-col>
      </v-row>
      <v-divider></v-divider>
    </div>
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

<style scope></style>
