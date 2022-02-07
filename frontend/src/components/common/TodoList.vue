<template>
  <div class="ma-2">
    <v-card :disabled="onlyRead"
      class="px-0"
      elevation="2"
      rounded="lg"
      :color="todo.done ? ( isParent ? 'parent04 ': 'child03')  :'white'">
  
    <v-container
      class="pa-2"
      width="200"
      fluid 
    > 
      <v-row
          align="center"
          class="mx-0"
          id="space-between"
      >
        <v-checkbox
          v-if="checkboxOn"
          v-model="todo.done"
          class="ml-2 pa-0 font-weight-black black--text"
          :color="isParent ? 'parent01': 'child01' "
          :label="todo.task"
          >
        </v-checkbox>

        <div class="ml-2 py-4 text-body-1 black--text">{{ todo.task }}</div>
        
        <v-btn v-if="(isParent && !onlyRead)"
          icon class="ml-4"
          align="right"
          color="black"
          @click="goToPage">
            <v-icon>mdi-chevron-right</v-icon>
        </v-btn>
      </v-row>
    </v-container>
    </v-card>
  </div>
</template>

<script>
export default {
  name: 'TodoList',
  data() {
    return{
    }
  },
  props:{
    checkboxOn: Boolean,
    todo :Object,
    isParent:Boolean,
    onlyRead:Boolean
  },
  methods:{
    goToPage:function(){
      this.$router.push(this.todo.url);
    }
  },
}
</script>

<style scoped>
.black--text /deep/ label {
    color: black;
}
#space-between{
  justify-content: space-between
}
</style>