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
import { apiGetEconomyTopic } from "@/api/economyAPI.js";

export default {
  name: "ChildStudy",
  components: {
    StudyCard,
  },
  data() {
    return {
      topicList: [
        {
          topicSeq: 1,
          imagePath: "/512/2474/2474455",
          title: "",
          isFirstTry: true,
          quizTotal: 0,
          quizScore: 0,
        },
        {
          topicSeq: 2,
          imagePath: "/512/1138/1138038",
          title: "",
          // 퀴즈 처음 푸는지
          isFirstTry: true,
          // 퀴즈 총 문항 수
          quizTotal: 0,
          // 퀴즈 맞은 개수
          quizScore: 0,
        },
        {
          topicSeq: 3,
          imagePath: "/512/3061/3061341",
          title: "",
          isFirstTry: false,
          quizTotal: 4,
          quizScore: 3,
        },
        {
          topicSeq: 4,
          imagePath: "/512/686/686379",
          title: "",
          isFirstTry: false,
          quizTotal: 4,
          quizScore: 2,
        },
        {
          topicSeq: 5,
          imagePath: "/512/5501/5501360",
          title: "",
          isFirstTry: true,
          quizTotal: 0,
          quizScore: 0,
        },
        {
          topicSeq: 6,
          imagePath: "/512/639/639365",
          title: "",
          isFirstTry: true,
          quizTotal: 0,
          quizScore: 0,
        },
      ],
    };
  },
  created() {
    apiGetEconomyTopic(
      (response) => {
        const arr = response.data;
        for (let i = 0; i < arr.length; i++) {
          this.topicList[i].title = arr[i];
        }
      },
      // (err) => {
      //   console.log(err);
      // }
      () => {}
    );
  },
};
</script>

<style></style>
