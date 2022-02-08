<template>
  <v-card class="rounded-0" :elevation="0">
    <v-toolbar color="parent02" class="font-weight-bold" :elevation="0"> 입금하기 </v-toolbar>
    <v-subheader>입금할 단추 개수를 입력해주세요.</v-subheader>
    <v-form class="mx-4">
      <v-text-field label="단추" suffix="개" color="parent01"></v-text-field>
    </v-form>
    <v-card-actions>
      <v-btn block color="parent01" outlined rounded text> 입금 </v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
export default {
  name: "Deposit",
  data() {
    return {
      sheetStatus: false,
      exchageRequests: [
        {
          childName: "황승연",
          amount: 5000,
          requestedTime: "22.02.05",
          status: "approved",
        },
        {
          childName: "정은이",
          amount: 5000,
          requestedTime: "22.02.05",
          status: "requested",
        },
        {
          childName: "김응철",
          amount: 5000,
          requestedTime: "22.02.05",
          status: "requested",
        },
      ],
      buyRequests: [
        {
          childName: "우정연",
          productName: "맥북 프로 16인치",
          requestedTime: "22.02.06",
          status: "requested",
        },
        {
          childName: "김지언",
          productName: "맥북 프로 14인치",
          requestedTime: "22.02.06",
          status: "rejected",
        },
      ],
    };
  },
};
</script>

<style></style>
