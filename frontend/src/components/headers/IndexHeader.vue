<template>
  <div>
    <v-app-bar
      dense
      color="transparent"
      elevation="0"
      absolute
    >
      <v-spacer></v-spacer>
      <v-btn
        to="/login"
        class="white--text mr-2"
        text
      >
        로그인
      </v-btn>
      <v-btn text class="white--text">회원가입</v-btn>
    </v-app-bar>
  </div>
</template>

<script>
export default {
  name: 'IndexHeader'
}
</script>

<style>

</style>