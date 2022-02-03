<template>
  <div class="mx-9 mt-10">
    <h2>부모님 회원가입</h2>
    <p class="mt-4">회원가입 방식을 선택해주세요.</p>
    <v-btn
      to="/parent/signup/form"
      block
      color="parent01"
      class="white--text"
    >일반 회원가입</v-btn>
    <v-btn
      block
      color="parent03"
      class="font-weight-bold mt-2"
    >카카오 소셜 로그인</v-btn>
  </div>
</template>

<script>
export default {
  name: 'TypeSelect'
}
</script>

<style>

</style>