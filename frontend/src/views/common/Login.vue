<!--
  author: 유현수
  modified : 우정연 - 아이 리스트 가져와서 vuex 저장
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
import { mapGetters, mapState, mapActions } from "vuex";

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
    ...mapGetters("userStore", ["checkUserInfo", "checkIsLogin"]),
    ...mapState("userStore", ["isLogin", "isLoginError"]),
  },
  methods: {
    ...mapActions("userStore", ["vuexLogin", "vuexGetUserInfo"]),
    ...mapActions("parentStore", ["vuexGetChildren", "vuexGetTutorialStage"]),

    async login() {
      const loginInfo = {
        isParent: this.isParent,
        credentials: this.credentials,
      };
      // vuex에 jwt 저장
      await this.vuexLogin(loginInfo);
      // vuex에 유저 정보 저장
      await this.vuexGetUserInfo(loginInfo);
      // 부모 유저라면 vuex에 아이 목록 저장
      if (this.checkIsLogin) {
        if (this.isParent) {
          await this.vuexGetChildren(this.checkUserInfo.seq);
          await this.vuexGetTutorialStage(this.checkUserInfo.seq);
        }
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
