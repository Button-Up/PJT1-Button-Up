<template>
  <div class="mx-9 mt-10">
    <h1>아이 회원가입</h1>
    <v-form>
      <v-text-field
        v-model="childSignupInfo.name"
        label="이름"
        required
        color="child01"
      ></v-text-field>
      <v-text-field
        v-model="childSignupInfo.nickname"
        label="아이디"
        required
        color="child01"
      ></v-text-field>
      <v-text-field
        v-model="childSignupInfo.phone"
        label="전화번호"
        required
        color="child01"
      ></v-text-field>
      <v-text-field
        v-model="childSignupInfo.email"
        label="이메일"
        required
        color="child01"
      ></v-text-field>
      <v-text-field
        v-model="childSignupInfo.password"
        label="비밀번호"
        required
        color="child01"
        type="password"
      ></v-text-field>
      <v-text-field
        v-model="passwordConfirm"
        label="비밀번호 확인"
        required
        color="child01"
        type="password"
      ></v-text-field>
      <v-btn
        block
        color="child01"
        class="font-weight-bold"
        @click.native="childSignup"
        >회원가입 완료</v-btn
      >
    </v-form>
  </div>
</template>

<script>
import { apiSignup } from "@/api/userAPI.js";

export default {
  name: "ChildSignup",
  data() {
    return {
      isParent: false,
      childSignupInfo: {
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
    childSignup() {
      apiSignup(this.isParent, this.childSignupInfo)
        .then((res) => {
          console.log(res);
          console.log(this.childSignupInfo);
          this.$router.push({ name: "Login" });
        })
        .catch((err) => {
          console.log(err);
          console.log(this.childSignupInfo);
        });
    },
  },
};
</script>

<style></style>
