<!--
  author: 유현수
-->

<template>
  <div class="mx-9 mt-10">
    <h1>{{ isParent ? "부모" : "자녀" }} 회원가입</h1>
    <v-form v-model="valid">
      <v-text-field
        v-model="signupInfo.name"
        :rules="checkValid.name"
        label="이름"
        required
        :color="isParent ? 'parent01' : 'child01'"
      ></v-text-field>
      <v-text-field
        v-model="signupInfo.nickname"
        :rules="checkValid.nickname"
        label="아이디"
        required
        :color="isParent ? 'parent01' : 'child01'"
      ></v-text-field>
      <v-text-field
        v-model="signupInfo.phone"
        :rules="checkValid.phone"
        label="전화번호"
        required
        :color="isParent ? 'parent01' : 'child01'"
      ></v-text-field>
      <v-text-field
        v-model="signupInfo.email"
        :rules="checkValid.email"
        label="이메일"
        required
        :color="isParent ? 'parent01' : 'child01'"
      ></v-text-field>
      <v-text-field
        v-model="signupInfo.password"
        :rules="checkValid.password"
        label="비밀번호"
        required
        :color="isParent ? 'parent01' : 'child01'"
        type="password"
      ></v-text-field>
      <v-text-field
        v-model="passwordConfirm"
        :rules="checkValid.passwordConfirm"
        label="비밀번호 확인"
        required
        :color="isParent ? 'parent01' : 'child01'"
        type="password"
      ></v-text-field>
      <v-btn
        :disabled="!valid"
        block
        :color="isParent ? 'parent01' : 'child01'"
        class="font-weight-bold mt-4"
        :class="{ 'white--text': isParent }"
        @click.native="isParent ? parentSignup() : childSignup()"
        >회원가입 완료</v-btn
      >
    </v-form>
  </div>
</template>

<script>
import { apiSignup } from "@/api/userAPI.js";

export default {
  name: "Signup",
  props: {
    isParent: Boolean,
  },
  data() {
    return {
      valid: true,
      checkValid: {
        email: [(v) => !!v || "이메일를 입력해주세요."],
        name: [(v) => !!v || "이름을 입력해주세요."],
        nickname: [(v) => !!v || "아이디를 입력해주세요."],
        password: [(v) => !!v || "비밀번호를 입력해주세요."],
        passwordConfirm: [
          (v) => !!v || "비밀번호를 한 번 더 입력해주세요.",
          (v) => v === this.signupInfo.password || "비밀번호가 일치하지 않습니다.",
        ],
        phone: [(v) => !!v || "전화번호를 입력해주세요."],
      },
      signupInfo: {
        email: null,
        name: null,
        nickname: null,
        password: null,
        phone: null,
      },
      passwordConfirm: null,
    };
  },
  methods: {
    parentSignup() {
      apiSignup(this.isParent, this.signupInfo)
        .then((res) => {
          console.log(res);
          console.log(this.signupInfo);
          this.$router.push({ name: "Login" });
        })
        .catch((err) => {
          console.log(err);
          console.log(this.signupInfo);
        });
    },
    childSignup() {
      apiSignup(this.isParent, this.signupInfo)
        .then((res) => {
          console.log(res);
          console.log(this.signupInfo);
          this.$router.push({ name: "Login" });
        })
        .catch((err) => {
          console.log(err);
          console.log(this.signupInfo);
        });
    },
  },
};
</script>

<style></style>
