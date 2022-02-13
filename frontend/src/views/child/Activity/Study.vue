<!--
  author: 유현수
-->

<template>
  <div class="ma-4">
    <v-row dense>
      <v-col :key="`topic-${idx}`" cols="6" v-for="(topic, idx) in topicList">
        <StudyCard
          :topic="topic"
          @click.native="$router.push(`/child/activity/study/${topic.topicSeq}/1`)"
        ></StudyCard>
      </v-col>
    </v-row>
  </div>
</template>

<script>
import StudyCard from "@/components/child/activity/StudyCard";

export default {
  name: "ChildStudy",
  components: {
    StudyCard,
  },
  data() {
    return {
      topicList: [
        {
          topicSeq: 0,
          imagePath: "/512/686/686379",
          title: "재화와 서비스",
          isFirstTry: true,
          quizTotal: 0,
          quizScore: 0,
        },
        {
          topicSeq: 1,
          imagePath: "/512/1138/1138038",
          title: "은행이란?",
          isFirstTry: false,
          quizTotal: 4,
          quizScore: 3,
        },
        {
          topicSeq: 2,
          imagePath: "/512/2474/2474455",
          title: "적금이란?",
          isFirstTry: false,
          quizTotal: 4,
          quizScore: 2,
        },
        {
          topicSeq: 3,
          imagePath: "/512/5501/5501360",
          title: "주식과 투자",
          isFirstTry: true,
          quizTotal: 0,
          quizScore: 0,
        },
      ],
    };
  },
};
</script>

<style></style>
