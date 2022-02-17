<!--
  author : 우정연
-->
<template>
  <div class="mx-6">
    <p class="my-4 mb-2">오늘자 뉴스</p>
    <NewsList :newsList="todayNews"></NewsList>
    <p class="mt-10 mb-2">지난 뉴스</p>
    <NewsList :newsList="lastNewsList"></NewsList>
  </div>
</template>

<script>
import NewsList from "@/components/child/activity/NewsList";
import { mapActions, mapGetters } from "vuex";
export default {
  components: {
    NewsList,
  },
  computed: {
    ...mapGetters("investStore", ["getNewsList"]),
    ...mapGetters("userStore", ["checkUserInfo"]),
  },
  watch: {
    getNewsList: {
      handler: function () {
        this.setNewsList();
      },
      deep: true,
    },
  },
  methods: {
    ...mapActions("investStore", ["vuexUpdateNewsList"]),
    setNewsList() {
      let today = new Date();
      let year = String(today.getFullYear()).substring(2, 4);
      let month = String("0" + (today.getMonth() + 1)).slice(-2);
      let date = String("0" + today.getDate()).slice(-2);
      let dateString = year + "-" + month + "-" + date;
      this.getNewsList.forEach((news) => {
        let newsRow = {
          date: news.date.substring(2, 10).replaceAll("-", "."),
          content: news.content,
        };
        if (news.date.substring(2, 10) != dateString) this.lastNewsList.push(newsRow);
        else this.todayNews.push(newsRow);
      });
    },
  },
  data() {
    return {
      lastNewsList: [],
      todayNews: [],
      // todayNews: [{ date: "2022-02-14", content: "오늘의 뉴스~~" }],
      // lastNewsList: [
      //   { date: "2022-02-13", content: "야근이 밀려있어ㅠㅠㅠ 오늘은 좀 늦지 않을까?" },
      //   { date: "2022-02-12", content: "치킨이 맛있네?" },
      //   { date: "2022-02-11", content: "오늘은 구매하자~~" },
      // ],
    };
  },
  created() {
    this.vuexUpdateNewsList(this.checkUserInfo.parentSeq);
  },
};
</script>

<style scoped>
p {
  font-size: 20px;
  font-weight: bold;
}
</style>
