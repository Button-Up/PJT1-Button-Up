<template>
  <div class="mx-9 mt-10">
    <h2>첫단추를 이용할 자녀가 <br> 몇 명인가요?</h2>
    <v-form>
      <v-text-field
        v-model="numOfChildren"
        label="자녀 수"
        required
        color="parent01"
      ></v-text-field>
    </v-form>
    <v-btn
      :to="{
        name: 'ParentSignupSyncCodeForm',
        params: { numOfChildren: numOfChildren }
      }"
      block
      color="parent01"
      class="white--text"
    >다음</v-btn>
  </div>
</template>

<script>
export default {
  name: 'ChildrenNumForm',
  data() {
    return {
      numOfChildren: null
    }
  }
}
</script>

<style>

</style>