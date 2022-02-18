<template>
  <div>
    <v-sheet
      :color="isParent ? 'parent02' : 'child01'"
      width="100%"
      height="100%"
      class="d-flex justify-center align-center"
    >
      <div class="d-flex flex-column justify-center align-center mt-12 mb-8">
        <v-avatar :color="isParent ? 'parent01' : 'child04'" size="80">
          <v-icon dark size="40">mdi-account-circle</v-icon>
        </v-avatar>
        <!-- 이름 -->
        <h2 class="mt-3">{{ checkUserInfo.name }}</h2>
        <v-btn
          :color="isParent ? 'parent01' : 'child04'"
          class="mt-4"
          :class="{ 'white--text': isParent }"
          @click="logout"
          >로그아웃</v-btn
        >
      </div>
    </v-sheet>
    <v-sheet class="d-flex justify-center align-center" height="300px">
      <div class="d-flex flex-column align-start">
        <v-btn text class="text-h6" max-width="300px" color="#4e4e4e">
          <v-icon left size="20"> mdi-pencil</v-icon>
          내 정보 수정
        </v-btn>
        <v-btn
          v-if="isParent"
          to="/parent/tutorial/child-sync/1"
          text
          class="text-h6 mt-6"
          max-width="300px"
          color="#4e4e4e"
        >
          <v-icon left size="20"> mdi-account-plus</v-icon>
          자녀 계정 연결
        </v-btn>
      </div>
    </v-sheet>
    <Modal
      :visible.sync="dialog"
      :isParent="isParent"
      :title="`로그아웃 되었습니다.`"
      :content="``"
      :textPositiveBtn="`확인`"
      :positiveAction="goLogout"
      :ishaveNegBtn="false"
    ></Modal>
  </div>
</template>

<script>
import { mapGetters, mapActions } from "vuex";
import Modal from "../../components/common/Modal.vue";

export default {
  name: "UserInfo",
  components: {
    Modal,
  },
  data() {
    return {
      dialog: false,
    };
  },
  props: {
    isParent: {
      type: Boolean,
      default: true,
    },
  },
  computed: {
    ...mapGetters("userStore", ["checkUserInfo"]),
  },
  methods: {
    ...mapActions("userStore", ["vuexLogout"]),
    logout() {
      // this.vuexLogout();
      // this.$router.push("/login");
      // alert("로그아웃 되었습니다.");
      this.dialog = true;
    },
    goLogout() {
      this.vuexLogout();
      this.goToLogin();
    },
    goToLogin() {
      this.$router.push("/login");
    },
  },
};
</script>

<style scoped>
.update-userinfo {
  padding: 0.8rem;
  width: 160px;
  margin: 100px auto;
}
</style>
