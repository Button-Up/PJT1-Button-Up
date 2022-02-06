<template>
  <div>
    <v-app-bar
      dense
      color="white"
      elevation="0"
    >
      <v-icon
        @click="onClickBackBtn(backBtnPath)"
        class="mr-6 back-btn"
      >mdi-arrow-left</v-icon>
      <v-toolbar-title v-if="$route.path.includes(`/parent/home/child-info`)">
        {{$route.params.name}}
      </v-toolbar-title>
      <v-toolbar-title v-else>
        {{ pageTitle }}
      </v-toolbar-title>
    </v-app-bar>
  </div>
</template>

<script>
export default {
  name: 'BackHeader',
  props: {
    pageTitle: {
      type: String,
      default: ''
    },
    backBtnPath: {
      type: String,
      default: ''
    }
  },
  methods: {
    onClickBackBtn(path) {
      if (path) {
        this.$router.push(path)
      } else {
        this.$router.go(-1)
      }
    }
  }
}
</script>

<style lang="scss" scoped>
  .back-btn {
    cursor: pointer;
  }
</style>