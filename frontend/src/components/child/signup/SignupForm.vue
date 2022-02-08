<template>
  <div class="mx-9 mt-10">
    <h1>아이 회원가입</h1>
    <v-form>
      <v-text-field
        v-model="signupInfo.name"
        label="이름"
        required
        color="child01"
      ></v-text-field>
      <v-text-field
        v-model="signupInfo.nickname"
        label="아이디"
        required
        color="child01"
      ></v-text-field>
      <v-text-field
        v-model="signupInfo.phone"
        label="전화번호"
        required
        color="child01"
      ></v-text-field>
      <v-text-field
        v-model="signupInfo.email"
        label="이메일"
        required
        color="child01"
      ></v-text-field>
      <v-text-field
        v-model="signupInfo.password"
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
        @click.native="signup"
      >회원가입 완료</v-btn>
    </v-form>
  </div>
</template>

<script>
import { childSignup } from '@/api/account.js'

export default {
  name: 'ChildSignup',
  data() {
    return {
      signupInfo: {
        email: null,
        nickname: null,
        name: null,
        phone: null,
      },
      passwordConfirm: null
    }
  },
  methods: {
    signup() {
      childSignup(this.signupInfo)
        .then(res => {
          console.log(res)
        })
        .catch(err => {
          console.log(err)
        })
    }
  }
}
</script>

<style>

</style>