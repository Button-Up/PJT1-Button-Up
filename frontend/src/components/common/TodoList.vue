<template>
  <div class="ma-2">
    <v-card class="px-0" elevation="2" rounded="lg" :color="backgroundColor">
      <v-container class="pa-2" width="200" fluid>
        <v-row align="center" class="mx-0" id="space-between">
          <v-checkbox
            v-model="todo.done"
            class="ml-2 font-weight-black black--text"
            :color="checkColor"
            :label="todo.task"
          >
          </v-checkbox>
          <v-btn
            icon
            class="ml-4"
            align="right"
            color="black"
            @click="goToPage"
          >
            <v-icon>mdi-chevron-right</v-icon>
          </v-btn>
        </v-row>
      </v-container>
    </v-card>
  </div>
</template>

<script>
export default {
  name: "TodoList",
  data() {
    return {
      backgroundColor: `parent04`,
      checkColor: `parent01`,
    };
  },
  props: {
    todo: Object,
  },
  methods: {
    goToPage: function () {
      this.$router.push(this.todo.url);
    },
  },
};
</script>

<style scoped>
.black--text /deep/ label {
  color: black;
}
#space-between {
  justify-content: space-between;
}
</style>
