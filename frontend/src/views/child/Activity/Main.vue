<!--
  author: 유현수
-->

<template>
  <NotSync v-if="checkNotSync"></NotSync>
  <v-sheet v-else class="my-5 mx-6">
    <CardMenu
      iconName="mdi-book-multiple"
      customColor="child02"
      title="경제 공부방"
      subtitle="모르는 단어나 개념이 있다면?"
      @click.native="$router.push('/child/activity/study')"
    ></CardMenu>
    <br />
    <CardMenu
      v-if="!getSavingStatus"
      iconName="mdi-account-cash"
      customColor="child02"
      title="적금 통장 개설"
      subtitle="월 이자 5% 적금, 3분만에 간편하게 개설하기"
      @click.native="$router.push('/child/activity/saving/1')"
    ></CardMenu>
    <br v-if="!getSavingStatus" />
    <CardMenu
      iconName="mdi-chart-areaspline-variant"
      customColor="child02"
      title="단추 투자"
      subtitle=""
      @click.native="$router.push('/child/activity/invest')"
    ></CardMenu>
  </v-sheet>
</template>

<script>
import NotSync from "@/components/child/NotSync";
import CardMenu from "@/components/common/CardMenu";
import { mapGetters } from "vuex";

export default {
  name: "Activity",
  components: {
    CardMenu,
    NotSync,
  },
  computed: {
    ...mapGetters("savingStore", ["getSavingStatus"]),
    ...mapGetters("userStore", ["checkNotSync"]),
  },
};
</script>
