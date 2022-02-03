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
        v-model="signupInfo.phone"
        label="전화번호"
        required
        color="child01"
      ></v-text-field>
      <v-text-field
        v-model="signupInfo.password1"
        label="비밀번호"
        required
        color="child01"
      ></v-text-field>
      <v-text-field
        v-model="signupInfo.password2"
        label="비밀번호 확인"
        required
        color="child01"
      ></v-text-field>
      <v-btn
        to="/child/signup/sync-code"
        block
        color="child01"
        class="font-weight-bold"
      >회원가입 완료</v-btn>
    </v-form>
  </div>
</template>

<script>
export default {
  name: 'ChildSignup',
  data() {
    return {
      signupInfo: {
        name: null,
        phone: null,
        password1: null,
        password2: null
      }
    }
  }
}
</script>

<style>

</style>