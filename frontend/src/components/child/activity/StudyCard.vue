<!--
  author: 유현수
-->

<template>
  <v-card height="12rem" class="rounded-lg">
    <v-sheet height="120px" class="d-flex justify-center align-center" color="#fffaf0">
      <v-img v-if="topic.imagePath" :src="imageURL" max-height="80px" max-width="80px"></v-img>
    </v-sheet>
    <v-divider></v-divider>
    <div class="px-3">
      <div class="d-flex justify-space-between align-center pt-3">
        <h1 class="text-body-2 font-weight-bold">{{ topic.title }}</h1>
        <v-chip v-if="!topic.isFirstTry" :color="isPass ? 'green' : 'red'" text-color="white" small>
          <v-icon v-if="isPass" size="16" left> mdi-check </v-icon>
          <v-icon v-else size="20" left> mdi-close </v-icon>
          {{ topic.quizScore }} / {{ topic.quizTotal }}
        </v-chip>
        <v-chip v-else color="gray" small> 학습 전 </v-chip>
      </div>
      <p v-if="!isPass" class="pt-1 text-caption">{{ "완료 시 500단추" }}</p>
    </div>
  </v-card>
</template>

<script>
export default {
  name: "StudyCard",
  props: {
    topic: Object,
    default: {
      imagePath: null,
      title: "title",
      isFirstTry: true,
      quizTotal: 0,
      quizScore: 0,
    },
  },
  computed: {
    isPass() {
      return this.topic.quizScore / this.topic.quizTotal > 0.7 ? true : false;
    },
    imageURL() {
      const baseURL = "https://cdn-icons-png.flaticon.com";
      return baseURL + this.topic.imagePath + ".png";
    },
  },
};
</script>

<style></style>
