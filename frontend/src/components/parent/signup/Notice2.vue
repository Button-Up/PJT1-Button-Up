<template>
  <div class="mx-9 mt-10">
    <h1>아이 회원가입을 <br> 먼저 완료해주세요.</h1>
    <p class="mt-4">부모님 계정과 연동을 위해 <br> 아이 계정 회원가입을 먼저 진행해주세요.</p>
    <v-btn
      to="/parent/signup/children-num-form"
      block
      color="parent01"
      class="white--text"
    >아이 회원가입을 완료했어요.</v-btn>
  </div>
</template>

<script>
export default {
  name: 'Notice2'
}
</script>

<style>

</style>