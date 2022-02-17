<template>
  <div>
    <v-row justify="center">
      <v-dialog v-model="visible" persistent max-width="290">
        <v-card>
          <v-card-title class="text-h6">{{ title }} </v-card-title>
          <v-card-text> {{ content }}</v-card-text>
          <v-card-actions class="pb-4">
            <v-spacer></v-spacer>

            <v-btn
              v-if="ishaveNegBtn"
              :color="isParent ? 'parent01' : 'child01'"
              elevation="0"
              @click="clickPositive"
              :class="isParent ? 'white--text' : 'black--text'"
            >
              {{ textPositiveBtn }}
            </v-btn>
            <v-btn
              v-if="ishaveNegBtn"
              :color="isParent ? 'parent02' : 'child04'"
              elevation="0"
              @click="clickNegative"
            >
              {{ textNegativeBtn }}
            </v-btn>

            <v-btn
              v-else
              block
              :color="isParent ? 'parent01' : 'child01'"
              elevation="0"
              @click="clickPositive"
              :class="isParent ? 'white--text' : 'black--text'"
              >{{ textPositiveBtn }}</v-btn
            >
          </v-card-actions>
        </v-card>
      </v-dialog>
    </v-row>
  </div>
</template>

<script>
export default {
  props: {
    visible: {
      type: Boolean,
      require: true,
      default: false,
    },
    isParent: Boolean,
    ishaveNegBtn: {
      type: Boolean,
      default: true,
    },
    title: String,
    content: String,
    positiveAction: Function,
    textPositiveBtn: {
      type: String,
      default: `예`,
    },
    textNegativeBtn: {
      type: String,
      default: `아니오`,
    },
  },
  methods: {
    clickPositive() {
      // this.visible = false;
      this.$emit("update:visible", false);
      this.positiveAction();
    },
    clickNegative() {
      this.$emit("update:visible", false);
    },
  },
};
</script>

<style></style>
