<template>
  <div>
    <div class="content mx-9 mt-10">
      <h2>부모님 회원가입</h2>
      <v-form>
        <v-text-field
          v-model="parentSignupInfo.name"
          label="이름"
          required
          color="parent01"
        ></v-text-field>
        <v-text-field
          v-model="parentSignupInfo.nickname"
          label="닉네임"
          required
          color="parent01"
        ></v-text-field>
        <v-text-field
          v-model="parentSignupInfo.phone"
          label="전화번호"
          required
          color="parent01"
        ></v-text-field>
        <v-text-field
          v-model="parentSignupInfo.email"
          label="이메일"
          required
          color="parent01"
        ></v-text-field>
        <v-text-field
          v-model="parentSignupInfo.password"
          label="비밀번호"
          required
          color="parent01"
        ></v-text-field>
        <v-text-field
          v-model="passwordConfirm"
          label="비밀번호 확인"
          required
          color="parent01"
        ></v-text-field>
        <v-btn
          to="/parent/home"
          block
          color="parent01"
          class="white--text"
          @click.native="parentSignup"
          >회원가입 완료</v-btn
        >
      </v-form>
    </div>
  </div>
</template>

<script>
import { signup } from "@/api/userAPI.js";
export default {
  name: "ParentSignupForm",
  data() {
    return {
      isParent: true,
      parentSignupInfo: {
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
      signup(this.isParent, this.parentSignupInfo)
        .then((res) => {
          console.log(res);
          console.log(this.parentSignupInfo);
          this.$router.push({ name: "Login" });
        })
        .catch((err) => {
          console.log(err);
          console.log(this.parentSignupInfo);
        });
    },
  },
};
</script>

<style></style>
