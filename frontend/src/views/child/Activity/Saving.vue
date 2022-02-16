<!--
  author: 유현수
-->

<template>
  <v-container class="fill-height">
    <v-row class="fill-height flex-column">
      <!-- 프로그레스 바 -->
      <v-col class="flex-grow-0">
        <v-progress-linear
          :value="($route.params.id / 3) * 100"
          color="child01"
          :rounded="true"
          height="6"
        ></v-progress-linear>
      </v-col>

      <v-col>
        <v-card class="fill-height d-flex flex-column justify-center">
          <!-- 페이지 1 -->
          <div
            v-if="$route.params.id == 1"
            class="d-flex flex-column align-center text-center py-7"
          >
            <h2>자유적립식적금 상품안내</h2>
            <v-img
              src="https://cdn-icons-png.flaticon.com/512/4221/4221591.png"
              alt=""
              class="my-10"
              max-height="130px"
              max-width="130px"
            />
            <p class="mt-2">
              만기일 전까지 원하는 때 <br />
              원하는만큼 입금할 수 있어요.
            </p>
          </div>
          <!-- 페이지 2 -->
          <div
            v-else-if="$route.params.id == 2"
            class="d-flex flex-column align-center text-center py-7"
          >
            <h2>이자율 안내</h2>
            <v-img
              src="https://cdn-icons-png.flaticon.com/512/2474/2474455.png"
              alt=""
              class="my-10"
              max-height="130px"
              max-width="130px"
            />
            <p class="mt-2">
              만기 시 원금의 5%에 해당하는 <br />
              이자를 함께 받을 수 있어요.
            </p>
          </div>
          <!-- 페이지 3 -->
          <div v-else class="d-flex flex-column align-center text-center py-7">
            <h2>중도 해지 시 불이익 안내</h2>
            <v-img
              src="https://cdn-icons-png.flaticon.com/512/2721/2721122.png"
              alt=""
              class="my-10"
              max-height="130px"
              max-width="130px"
            />
            <p class="mt-2">
              만기일 전 적금을 해지하면 <br />
              원금만 돌려받을 수 있어요.
            </p>
          </div>
        </v-card>
      </v-col>

      <!-- 하단 버튼 -->
      <v-col class="flex-grow-0">
        <v-row dense>
          <v-col v-if="$route.params.id != 1">
            <v-btn
              block
              color="child01"
              class="font-weight-bold"
              :to="`/child/activity/saving/${-1 + +$route.params.id}`"
              >이전</v-btn
            >
          </v-col>
          <v-col v-if="$route.params.id != 3">
            <v-btn
              block
              color="child01"
              class="font-weight-bold"
              :to="`/child/activity/saving/${1 + +$route.params.id}`"
              >다음</v-btn
            >
          </v-col>
          <v-col v-else>
            <!-- 모달 -->
            <v-btn color="child01" block @click.native="dialog = true" class="font-weight-bold">
              잘 이해했어요!
            </v-btn>
            <v-row justify="center">
              <v-dialog v-model="dialog" persistent max-width="290">
                <v-card>
                  <v-card-title class="text-h5"> 확인해주세요! </v-card-title>
                  <v-card-text>적금 통장을 개설하시겠어요?</v-card-text>
                  <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn color="child01" elevation="0" @click="[openSavings(), (dialog = false)]">
                      예
                    </v-btn>
                    <v-btn
                      color="child04"
                      elevation="0"
                      @click="dialog = false"
                      to="/child/activity"
                    >
                      아니오
                    </v-btn>
                  </v-card-actions>
                </v-card>
              </v-dialog>
            </v-row>
          </v-col>
        </v-row>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import { mapGetters, mapActions } from "vuex";

export default {
  name: "ChildSaving",
  data() {
    return {
      dialog: false,
    };
  },
  computed: {
    ...mapGetters("userStore", ["checkUserInfo"]),
  },
  methods: {
    ...mapActions("savingStore", ["vuexPostOpenSavings"]),
    openSavings() {
      this.vuexPostOpenSavings(this.checkUserInfo.seq).then(() => {
        alert("적금 개설이 완료되었습니다.");
        this.$router.push("/child/home");
      });
    },
  },
};
</script>

<style></style>
