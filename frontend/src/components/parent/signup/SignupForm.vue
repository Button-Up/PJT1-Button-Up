<template>
  <div>
    <div class="content mx-9 mt-10">
      <h2>부모님 회원가입</h2>
      <v-form>
        <v-text-field
          v-model="signupInfo.name"
          label="이름"
          required
          color="parent01"
        ></v-text-field>
        <v-text-field
          v-model="signupInfo.nickname"
          label="닉네임"
          required
          color="parent01"
        ></v-text-field>
        <v-text-field
          v-model="signupInfo.phone"
          label="전화번호"
          required
          color="parent01"
        ></v-text-field>
        <v-text-field
          v-model="signupInfo.email"
          label="이메일"
          required
          color="parent01"
        ></v-text-field>
        <v-text-field
          v-model="signupInfo.password1"
          label="비밀번호"
          required
          color="parent01"
        ></v-text-field>
        <v-text-field
          v-model="signupInfo.password2"
          label="비밀번호 확인"
          required
          color="parent01"
        ></v-text-field>
        <v-btn
          to="/parent/home"
          block
          color="parent01"
          class="white--text"
        >회원가입 완료</v-btn>
      </v-form>
    </div>
  </div>
</template>

<script>
export default {
  name: 'ParentSignupForm',
  data() {
    return {
      signupInfo: {
        name: null,
        nickname: null,
        phone: null,
        email: null,
        password1: null,
        password2: null
      }
    }
  }
}
</script>

<style>

</style>