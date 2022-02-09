<template>
  <div>
    <div class="mx-9 mt-10">
      <!-- 부모/자녀 탭 -->
      <v-tabs :color="color">
        <v-tab @click="toggleLoginType">부모님 로그인</v-tab>
        <v-tab @click="toggleLoginType">자녀 로그인</v-tab>
      </v-tabs>

      <!-- 타이틀 -->
      <h1 class="mt-6">로그인</h1>

      <!-- 로그인 폼 -->
      <v-form>
        <!-- 휴대폰번호 input -->
        <v-text-field
          v-model="credentials.nickname"
          label="닉네임"
          required
          :color="color"
        ></v-text-field>

        <!-- 비밀번호 input -->
        <v-text-field
          v-model="credentials.password"
          label="비밀번호"
          required
          :color="color"
          type="password"
        ></v-text-field>

        <!-- 로그인 btn -->
        <v-btn
          :color="color"
          block
          :class="{ 'white--text': isParent }"
          class="font-weight-bold"
          @click="testLogin"
        >
          로그인
        </v-btn>
      </v-form>
    </div>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";
// import { login } from "@/api/userAPI.js";

export default {
  name: "Login",
  data() {
    return {
      isParent: true,
      color: "parent01",
      credentials: {
        nickname: null,
        password: null,
      },
    };
  },
  computed: {
    ...mapState("userStore", ["isLogin", "isLoginError"]),
  },
  methods: {
    ...mapActions("userStore", ["vuexUserLogin"]),

    async testLogin() {
      const userInfo = {
        isParent: this.isParent,
        credentials: this.credentials
      }
      await this.vuexUserLogin(userInfo);
      // let token = sessionStorage.getItem("access-token");
      // if (this.isLogin) {
      //   await this.vuexGetUserInfo(token);
      //   this.$router.push({ name: "Index" });
      // }
    },

    toggleLoginType() {
      this.isParent = !this.isParent;
      this.color = this.isParent ? "parent01" : "child01";
    },
  },
};
</script>

<style></style>
