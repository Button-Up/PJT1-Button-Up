<template>
  <div  class="ma-4">
    <h1 class="font-weight-black">아이 정보</h1>
    <v-sheet class="mx-auto" elevation="" max-width="1200">
      <v-slide-group v-model="model" class="pa-1" center-active show-arrows>
        <!--
          아이 정보 카드 클릭 시 activity로 이동하게 되어있습니다.
          추후 아이 상세 페이지로 이동하도록 수정 필요
        -->
        <v-slide-item
          justify-center
          v-for="(item, i) in items"
          :key="i"
          @click.native="$router.push('/parent/activity')"
        >
          <child-card-info
            @click="toggle"
            :item="item"
          ></child-card-info>
        </v-slide-item>
      </v-slide-group>
    </v-sheet>
  </div>
</template>
<script>
import ChildCardInfo from '@/components/parent/home/ChildCardInfo';

export default {
  name: "Home",
  components: { ChildCardInfo },
  data() {
    return {
      interval: {},
      value: 0,
      progressAmount: "2/3",
      items: [
        {
          name: "정은이",
          danchuAmount: "32,000 단추",
          progressValue: 30,
          progressAmount: "1/3",
        },
        {
          name: "김응철",
          danchuAmount: "14,000 단추",
          progressValue: 60,
          progressAmount: "2/3",
        },
        {
          name: "유현수",
          danchuAmount: "14,000 단추",
          progressValue: 60,
          progressAmount: "2/3",
        },
        {
          name: "정은이",
          danchuAmount: "32,000 단추",
          progressValue: 30,
          progressAmount: "1/3",
        },
        {
          name: "김응철",
          danchuAmount: "14,000 단추",
          progressValue: 60,
          progressAmount: "2/3",
        },
        {
          name: "유현수",
          danchuAmount: "14,000 단추",
          progressValue: 60,
          progressAmount: "2/3",
        },
        
      ],
    };
  },
  beforeDestroy() {
    clearInterval(this.interval);
  },
  mounted() {
    this.interval = setInterval(() => {
      if (this.value === 60) {
        return (this.value = 60);
      }
      this.value += 10;
    }, 1000);
  },
};
</script>
