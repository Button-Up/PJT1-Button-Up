<!--
  author : 유현수

  modified by Jiun Kim, 2022-02-08 18:00
-->
<template>
  <div class="text-center">
    <v-bottom-sheet v-model="sheet">
      <!-- 바텀 시트 on/off 버튼 -->
      <template v-slot:activator="{ on, attrs }">
        <slot name="activatorBtn">
          <v-btn
            :color="btnColor"
            :class="btnClass"
            :icon="isIcon"
            v-bind="attrs"
            v-on="on"
            :block="isBlockBtn"
            :width="customBtnWidth"
          >
            <v-icon v-if="isIcon">{{ iconName }}</v-icon>
            {{ btnName }}
          </v-btn>
        </slot>
      </template>

      <!-- 바텀 시트 내부 영역 -->
      <v-sheet :height="sheetHeight">
        <slot name="body">
          <div class="pa-6">This is a bottom sheet</div>
        </slot>
      </v-sheet>
    </v-bottom-sheet>
  </div>
</template>

<script>
export default {
  data() {
    return {
      sheet: false,
    };
  },
  props: {
    sheetHeight: {
      type: String,
      default: "fit-content",
    },
    isBlockBtn: {
      type: Boolean,
      default: false,
    },
    customBtnWidth: {
      type: String,
      default: null,
    },
    btnColor: {
      type: String,
      default: null,
    },
    btnName: {
      type: String,
      default: null,
    },
    btnClass: {
      type: String,
      default: null,
    },
    isIcon: {
      type: Boolean,
      default: false,
    },
    iconName: {
      type: String,
      default: "",
    },
  },
};
</script>
