<template>
  <v-card
    class="mr-auto ma-0"
    outlined
    rounded="xl"
    elevation="4"
    :color="account.isDeposit ? 'parent01': 'parent03'"
  >
    <v-list-item three-line class="text-center justify-center pa-0">
      <v-list-item-content class="pa-2">
        
        <v-card-text v-text="account.isDeposit ? '예금' : '적금'" :class="account.isDeposit ? 'white--text' : 'black--text'" class="pt-3 pb-0"></v-card-text>
        <v-list-item-title v-text="account.amount" class="text-h4 ma-1 font-weight-bold " :class="account.isDeposit ? 'white--text' : 'black--text'">
        </v-list-item-title>
        <v-card-text v-text="`단추`" :class="account.isDeposit ? 'white--text' : 'black--text'" class="pb-3 pt-2"></v-card-text>
      </v-list-item-content>
    </v-list-item>
  </v-card>
</template>

<script>
export default {
  name: 'ChildAccount',
  data(){
    return{}
  },
  props:{
    account:Object
  }
}
</script>

<style>

</style>