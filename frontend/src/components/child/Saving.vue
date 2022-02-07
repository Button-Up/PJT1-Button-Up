<template>
  <div>
    <div>
      <div>25,000단추</div>
      <bottom-sheet :isIcon="false" btnColor="child02" btnName="환전 요청하기">
        <template v-slot:body>
          <h1>환전 요청</h1>
          <p>환전 요청할 금액을 입력해 주세요</p>
          <input type="number" name="exchange" id="exchange" />단추
          <v-icon>mdi-arrow-right</v-icon>원
        </template>
        <v-btn color="child02"> 환전 요청하기 </v-btn>
      </bottom-sheet>
    </div>
    <div>
      <v-col class="d-flex" cols="3" sm="6">
        <v-select :items="select" label="입출금"></v-select>
      </v-col>
    </div>

    <v-list two-line>
      <v-list-item-group multiple>
        <template v-for="(accountHistory, index) in accountHistories">
          <money-list :key="`accountHistory-${index}`"> </money-list>

          <v-divider
            v-if="index < accountHistories.length - 1"
            :key="`divider-${index}`"
          ></v-divider>
        </template>
      </v-list-item-group>
    </v-list>
  </div>
</template>

<script>
import BottomSheet from "../../components/common/BottomSheet.vue";
import MoneyList from "@/components/child/MoneyList.vue";
export default {
  name: "ChildSaving",
  components: {
    BottomSheet,
    MoneyList,
  },
  data: () => ({
    select: ["전체", "입금", "출금"],
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
    ],
  }),
};
</script>

<style>
#exchange {
  background-color: #fcf1e2;
  border: 1px solid;
  border-radius: 3px;
  border-color: #fdd26c;
}
</style>
