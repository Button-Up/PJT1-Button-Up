<!--
  author: 유현수
-->

<template>
  <div class="my-5 mx-6">
    <CardMenu
      iconName="mdi-account-wrench"
      customColor="parent02"
      title="직업 관리"
      subtitle=""
      @click.native="$router.push('/parent/activity/job')"
    ></CardMenu>
    <br />
    <CardMenu
      iconName="mdi-chart-areaspline-variant"
      customColor="parent02"
      title="투자 활동 관리"
      subtitle=""
      @click.native="
        $router.push('/parent/activity/invest' + (getInvestList.length === 0 ? '/new/1' : ''))
      "
    ></CardMenu>
    <br />
    <CardMenu
      iconName="mdi-cards-playing"
      customColor="parent02"
      title="소원권 관리"
      subtitle=""
    ></CardMenu>
  </div>
</template>

<script>
import CardMenu from "@/components/common/CardMenu";
import { mapActions, mapGetters } from "vuex";

export default {
  name: "Activity",
  components: {
    CardMenu,
  },
  computed: {
    ...mapGetters("investStore", ["getInvestList"]),
    ...mapGetters("userStore", ["checkUserInfo"]),
  },
  mounted() {
    this.vuexUpdateNewsList(this.checkUserInfo.seq);
  },
  methods: {
    ...mapActions("investStore", ["vuexUpdateNewsList"]),
  },
};
</script>
