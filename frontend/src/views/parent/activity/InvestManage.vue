<template>
  <div class="mx-6 mt-6">
    <!-- 투자 종목 리스트 -->
    <div class="mb-4">
      <v-row class="ma-0 align-center justify-space-between">
        <h2>투자 종목</h2>
        <v-btn
          small
          :color="`parent01`"
          class="white--text"
          @click="$router.push('/parent/activity/invest/new/1')"
          >추가하기</v-btn
        >
      </v-row>
    </div>

    <v-list-item
      v-for="(todo, t) in convertToFitToDoComponents"
      :key="t"
      class="mb-2 pa-2"
      style="display: contents"
    >
      <TodoList :todo="todo" :isParent="true"></TodoList>
    </v-list-item>

    <div class="mt-8">
      <h2>오늘의 투자 뉴스</h2>
      <p class="caption font-weight-normal">
        아이가 정보를 바탕으로 투자할 수 있도록 뉴스를 전달해보세요!
      </p>
    </div>

    <v-text-field
      cols="8"
      v-model="news"
      hide-details
      label="ex) 오늘 저녁에 치킨을 먹을거에요"
      required
      color="parent01"
    ></v-text-field>

    <v-btn block color="parent01" class="white--text mt-4" @click="submitNews">뉴스 보내기</v-btn>
  </div>
</template>

<script>
import TodoList from '../../../components/common/TodoList.vue';
import { mapGetters } from 'vuex';

export default {
  name: 'InvestManage',
  components: {
    TodoList,
  },
  data() {
    return {
      dialog: false,
      news: '',
      TodoList: [
        {
          done: true,
          content: '환전/결제 요청 확인',
          url: '/parent/request-list',
        },
        {
          done: true,
          content: '투자 가격 업데이트',
          url: '/parent/userinfo',
        },
        {
          done: true,
          content: '투자 가격 업데이트',
          url: '/parent/userinfo',
        },
      ],
    };
  },
  mounted() {
    this.getInvest();
  },
  computed: {
    ...mapGetters('investStore', ['getInvestList']),
    ...mapGetters('userStore', ['checkUserInfo']),
    convertToFitToDoComponents() {
      var convertInvest = [];
      for (const investItem of this.getInvestList) {
        convertInvest.push({
          done: true,
          content: investItem.name,
          url: '/parent/activity/job',
        });
      }
      return convertInvest;
    },
  },
  methods: {
    getInvest() {
      this.$store.dispatch('investStore/vuexUpdateInvestList', this.checkUserInfo.seq);
    },
    submitNews() {
      console.log(this.news);
      var param = {
        content: this.news,
        parentSeq: this.checkUserInfo.seq,
      };
      console.log(param);
      this.$store.dispatch('investStore/vuexAddNews', param);
      this.news = '';
    },
    clicYesBtn() {
      console.log('yes 버튼 눌렀다');
    },
    setDialog(value) {
      this.dialog = value;
    },
  },
};
</script>

<style></style>
