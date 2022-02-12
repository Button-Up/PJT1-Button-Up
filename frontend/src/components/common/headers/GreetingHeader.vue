<template>
  <div>
    <v-app-bar dense :color="bgColor" elevation="0">
      <div v-if="userInfo">
        <v-toolbar-title> {{ userName }}님 안녕하세요 :D </v-toolbar-title>
      </div>
      <div v-else>
        <v-toolbar-title>
          <router-link to="/" class="text-decoration-none black--text">
            우리 아이 경제 교육의 첫단추
          </router-link>
        </v-toolbar-title>
      </div>
    </v-app-bar>
  </div>
</template>

<script>
import { mapState } from "vuex";

export default {
  name: "IndexHeader",
  props: {
    bgColor: {
      type: String,
      default: "white",
    },
  },
  computed: {
    ...mapState("userStore", ["userInfo"]),
    userName() {
      return this.userInfo.name;
    },
  },
};
</script>
