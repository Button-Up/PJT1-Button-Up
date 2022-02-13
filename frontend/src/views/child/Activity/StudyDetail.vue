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
      <v-col>
        <v-card class="fill-height d-flex flex-column justify-center">
          <div class="px-6 pb-16">
            <h2>{{ postList[$route.params.postSeq - 1].title }}</h2>
            <p>{{ postList[$route.params.postSeq - 1].content }}</p>
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
            <v-btn block color="child04" class="font-weight-bold">복습 퀴즈 풀기</v-btn>
          </v-col>
        </v-row>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
export default {
  name: "StudyDetail",
  data() {
    return {
      postList: [
        {
          postSeq: 1,
          title: "title1",
          content: "content",
          imageURL: "",
        },
        {
          postSeq: 2,
          title: "title2",
          content: "content",
          imageURL: "",
        },
        {
          postSeq: 3,
          title: "title3",
          content: "content",
          imageURL: "",
        },
        {
          postSeq: 4,
          title: "title4",
          content: "content",
          imageURL: "",
        },
      ],
    };
  },
};
</script>

<style></style>
