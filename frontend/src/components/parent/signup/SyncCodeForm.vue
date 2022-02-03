<template>
  <div class="mx-9 mt-10">
    <h2>자녀의 계정 연동 코드를 <br> 입력해주세요.</h2>
    <p class="mt-4">자녀 핸드폰에서 로그인하면 나오는 <br> 계정 연동 코드를 입력해주세요.</p>
    <v-form>
      <v-text-field
        v-for="n in childrenList"
        :key="n"
        :label="`아이${n} 계정 연동 코드 입력`"
        required
        color="parent01"
      ></v-text-field>
    </v-form>
    <v-btn
      to="/parent/signup/type-select"
      block
      color="parent01"
      class="white--text"
    >다음</v-btn>
  </div>
</template>

<script>
export default {
  name: 'ParentSignupSyncCodeForm',
  props: {
    numOfChildren: {
      type: String,
      default: '1',
    }
  },
  computed: {
    childrenList() {
      return Array.from({length: this.numOfChildren}, (_, index) => index + 1)
    }
  }
}
</script>

<style>

</style>