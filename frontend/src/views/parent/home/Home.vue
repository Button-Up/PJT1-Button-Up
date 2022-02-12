<!--
  author : 정은이

  modified: 김지언 - 자녀 리스트 불러오는 api 연결
-->
<template>
  <div class="mx-9 mt-6 mb-6">
    <div v-if="checkInTutorial">
      <ParentTutorial :checkTutorialStage="checkTutorialStage"></ParentTutorial>
    </div>
    <div v-else>
      <h2>아이 정보</h2>
      <v-sheet class="mx-auto" elevation="" max-width="1200">
        <v-slide-group class="pa-1" center-active>
          <v-slide-item
            justify-center
            v-for="(item, idx) in childrenInfo"
            :key="idx"
            @click.native="$router.push({ name: 'ChlidInfo', params: { child: item } })"
          >
            <ChildCardInfo :item="item"></ChildCardInfo>
          </v-slide-item>
        </v-slide-group>
      </v-sheet>

      <!-- 부모의 할일 -->
      <div class="mt-2">
        <h2>확인해주세요!</h2>
        <p class="caption font-weight-normal">오늘 확인해야 할 항목들이에요</p>
      </div>
      <v-list-item
        v-for="(todo, t) in TodoList"
        :key="t"
        class="mb-2 pa-2"
        style="display: contents"
      >
        <TodoList :todo="todo" :isParent="true"></TodoList>
      </v-list-item>
    </div>
  </div>
</template>
<script>
import ChildCardInfo from "@/components/parent/home/ChildCardInfo";
import ParentTutorial from "@/components/parent/home/Tutorial";
import TodoList from "../../../components/common/TodoList.vue";
import { mapActions, mapGetters } from "vuex";

export default {
  name: "Home",
  components: {
    ChildCardInfo,
    ParentTutorial,
    TodoList,
  },
  data() {
    return {
      TodoList: [
        {
          done: false,
          content: "환전/결제 요청 확인",
          url: "/parent/request-list",
        },
        {
          done: true,
          content: "투자 가격 업데이트",
          url: "/parent/userinfo",
        },
        {
          done: true,
          content: "투자 가격 업데이트",
          url: "/parent/userinfo",
        },
      ],
    };
  },
  computed: {
    ...mapGetters("userStore", ["checkUserInfo"]),
    ...mapGetters("parentStore", ["childrenInfo", "checkInTutorial", "checkTutorialStage"]),
  },
  methods: {
    ...mapActions("parentStore", ["vuexGetTutorialStage"]),
  },
  async mounted() {
    await this.vuexGetTutorialStage(this.checkUserInfo.seq);
  },
};
</script>
