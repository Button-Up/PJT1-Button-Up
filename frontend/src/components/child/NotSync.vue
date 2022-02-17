<!--
  author: 유현수
-->

<template>
  <v-sheet class="d-flex align-center" height="500px">
    <v-sheet class="mx-6 rounded-lg" elevation="2" width="100%">
      <div class="pa-4">
        <h2>
          부모님 계정과 <br />
          연결이 필요해요!
        </h2>
        <p class="mt-3 text-subtitle-2">
          부모님 튜토리얼 페이지에서 <br />
          내 아이디를 입력하면 계정을 연결할 수 있어요.
        </p>
        <v-sheet class="my-3 pa-3 rounded-lg text-center font-weight-bold" color="child01">
          내 아이디: {{ checkUserInfo.nickName }}
        </v-sheet>
      </div>
    </v-sheet>
  </v-sheet>
</template>

<script>
import { mapGetters } from "vuex";

export default {
  name: "ChildNotSync",
  computed: {
    ...mapGetters("userStore", ["checkUserInfo"]),
  },
};
</script>

<style></style>
