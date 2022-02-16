<!--
  author : 정은이
  modified : 유현수 - Add v-if checkboxOn
  modified : 우정연 - 아이 체크리스트 수정되도록, 부모쪽 체크리스트 안보이도록 수정
-->
<template>
  <div class="ma-2">
    <v-card
      :disabled="onlyRead"
      class="px-0"
      elevation="2"
      rounded="lg"
      :color="todo.done ? (isParent ? 'parent04 ' : 'child03') : 'white'"
    >
      <v-container class="pa-2" width="200" fluid>
        <v-row align="center" class="mx-0" id="space-between" @click="clickEvent">
          <v-checkbox
            v-if="!isParent || (onlyRead && isParent && checkboxOn)"
            v-model="todo.flag"
            class="ml-2 pa-0 font-weight-black black--text"
            :color="isParent ? 'parent01' : 'child01'"
            :label="todo.content"
            @click="putCheckListRow()"
          >
          </v-checkbox>

          <div v-else class="ml-2 py-4 text-body-1 black--text">{{ todo.content }}</div>

          <v-btn v-if="isParent && !onlyRead" icon class="ml-4" align="right" color="black">
            <v-icon>mdi-chevron-right</v-icon>
          </v-btn>
        </v-row>
      </v-container>
    </v-card>
  </div>
</template>

<script>
import { apiPutCheckListRow } from '@/api/checkListAPI';
export default {
  name: 'TodoList',
  data() {
    return {};
  },
  props: {
    checkboxOn: {
      type: Boolean,
      default: true,
    },
    todo: Object,
    isParent: Boolean,
    onlyRead: Boolean,
  },
  methods: {
    clickEvent: function () {
      if (this.isParent) {
        this.$router.push(this.todo.url);
      }
    },
    putCheckListRow() {
      let toDoCheckRequest = {
        checkListSeq: this.todo.seq,
        flag: this.todo.flag,
      };
      apiPutCheckListRow(toDoCheckRequest, () => {
        console.log('체크리스트 수정 완료!');
      });
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
