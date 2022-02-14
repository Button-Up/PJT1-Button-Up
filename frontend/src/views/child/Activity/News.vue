<!--
  author : 우정연
-->
<template>
  <div>
    <h2 class="mx-6 mt-4">오늘자 뉴스</h2>
    <news-list :newsList="todayNews"></news-list>
    <h2 class="mx-6 mt-4">지난 뉴스</h2>
    <news-list :newsList="lastNewsList"></news-list>
  </div>
</template>

<script>
import NewsList from "@/components/child/activity/NewsList";
export default {
  components: {
    NewsList,
  },
  data() {
    return {
      todayNews: [{ date: "2022-02-14", content: "오늘의 뉴스~~" }],
      lastNewsList: [
        { date: "2022-02-13", content: "야근이 밀려있어ㅠㅠㅠ 오늘은 좀 늦지 않을까?" },
        { date: "2022-02-12", content: "치킨이 맛있네?" },
        { date: "2022-02-11", content: "오늘은 구매하자~~" },
      ],
    };
  },
};
</script>

<style></style>
