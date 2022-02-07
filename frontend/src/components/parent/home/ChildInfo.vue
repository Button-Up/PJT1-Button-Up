<template>
  <div class="mx-6 mt-6">
    <!-- 아이의 단추 계좌 START -->
    <div>
      <v-row id="space-between" class="ma-0 align-center"> 
        <h2>아이의 단추 계좌</h2>
        <v-btn small color="parent01" class="white--text">입금하기</v-btn>
      </v-row>
      <v-row >
        <v-col v-for="(account, a) in accounts"
            :key="a" class="mt-2 pa-1 align-center"> 
        <child-account :account="account"></child-account>
        </v-col>
      </v-row>
      <v-list-item>

      </v-list-item>
    </div> 
    <!-- 아이의 단추 계좌 END -->
    <div>
      <h2>아이의 할 일 진행 상황</h2>
      <job-with-todo-list-card :isParent="true" :job="job"></job-with-todo-list-card>
    </div> 
  </div>
</template>

<script>
import JobWithTodoListCard from '../../common/JobWithTodoListCard.vue'
import ChildAccount from './ChildAccount.vue'
export default {
  name: 'ChildInfo',
  components: { JobWithTodoListCard, ChildAccount },
  data(){
    return{
      accounts:[
        {
          isDeposit:false,
          amount:"25,000"
        },
        // {
        //   isDeposit:false,
        //   amount:"15,000"
        // },
      ],
      job:{
          name:'청소부',
          image: 'https://cdn.vuetifyjs.com/images/john.jpg',
          TodoList:[
          {
            done:false,
            task:"투자 가격 업데이트",
            url:'/parent/activity',
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
        },
    }
  }

}
</script>

<style scoped>
#space-between{
  justify-content: space-between
}
</style>