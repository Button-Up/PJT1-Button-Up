<!--
  author : 정은이

  modified: 김지언 - 자녀 리스트 불러오는 api 연결
-->
<template>
  <div class="mx-9 mt-6 mb-6">
    <!-- <div v-if="inTutorial">
      <ParentOnboard :tutorialStep="tutorialStep"></ParentOnboard>
    </div> -->
    <!-- <div v-else> -->
    <div>
      <h2>아이 정보</h2>
      <v-sheet class="mx-auto" elevation="" max-width="1200">
        <v-slide-group class="pa-1" center-active>
          <!--
            아이 정보 카드 클릭 시 activity로 이동하게 되어있습니다.
            추후 아이 상세 페이지로 이동하도록 수정 필요
          -->
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
//import ParentOnboard from "@/components/parent/home/Onboard";
import TodoList from "../../../components/common/TodoList.vue";
import { mapGetters } from "vuex";

//import { mapState } from "vuex";

export default {
  name: "Home",
  components: {
    ChildCardInfo,
    //ParentOnboard,
    TodoList,
  },
  data() {
    return {
      TodoList: [
        {
          done: false,
          task: "환전/결제 요청 확인",
          url: "/parent/request-list",
        },
        {
          done: true,
          task: "투자 가격 업데이트",
          url: "/parent/userinfo",
        },
        {
          done: true,
          task: "투자 가격 업데이트",
          url: "/parent/userinfo",
        },
      ],
    };
  },
  computed: {
    //...mapState('tempAccountStore', ['inTutorial', 'tutorialStep']),
    ...mapGetters("parentStore", ["childrenInfo"]),
  },
};
</script>
