<!--
  author: 유현수
-->

<template>
  <div>
    <div class="mx-9 mt-10">
      <!-- 부모/자녀 탭 -->
      <v-tabs :color="color">
        <v-tab @click="loginTypeParent">부모님 로그인</v-tab>
        <v-tab @click="loginTypeChild">자녀 로그인</v-tab>
      </v-tabs>

      <!-- 타이틀 -->
      <h1 class="mt-6">로그인</h1>

      <!-- 로그인 폼 -->
      <v-form v-model="valid">
        <!-- 아이디 input -->
        <v-text-field
          v-model="credentials.nickname"
          :rules="nicknameRules"
          label="아이디"
          required
          :color="color"
        ></v-text-field>

        <!-- 비밀번호 input -->
        <v-text-field
          v-model="credentials.password"
          :rules="passwordRules"
          label="비밀번호"
          required
          :color="color"
          type="password"
          @keyup.enter="login"
        ></v-text-field>

        <!-- 로그인 btn -->
        <v-btn
          :disabled="!valid"
          :color="color"
          block
          :class="{ 'white--text': isParent }"
          class="font-weight-bold mt-4"
          @click="login"
        >
          로그인
        </v-btn>
        <!-- 회원가입 btn -->
        <v-btn :color="color" outlined block class="font-weight-bold mt-2">
          {{ isParent ? "부모님 회원가입" : "자녀 회원가입" }}
        </v-btn>
      </v-form>
    </div>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";

export default {
  name: "Login",
  data() {
    return {
      valid: true,
      isParent: true,
      color: "parent01",
      credentials: {
        nickname: null,
        password: null,
      },
      nicknameRules: [(v) => !!v || "아이디를 입력해주세요."],
      passwordRules: [(v) => !!v || "비밀번호를 입력해주세요."],
    };
  },
  computed: {
    ...mapState("userStore", ["isLogin", "isLoginError"]),
  },
  methods: {
    ...mapActions("userStore", ["vuexLogin", "vuexGetUserInfo"]),

    async login() {
      const loginInfo = {
        isParent: this.isParent,
        credentials: this.credentials,
      };
      await this.vuexLogin(loginInfo);
      if (this.isLogin) {
        await this.vuexGetUserInfo(loginInfo);
        this.$router.push(this.isParent ? "/parent/home" : "/child/home");
      } else {
        alert("아이디 혹은 비밀번호가 맞지 않습니다.");
      }
    },
    loginTypeParent() {
      this.isParent = true;
      this.color = "parent01";
    },
    loginTypeChild() {
      this.isParent = false;
      this.color = "child01";
    },
  },
};
</script>

<style></style>
