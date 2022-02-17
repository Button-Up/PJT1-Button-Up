<template>
  <v-container class="fill-height">
    <v-row class="fill-height flex-column">
      <!-- 프로그레스 바 -->
      <v-col class="flex-grow-0">
        <v-progress-linear
          :value="($route.params.postSeq / postList.length) * 100"
          color="child01"
          height="6"
          rounded
        ></v-progress-linear>
      </v-col>

      <!-- 본문 -->
      <v-col class="flex-shrink-1">
        <v-card class="fill-height text-center d-flex justify-center align-center">
          <div class="d-flex flex-column align-center">
            <h2 class="mb-6">{{ postList[$route.params.postSeq - 1].title }}</h2>
            <div v-html="postList[$route.params.postSeq - 1].content">
              {{ postList[$route.params.postSeq - 1].content }}
            </div>
            <v-img
              v-if="postList[$route.params.postSeq - 1].image != null"
              :src="postList[$route.params.postSeq - 1].image"
              width="150px"
            >
            </v-img>
          </div>
        </v-card>
      </v-col>

      <!-- 하단 버튼 -->
      <v-col class="flex-grow-0">
        <v-row dense>
          <v-col v-if="$route.params.postSeq != 1">
            <v-btn
              block
              color="child01"
              class="font-weight-bold"
              :to="`/child/activity/study/${$route.params.topicSeq}/${-1 + +$route.params.postSeq}`"
              >이전</v-btn
            >
          </v-col>
          <v-col v-if="$route.params.postSeq != postList.length">
            <v-btn
              block
              color="child01"
              class="font-weight-bold"
              :to="`/child/activity/study/${$route.params.topicSeq}/${1 + +$route.params.postSeq}`"
              >다음</v-btn
            >
          </v-col>
          <v-col v-else>
            <v-btn
              block
              color="child04"
              class="font-weight-bold"
              @click.native="$router.push(`/child/activity/study`)"
              >학습 완료</v-btn
            >
            <!-- 퀴즈 추가되면 '복습 퀴즈 풀기'로 변경-->
          </v-col>
        </v-row>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import { apiGetContentOfTopic } from "@/api/economyAPI.js";

export default {
  name: "StudyDetail",
  data() {
    return {
      postList: [],
      // postList: [
      //   {
      //     // 포스트 순서
      //     postSeq: 1,
      //     title: "title1",
      //     content: "content",
      //     imageURL: "",
      //   },
      //   {
      //     postSeq: 2,
      //     title: "title2",
      //     content: "content",
      //     imageURL: "",
      //   },
      //   {
      //     postSeq: 3,
      //     title: "title3",
      //     content: "content",
      //     imageURL: "",
      //   },
      //   {
      //     postSeq: 4,
      //     title: "title4",
      //     content: "content",
      //     imageURL: "",
      //   },
      // ],
    };
  },
  created() {
    apiGetContentOfTopic(
      this.$route.params.topicSeq,
      (response) => {
        console.log(response.data);
        this.postList = response.data;
      },
      (error) => {
        console.log(error);
      }
    );
  },
};
</script>

<style></style>
