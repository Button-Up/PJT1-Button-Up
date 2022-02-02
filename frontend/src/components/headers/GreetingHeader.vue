<template>
  <div>
    <v-app-bar
      dense
      color="white"
      elevation="0"
    >
      <v-toolbar-title>
        {{ userName }}님 안녕하세요 :D
      </v-toolbar-title>
    </v-app-bar>
  </div>
</template>

<script>
import { mapState } from 'vuex'

export default {
  name: 'IndexHeader',
  computed: {
    ...mapState('tempAccountStore', ['userInfo']),
    userName() {
      return this.userInfo.name
    }
  }
}
</script>