<!--
  author : 정은이
-->
<template>
  <div>
    <v-card
      elevation="4"
      max-width="250"
      outlined
      shaped
      class="ma-4 justify-center"
      height="200"
      width="150"
    >
      <div class="text-center mb-1">
        <v-list-item two-line>
          <v-list-item-content class="pa-2 mb-1">
            <v-list-item-title class="font-weight-black text-h5 justify-start ma-2 pa-0">
              {{ item.name }}
            </v-list-item-title>
            <v-list-item-subtitle class="justify-start ma-0 pa-0">
              {{ item.danchuAmount }}
            </v-list-item-subtitle>
          </v-list-item-content>
        </v-list-item>
        <v-progress-circular
          align="center"
          :rotate="-90"
          :size="100"
          :width="20"
          :value="value"
          color="parent01"
          class="justify-center mb-4"
        >
          {{ doneCheckList.length }}/{{ checkList.length }} 완료
        </v-progress-circular>
      </div>
    </v-card>
  </div>
</template>

<script>
import { apiGetCheckListRow } from "@/api/checkListAPI.js";

export default {
  name: "ChildCardInfo",
  data() {
    return {
      interval: {},
      value: 0,
      checkList: [],
    };
  },
  props: {
    item: Object,
  },
  beforeDestroy() {
    clearInterval(this.interval);
  },
  created() {
    apiGetCheckListRow(
      this.item.seq,
      (response) => {
        this.checkList = response.data;
      },
      (error) => {
        console.log(error);
      }
    );
  },
  computed: {
    doneCheckList() {
      return this.checkList.filter((val) => {
        return val.flag === true;
      });
    },
  },
  mounted() {
    this.interval = setInterval(() => {
      if (this.checkList.length == 0) {
        return;
      }
      // console.log(this.value);
      if (this.value == parseInt((this.doneCheckList.length / this.checkList.length) * 100)) {
        return (this.value = parseInt((this.doneCheckList.length / this.checkList.length) * 100));
      }
      this.value += 1;
    }, 50);
  },
};
</script>
