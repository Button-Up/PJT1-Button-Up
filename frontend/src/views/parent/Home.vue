<template>
  <div  class="ma-4">
    <h1>
      <p class="font-weight-black">아이 정보</p>
    </h1>
    <v-sheet class="mx-auto" elevation="" max-width="1200">
      <v-slide-group v-model="model" class="pa-1" center-active show-arrows>
        <v-slide-item
          justify-center
          v-for="n in 5"
          :key="n"
          v-slot="{ active, toggle }"
        >
          <v-card
            elevation="4"
            max-width="250"
            outlined
            :color="active ? 'parent02' : 'white'"
            class="ma-4 justify-center"
            height="200"
            width="150"
            @click="toggle"
          >
            <div class="text-center mb-1">
              <v-list-item two-line>
                <v-list-item-content class="pa-2 mb-1">
                  <!-- <div class="text-overline mb-4 justify-center">OVERLINE</div> -->
                  <v-list-item-title class="font-weight-black text-h5 justify-start ma-2 pa-0">
                    정은이
                  </v-list-item-title>
                  <v-list-item-subtitle class="justify-start ma-0 pa-0"
                    >14,000단추</v-list-item-subtitle
                  >
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
                {{ progressAmount }}
                완료
              </v-progress-circular>
            </div>
            <!-- </v-card> -->
            <v-row
              class="fill-height justify-center"
              align="center"
              justify="center"
            >
              <v-flex justify-center>
                <v-scale-transition>
                  <v-icon
                    v-if="active"
                    color="white"
                    size="48"
                    v-text="'mdi-close-circle-outline'"
                  ></v-icon>
                </v-scale-transition>
              </v-flex>
            </v-row>
          </v-card>
        </v-slide-item>
      </v-slide-group>
    </v-sheet>

  </div>
</template>

<script>
export default {
  name: "Home",
  data() {
    return {
      interval: {},
      value: 0,
      progressAmount: "2/3",
      items: [
        {
          color: "#1F7087",
          src: "https://cdn.vuetifyjs.com/images/cards/foster.jpg",
          title: "Supermodel",
          artist: "Foster the People",
        },
        {
          color: "#952175",
          src: "https://cdn.vuetifyjs.com/images/cards/halcyon.png",
          title: "Halcyon Days",
          artist: "Ellie Goulding",
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
