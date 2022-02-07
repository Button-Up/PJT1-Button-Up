<template>
  <div>
    <v-card 
      class="mx-auto ma-4 pa-1 mb-8"
      max-width="344"
      rounded="xl"
      outlined
    >
      <v-list-item class="mt-4 ml-0 ">
        <v-avatar size="60" color="grey">
          <img
            :src="job.image"
            :alt="job.name"
          >
        </v-avatar>
        <v-list-item-title class="text-h5 ma-4">
          {{job.name}}
        </v-list-item-title>
      </v-list-item>
      <div class="ml-2 mr-2 mt-4 mb-6">
        <v-list-item v-for="(todo, t) in TodoList"  :key="t" class="pa-4" style="display:contents">
          <todo-list class="pb-1" :todo="todo" :isParent="isParent" :onlyRead="true"></todo-list>
        </v-list-item>
      </div>
    </v-card>
  </div>
</template>

<script>
import TodoList from '../../components/common/TodoList.vue'
export default {
  name: "JobCard",
  components: { 
    TodoList, 
  },
  props:{
    isParent:Boolean
  },
  data() {
      return {
        job:{
          name:'청소부',
          image: 'https://cdn.vuetifyjs.com/images/john.jpg'
        },
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
      };
    },
}
</script>

<style>

</style>