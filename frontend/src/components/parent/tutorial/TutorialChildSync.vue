<!--
  author: 유현수
-->

<template>
  <v-container class="fill-height">
    <v-row class="fill-height flex-column">
      <!-- 프로그레스 바 -->
      <v-col class="flex-grow-0">
        <v-progress-linear
          :value="($route.params.id / 2) * 100"
          color="parent01"
          :rounded="true"
          height="6"
        ></v-progress-linear>
      </v-col>

      <!-- 페이지 1 -->
      <v-col v-if="$route.params.id == 1">
        <v-card class="fill-height d-flex flex-column justify-center">
          <div class="px-6 pb-16">
            <h2>
              첫단추를 이용할 자녀가 <br />
              몇 명인가요?
            </h2>
            <v-form v-model="pageOneValid">
              <v-text-field
                v-model="numOfChildren"
                label="자녀 수"
                required
                color="parent01"
                :rules="numOfChildrenRule"
              ></v-text-field>
            </v-form>
          </div>
        </v-card>
      </v-col>

      <!-- 페이지 2 -->
      <v-col v-else>
        <v-card class="fill-height d-flex flex-column justify-center">
          <div class="px-6 pb-16">
            <h2>
              자녀의 아이디를 <br />
              입력해주세요.
            </h2>
            <p class="mt-4">
              자녀 계정으로 로그인하면 <br />
              아이디를 확인할 수 있어요.
            </p>
            <v-form v-model="pageTwoValid">
              <v-text-field
                v-for="(val, idx) in childrendNicknames"
                v-model="childrendNicknames[idx]"
                :key="idx"
                :label="`아이 ${idx + 1}의 아이디`"
                required
                :rules="childrendNicknameRule"
                color="parent01"
              ></v-text-field>
            </v-form>
          </div>
        </v-card>
      </v-col>

      <!-- 하단 버튼 -->
      <v-col class="flex-grow-0">
        <v-row dense>
          <v-col v-if="$route.params.id != 1">
            <v-btn
              block
              color="parent01"
              class="white--text"
              :to="`/parent/tutorial/child-sync/${-1 + +$route.params.id}`"
              >이전</v-btn
            >
          </v-col>
          <v-col v-if="$route.params.id != 2">
            <v-btn
              :disabled="!pageOneValid"
              block
              color="parent01"
              class="white--text"
              :to="`/parent/tutorial/child-sync/${1 + +$route.params.id}`"
              >다음</v-btn
            >
          </v-col>
          <v-col v-else>
            <v-btn
              :disabled="!pageTwoValid"
              block
              color="parent01"
              class="white--text"
              @click.native="putChildrenConnect(childrendNicknames)"
              >계정 연동하기</v-btn
            >
          </v-col>
        </v-row>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import { apiPutChildrenConnect } from "@/api/parentAPI";
import { mapGetters, mapActions } from "vuex";

export default {
  name: "TutorialChildSync",
  data() {
    return {
      // 1페이지 데이터
      pageOneValid: true,
      numOfChildren: null,
      numOfChildrenRule: [(v) => !!v || "첫단추를 이용할 자녀 수를 입력해주세요."],
      // 2페이지 데이터
      pageTwoValid: true,
      childrendNicknames: [],
      childrendNicknameRule: [(v) => !!v || "아이디를 입력해주세요."],
    };
  },
  computed: {
    ...mapGetters("userStore", ["checkUserInfo"]),
  },
  methods: {
    ...mapActions("parentStore", ["vuexPutTutorialStage", "vuexGetChildren"]),
    async putChildrenConnect(array) {
      // 아이 아이디 리스트 순회하면서 연결
      for (const item of array) {
        const nicknameInfo = {
          nickname: item,
          parentNickname: this.checkUserInfo.nickName,
        };
        await apiPutChildrenConnect(
          nicknameInfo,
          () => {
            console.log("부모-아이 연결 완료");
          },
          (err) => {
            console.log(err);
            console.log("부모-아이 연결 실패");
          }
        );
      }
      // 부모 유저의 아이 정보 업데이트
      await this.vuexGetChildren(this.checkUserInfo.seq);
      // 튜토리얼 단계 수정
      const tutorialInfo = {
        parentSeq: this.checkUserInfo.seq,
        stage: 2,
      };
      alert("자녀 계정 연동이 완료되었습니다.");
      await this.vuexPutTutorialStage(tutorialInfo);
      this.$router.push("/parent/home");
    },
  },
  watch: {
    numOfChildren() {
      this.childrendNicknames.length = this.numOfChildren;
      this.childrendNicknames.fill("");
    },
  },
};
</script>

<style></style>
