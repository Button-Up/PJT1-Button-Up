<template>
  <div>
    <div class="mx-9 mt-10">
      <!-- 부모/자녀 탭 -->
      <v-tabs :color="color">
        <v-tab @click="isParentTrue">부모님 로그인</v-tab>
        <v-tab @click="isParentFalse">자녀 로그인</v-tab>
      </v-tabs>

      <!-- 타이틀 -->
      <h1 class="mt-6">로그인</h1>

      <!-- 로그인 폼 -->
      <v-form>
        <!-- 휴대폰번호 input -->
        <v-text-field
          v-model="credentials.nickname"
          label="닉네임"
          required
          :color="color"
        ></v-text-field>

        <!-- 비밀번호 input -->
        <v-text-field
          v-model="credentials.password"
          label="비밀번호"
          required
          :color="color"
        ></v-text-field>

        <!-- 로그인 btn -->
        <v-btn
          :color="color"
          block
          :class="{ 'white--text': isParent }"
          class="font-weight-bold"
          @click="tempLogin"
        >
          로그인
        </v-btn>
      </v-form>
    </div>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";
// import { login } from "@/api/userAPI.js";
export default {
  name: "Login",
  data() {
    return {
      isParent: true,
      color: "parent01",
      credentials: {
        nickname: null,
        password: null,
      },
    };
  },
  computed: {
    ...mapState("userStore", ["isLogin", "isLoginError"]),
  },
  methods: {
    ...mapActions("userStore", ["userLogin"]),

    isParentTrue() {
      this.isParent = true;
      this.color = "parent01";
    },

    isParentFalse() {
      this.isParent = false;
      this.color = "child01";
    }, // 부모로 로그인할건지 아이로 로그인 할건지

    // async userLogin() {
    //   await this.userLogin(this.isParent, this.credentials);
    //   let token = sessionStorage.getItem("access-token");
    //   if (this.isLogin) {
    //     await this.getUserInfo(token);
    //     this.$router.push({ name: "Index" });
    //   }
    // },

    ...mapActions("tempAccountStore", ["saveUserInfo"]),
    toggleLoginType() {
      this.isParent = !this.isParent;
      this.color = this.isParent ? "parent01" : "child01";
    },
    // 로그인 버튼을 누르면 무조건 로그인 되도록 만든 임시 로그인 메서드입니다.
    tempLogin() {
      const userInfo = {
        isParent: this.isParent,
        name: "유현수",
      };
      this.saveUserInfo(userInfo);
      this.$router.push(this.isParent ? "/parent/home" : "/child/home");
    },
  },
};
</script>

<style></style>
