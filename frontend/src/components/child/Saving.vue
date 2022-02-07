<template>
  <div>
    <!-- 단추 잔액 & 환전 요청 버튼 -->
    <v-sheet
      color="child01"
      width="100%"
      height="180"
      class="d-flex justify-center align-center"
    >
      <div>
        <h2>{{ balance }} 단추</h2>
        <bottom-sheet
          btnColor="child02"
          btnName="환전 요청하기"
          btnClass="font-weight-bold mt-3"
        ></bottom-sheet>
      </div>
    </v-sheet>
    <!-- 필터 -->
    <v-row justify="end">
      <v-col cols="4">
        <v-select
          color="child01"
          item-color="child01"
          v-model="filterSelect"
          :items="historyFilter"
          class="mr-4"
        ></v-select>
      </v-col>
    </v-row>
    <!-- 거래내역 리스트 -->
    <v-list two-line>
      <v-divider></v-divider>
      <template
        v-for="(accountHistory, idx) in accountHistories"
      >
        <v-list-item :key="`item-${idx}`">
          <v-list-item-content>
            <v-list-item-title>title</v-list-item-title>
            <v-list-item-subtitle>subtitle</v-list-item-subtitle>
          </v-list-item-content>
        </v-list-item>
        <v-divider
          v-if="idx < accountHistories.length"
          :key="`divider-${idx}`"
        ></v-divider>
      </template>
    </v-list>
  </div>
</template>

<script>
import BottomSheet from "../../components/common/BottomSheet.vue";

export default {
  name: "ChildSaving",
  components: {
    BottomSheet,
  },
  data() {
    return {
      balance: 25000,
      filterSelect: '전체',
      historyFilter: [ '전체', '입금', '출금' ],
      accountHistories: [
        {
          action: "15 min",
          headline: "Brunch this weekend?",
          subtitle: `I'll be in your neighborhood doing errands this weekend. Do you want to hang out?`,
          title: "Ali Connors",
        },
        {
          action: "2 hr",
          headline: "Summer BBQ",
          subtitle: `Wish I could come, but I'm out of town this weekend.`,
          title: "me, Scrott, Jennifer",
        },
        {
          action: "6 hr",
          headline: "Oui oui",
          subtitle: "Do you have Paris recommendations? Have you ever been?",
          title: "Sandra Adams",
        },
        {
          action: "12 hr",
          headline: "Birthday gift",
          subtitle:
            "Have any ideas about what we should get Heidi for her birthday?",
          title: "Trevor Hansen",
        },
        {
          action: "18hr",
          headline: "Recipe to try",
          subtitle:
            "We should eat this: Grate, Squash, Corn, and tomatillo Tacos.",
          title: "Britta Holt",
        },
        {
          action: "18hr",
          headline: "Recipe to try",
          subtitle:
            "We should eat this: Grate, Squash, Corn, and tomatillo Tacos.",
          title: "Britta Holt",
        },
        {
          action: "18hr",
          headline: "Recipe to try",
          subtitle:
            "We should eat this: Grate, Squash, Corn, and tomatillo Tacos.",
          title: "Britta Holt",
        },
        {
          action: "18hr",
          headline: "Recipe to try",
          subtitle:
            "We should eat this: Grate, Squash, Corn, and tomatillo Tacos.",
          title: "Britta Holt",
        },
        {
          action: "18hr",
          headline: "Recipe to try",
          subtitle:
            "We should eat this: Grate, Squash, Corn, and tomatillo Tacos.",
          title: "Britta Holt",
        },
        {
          action: "18hr",
          headline: "Recipe to try",
          subtitle:
            "We should eat this: Grate, Squash, Corn, and tomatillo Tacos.",
          title: "Britta Holt",
        },
        {
          action: "18hr",
          headline: "Recipe to try",
          subtitle:
            "We should eat this: Grate, Squash, Corn, and tomatillo Tacos.",
          title: "Britta Holt",
        },
        {
          action: "18hr",
          headline: "Recipe to try",
          subtitle:
            "We should eat this: Grate, Squash, Corn, and tomatillo Tacos.",
          title: "Britta Holt",
        },
      ],
    }
  },
};
</script>

<style>

</style>
