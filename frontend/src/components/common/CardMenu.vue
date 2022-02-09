<template>
  <v-card
    :color="customColor"
    :height="customHeight"
    class="d-flex justify-center align-center card-menu"
  >
    <div
      class="d-flex flex-column justify-center mt-3"
      :class="customClass"
    >
      <v-icon 
        :size="iconSize"
        :color="iconColor"
      >{{ iconName }}</v-icon>
      <h2 class="text-center">{{ title }}</h2>
      <p class="text-center">{{ subtitle }}</p>
    </div>
  </v-card>
</template>

<script>
export default {
  name: 'CardMenu',
  props: {
    customClass: {
      type: String,
      default: ''
    },
    customColor: {
      type: String,
      default: 'child01'
    },
    customHeight: {
      type: String,
      default: '160px'
    },
    iconName: {
      type: String,
      default: 'mdi-information'
    },
    iconSize: {
      type: String,
      default: '3rem'
    },
    iconColor: {
      type: String,
      default: 'black'
    },
    title: {
      type: String,
      default: 'title'
    },
    subtitle: {
      type: String,
      default: 'subtitle'
    }
  }
}
</script>

<style>
.card-menu {
  cursor: pointer;
}
</style>