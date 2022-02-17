<template>
  <div>
    <v-divider></v-divider>
    <div v-for="(news, idx) in newsList" :key="idx">
      <v-row class="pa-0 my-1">
        <v-col cols="3" align="end">
          <small style="color: #444444">{{ news.date }}</small>
        </v-col>
        <v-col cols="9">{{ news.content }}</v-col>
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

<style></style>
