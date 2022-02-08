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
              {{item.name}}
            </v-list-item-title>
            <v-list-item-subtitle class="justify-start ma-0 pa-0">
              {{item.danchuAmount}}
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
          {{ item.progressAmount }}완료
        </v-progress-circular>
      </div>
  </v-card>
  </div>
</template>

<script>
  export default {
    name: 'ChildCardInfo',
    data() {
      return {
        interval: {},
        value: 0,
      };
    },
    props: {
      item: Object,
    },
    beforeDestroy() {
      clearInterval(this.interval);
    },
    mounted() {
    this.interval = setInterval(() => {
      // console.log(this.value);
      if (this.value == this.item.progressValue) {
        return (this.value = this.item.progressValue);
      }
      this.value += 1;
    }, 50);
    },
  }
</script>