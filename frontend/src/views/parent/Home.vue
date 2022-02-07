<template>
  <div class="mx-9 mt-6">
    <div v-if="inTutorial">
      <parent-onboard :tutorialStep="tutorialStep"></parent-onboard>
    </div>
    <div v-else>
      <h2>아이 정보</h2>
      <v-sheet class="mx-auto" elevation="" max-width="1200">
        <v-slide-group class="pa-1" center-active>
          <!--
            아이 정보 카드 클릭 시 activity로 이동하게 되어있습니다.
            추후 아이 상세 페이지로 이동하도록 수정 필요
          -->
          <v-slide-item
            justify-center
            v-for="(item, i) in items"
            :key="i"
            @click.native="$router.push('/parent/home/child-info/'+item.name)"
          >
            <child-card-info
              :item="item"
            ></child-card-info>
          </v-slide-item>
        </v-slide-group>
      </v-sheet>

      <!-- 부모의 할일 -->
      <div class="mt-2">
          <h2>확인해주세요!</h2>
          <p class="caption font-weight-normal">오늘 확인해야 할 항목들이에요</p>
      </div>
      <v-list-item v-for="(todo, t) in TodoList"  :key="t" class="mb-2 pa-2" style="display:contents">
        <todo-list :todo="todo" :isParent="true"></todo-list>
      </v-list-item>
    </div>
  </div>
</template>
<script>
import ChildCardInfo from '@/components/parent/home/ChildCardInfo'
import ParentOnboard from '@/components/parent/home/Onboard'
import TodoList from '../../components/common/TodoList.vue'

import { mapState } from 'vuex'

export default {
  name: "Home",
  components: { 
    ChildCardInfo,
    ParentOnboard,
    TodoList
  },
  data() {
    return {
      items: [
        {
          name: "정은이",
          danchuAmount: "32,000 단추",
          progressValue: 30,
          progressAmount: "1/3",
        },
        {
          name: "김응철",
          danchuAmount: "14,000 단추",
          progressValue: 60,
          progressAmount: "2/3",
        },
        {
          name: "유현수",
          danchuAmount: "14,000 단추",
          progressValue: 60,
          progressAmount: "2/3",
        },
        {
          name: "정은이",
          danchuAmount: "32,000 단추",
          progressValue: 30,
          progressAmount: "1/3",
        },
        {
          name: "김응철",
          danchuAmount: "14,000 단추",
          progressValue: 60,
          progressAmount: "2/3",
        },
        {
          name: "유현수",
          danchuAmount: "14,000 단추",
          progressValue: 60,
          progressAmount: "2/3",
        },
        
      ],
      TodoList:[
        {
          done:false,
          task:"환전/결제 요청 확인",
          url:'/parent/request-list',
        },
        {
          done:true,
          task:"투자 가격 업데이트",
          url:'/parent/userinfo',
        },
        {
          done:true,
          task:"투자 가격 업데이트",
          url:'/parent/userinfo',
        }
      ]
    };
  },
  computed: {
    ...mapState('tempAccountStore', ['inTutorial', 'tutorialStep']),
  },
};
</script>
