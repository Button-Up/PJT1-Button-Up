<template>
  <div>
    <v-list subheader two-line>
      <v-subheader>대기 중인 요청</v-subheader>
      <!-- 환전 요청 -->
      <v-list-item v-for="(exchangeRequest, idx) in activeExchangeRequests" :key="idx">
        <v-list-item-avatar>
          <v-icon class="parent01 lighten-1" dark> mdi-hand-coin </v-icon>
        </v-list-item-avatar>

        <v-list-item-content>
          <v-list-item-title v-text="exchangeRequest.childName"></v-list-item-title>
          <v-list-item-subtitle v-text="`${exchangeRequest.amount}원`"></v-list-item-subtitle>
        </v-list-item-content>

        <v-list-item-action>
          <v-list-item-action-text v-text="exchangeRequest.requestedTime"></v-list-item-action-text>
          <!-- 바텀 시트 버튼 -->
          <bottom-sheet sheetHeight="320px" :isIcon="true" iconName="mdi-arrow-right">
            <template v-slot:body>
              <v-card class="rounded-0" :elevation="0">
                <v-toolbar color="parent01" dark :elevation="0">
                  환전 요청 - {{ exchangeRequest.childName }}
                </v-toolbar>
                <v-subheader>{{ exchangeRequest.requestedTime }}</v-subheader>
                <div class="mx-4">
                  <div class="text-h4 font-weight-bold">{{ exchangeRequest.amount }}원</div>
                  <div class="text-subtitle-1 my-2">환전 방법</div>
                  <div class="text-body-2 mb-4">
                    <div>1. {{ exchangeRequest.amount }}원을 현금으로 지급해주세요.</div>
                    <div>2. '현금 지급 완료' 버튼을 눌러주세요.</div>
                    <div>3. 자녀의 단추 계좌에서 5000 단추가 차감됩니다.</div>
                  </div>
                  <v-row dense>
                    <v-col><v-btn block color="parent01" class="white--text">현금 지급 완료</v-btn></v-col>
                    <v-col><v-btn block color="parent03">환전 요청 거절</v-btn></v-col>
                  </v-row>
                </div>
              </v-card>
            </template>
          </bottom-sheet>
        </v-list-item-action>
      </v-list-item>

      <!-- 구매 요청 -->
      <v-list-item v-for="(buyRequest, idx) in activeBuyRequests" :key="idx">
        <v-list-item-avatar>
          <v-icon class="parent02 lighten-1" color="black"> mdi-basket </v-icon>
        </v-list-item-avatar>

        <v-list-item-content>
          <v-list-item-title v-text="buyRequest.childName"></v-list-item-title>
          <v-list-item-subtitle v-text="buyRequest.productName"></v-list-item-subtitle>
        </v-list-item-content>

        <v-list-item-action>
          <v-list-item-action-text v-text="buyRequest.requestedTime"></v-list-item-action-text>
          <bottom-sheet sheetHeight="260px" :isIcon="true" iconName="mdi-arrow-right">
            <template v-slot:body>
              <v-card class="rounded-0" :elevation="0">
                <v-toolbar color="parent02" class="font-weight-bold" :elevation="0">
                  구매 요청 - {{ buyRequest.childName }}
                </v-toolbar>
                <v-subheader>{{ buyRequest.requestedTime }}</v-subheader>
                <div class="mx-4">
                  <div class="text-h5 font-weight-bold">{{ buyRequest.productName }}</div>
                  <div class="text-body-2 my-4">
                    아이가 모아둔 단추로 <br />
                    {{ buyRequest.productName }}를 사고 싶어합니다.
                  </div>
                  <v-row dense>
                    <v-col><v-btn block color="parent01" class="white--text">결제하기</v-btn></v-col>
                    <v-col><v-btn block color="parent03">구매 요청 거절</v-btn></v-col>
                  </v-row>
                </div>
              </v-card>
            </template>
          </bottom-sheet>
        </v-list-item-action>
      </v-list-item>

      <v-divider></v-divider>

      <v-subheader>처리 완료 요청</v-subheader>

      <!-- 환전 요청 -->
      <v-list-item v-for="(exchangeRequest, idx) in inactiveExchangeRequeests" :key="idx">
        <v-list-item-avatar>
          <v-icon class="parent03 lighten-1" disabled> mdi-hand-coin </v-icon>
        </v-list-item-avatar>

        <v-list-item-content>
          <v-list-item-title v-text="exchangeRequest.childName"></v-list-item-title>
          <v-list-item-subtitle v-text="`${exchangeRequest.amount}원`"></v-list-item-subtitle>
        </v-list-item-content>

        <v-list-item-action>
          <v-list-item-action-text v-text="exchangeRequest.requestedTime"></v-list-item-action-text>
          <bottom-sheet sheetHeight="200px" :isIcon="true" iconName="mdi-information" btnColor="grey lighten-1">
            <template v-slot:body>
              <v-card class="rounded-0" :elevation="0">
                <v-toolbar color="parent01" dark :elevation="0">
                  환전 요청 - {{ exchangeRequest.childName }}
                </v-toolbar>
                <v-subheader>{{ exchangeRequest.requestedTime }}</v-subheader>
                <div class="mx-4">
                  <div class="text-h4 font-weight-bold">{{ exchangeRequest.amount }}원</div>
                  <div class="text-subtitle-1 my-2">
                    {{ exchangeRequest.status === "approved" ? "환전 완료" : "환전 요청 거절" }}
                  </div>
                </div>
              </v-card>
            </template>
          </bottom-sheet>
        </v-list-item-action>
      </v-list-item>

      <!-- 구매 요청 -->
      <v-list-item v-for="(buyRequest, idx) in inactiveBuyRequests" :key="idx">
        <v-list-item-avatar>
          <v-icon class="parent03 lighten-1" disabled> mdi-basket </v-icon>
        </v-list-item-avatar>

        <v-list-item-content>
          <v-list-item-title v-text="buyRequest.childName"></v-list-item-title>
          <v-list-item-subtitle v-text="buyRequest.productName"></v-list-item-subtitle>
        </v-list-item-content>

        <v-list-item-action>
          <v-list-item-action-text v-text="buyRequest.requestedTime"></v-list-item-action-text>
          <bottom-sheet :isIcon="true" iconName="mdi-information" btnColor="grey lighten-1">
            <template v-slot:body>
              <v-card class="rounded-0" :elevation="0">
                <v-toolbar color="parent02" class="font-weight-bold" :elevation="0">
                  환전 요청 - {{ buyRequest.childName }}
                </v-toolbar>
                <v-subheader>{{ buyRequest.requestedTime }}</v-subheader>
                <div class="mx-4">
                  <div class="text-h5 font-weight-bold">{{ buyRequest.productName }}</div>
                  <div class="text-subtitle-1 my-2">
                    {{ buyRequest.status === "approved" ? "구매 완료" : "구매 요청 거절" }}
                  </div>
                </div>
              </v-card>
            </template>
          </bottom-sheet>
        </v-list-item-action>
      </v-list-item>
    </v-list>
  </div>
</template>

<script>
import BottomSheet from "@/components/common/BottomSheet";
import // getRequest,
//getRequestList,
// addExchangeRequest,
// modifyRequestStatusApprove,
// modifyRequestStatusReject,
"@/api/requestAPI.js";

export default {
  name: "ParentRequestList",
  components: {
    BottomSheet,
  },
  data() {
    return {
      sheetStatus: false,
      requestList: [],
      // exchageRequests: [
      //   {
      //     childName: "황승연",
      //     amount: 5000,
      //     requestedTime: "22.02.05",
      //     status: "approved",
      //   },
      //   {
      //     childName: "정은이",
      //     amount: 5000,
      //     requestedTime: "22.02.05",
      //     status: "requested",
      //   },
      //   {
      //     childName: "김응철",
      //     amount: 5000,
      //     requestedTime: "22.02.05",
      //     status: "requested",
      //   },
      // ],
      // buyRequests: [
      //   {
      //     childName: "우정연",
      //     productName: "맥북 프로 16인치",
      //     requestedTime: "22.02.06",
      //     status: "requested",
      //   },
      //   {
      //     childName: "김지언",
      //     productName: "맥북 프로 14인치",
      //     requestedTime: "22.02.06",
      //     status: "rejected",
      //   },
      // ],
    };
  },
  created: {},
  computed: {
    activeExchangeRequests() {
      return this.exchageRequests.filter((request) => {
        return request.status === "requested";
      });
    },
    inactiveExchangeRequeests() {
      return this.exchageRequests.filter((request) => {
        return request.status !== "requested";
      });
    },
    activeBuyRequests() {
      return this.buyRequests.filter((request) => {
        return request.status === "requested";
      });
    },
    inactiveBuyRequests() {
      return this.buyRequests.filter((request) => {
        return request.status !== "requested";
      });
    },
  },
};
</script>

<style></style>
