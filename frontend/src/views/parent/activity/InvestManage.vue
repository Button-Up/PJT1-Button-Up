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
          @click="$router.push('/parent/activity/job')"
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
  },
};
</script>

<style></style>
