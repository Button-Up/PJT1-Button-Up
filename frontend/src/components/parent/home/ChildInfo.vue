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
      <job-card> </job-card>
    </div> 
  </div>
</template>

<script>
import JobCard from '../../common/JobCard.vue'
import ChildAccount from './ChildAccount.vue'
export default {
  name: 'ChildInfo',
  components: { JobCard, ChildAccount },
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
    }
  }

}
</script>

<style scoped>
#space-between{
  justify-content: space-between
}
</style>