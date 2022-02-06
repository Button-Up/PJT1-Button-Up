<template>
  <div>
    <v-list
      subheader
      two-line
    >
      <v-subheader>대기 중인 요청</v-subheader>
      <!-- 환전 요청 -->
      <v-list-item
        v-for="(exchangeRequest, idx) in activeExchangeRequests"
        :key="idx"
      >
        <v-list-item-avatar>
          <v-icon
            class="parent01 lighten-1"
            dark
          >
            mdi-hand-coin
          </v-icon>
        </v-list-item-avatar>

        <v-list-item-content>
          <v-list-item-title v-text="exchangeRequest.childName"></v-list-item-title>
          <v-list-item-subtitle v-text="`${exchangeRequest.amount}원`"></v-list-item-subtitle>
        </v-list-item-content>

        <v-list-item-action>
          <v-list-item-action-text v-text="exchangeRequest.requestedTime"></v-list-item-action-text>
          <bottom-sheet
            :isIcon="true"
            iconName="mdi-arrow-right"
          ></bottom-sheet>
        </v-list-item-action>
      </v-list-item>

      <!-- 구매 요청 -->
      <v-list-item
        v-for="(buyRequest, idx) in activeBuyRequests"
        :key="idx"
      >
        <v-list-item-avatar>
          <v-icon
            class="parent02 lighten-1"
            color="black"
          >
            mdi-basket
          </v-icon>
        </v-list-item-avatar>

        <v-list-item-content>
          <v-list-item-title v-text="buyRequest.childName"></v-list-item-title>
          <v-list-item-subtitle v-text="buyRequest.productName"></v-list-item-subtitle>
        </v-list-item-content>

        <v-list-item-action>
          <v-list-item-action-text v-text="buyRequest.requestedTime"></v-list-item-action-text>
          <bottom-sheet
            :isIcon="true"
            iconName="mdi-arrow-right"
          ></bottom-sheet>
        </v-list-item-action>
      </v-list-item>

      <v-divider></v-divider>

      <v-subheader>처리 완료 요청</v-subheader>

      <!-- 환전 요청 -->
      <v-list-item
        v-for="(exchangeRequest, idx) in inactiveExchangeRequeests"
        :key="idx"
      >
        <v-list-item-avatar>
          <v-icon
            class="parent03 lighten-1"
            disabled
          >
            mdi-hand-coin
          </v-icon>
        </v-list-item-avatar>

        <v-list-item-content>
          <v-list-item-title v-text="exchangeRequest.childName"></v-list-item-title>
          <v-list-item-subtitle v-text="`${exchangeRequest.amount}원`"></v-list-item-subtitle>
        </v-list-item-content>

        <v-list-item-action>
          <v-list-item-action-text v-text="exchangeRequest.requestedTime"></v-list-item-action-text>
          <bottom-sheet
            :isIcon="true"
            iconName="mdi-information"
            btnColor="grey lighten-1"
          ></bottom-sheet>
        </v-list-item-action>
      </v-list-item>

      <!-- 구매 요청 -->
      <v-list-item
        v-for="(buyRequest, idx) in inactiveBuyRequests"
        :key="idx"
      >
        <v-list-item-avatar>
          <v-icon
            class="parent03 lighten-1"
            disabled
          >
            mdi-basket
          </v-icon>
        </v-list-item-avatar>

        <v-list-item-content>
          <v-list-item-title v-text="buyRequest.childName"></v-list-item-title>
          <v-list-item-subtitle v-text="buyRequest.productName"></v-list-item-subtitle>
        </v-list-item-content>

        <v-list-item-action>
          <v-list-item-action-text v-text="buyRequest.requestedTime"></v-list-item-action-text>
          <bottom-sheet
            :isIcon="true"
            iconName="mdi-information"
            btnColor="grey lighten-1"
          ></bottom-sheet>
        </v-list-item-action>
      </v-list-item>
    </v-list>
  </div>
</template>

<script>
  import BottomSheet from '@/components/common/BottomSheet'

  export default {
    name: 'ParentRequestList',
    components: {
      BottomSheet,
    },
    data() {
      return {
        sheetStatus: false,
        exchageRequests: [
          {
            childName: '황승연',
            amount: 5000,
            requestedTime: '22.02.05',
            isDone: true
          },
          {
            childName: '정은이',
            amount: 5000,
            requestedTime: '22.02.05',
            isDone: false
          },
          {
            childName: '김응철',
            amount: 5000,
            requestedTime: '22.02.05',
            isDone: false
          },
        ],
        buyRequests: [
          {
            childName: '우정연',
            productName: '맥북 프로 16인치',
            requestedTime: '22.02.06',
            isDone: false
          },
          {
            childName: '김지언',
            productName: '맥북 프로 14인치',
            requestedTime: '22.02.06',
            isDone: true
          }
        ],
      }
    },
    computed: {
      activeExchangeRequests() {
        return this.exchageRequests.filter(request => {
          return !request.isDone
        })
      },
      inactiveExchangeRequeests() {
        return this.exchageRequests.filter(request => {
          return request.isDone
        })
      },
      activeBuyRequests() {
        return this.buyRequests.filter(request => {
          return !request.isDone
        })
      },
      inactiveBuyRequests() {
        return this.buyRequests.filter(request => {
          return request.isDone
        })
      }
    }
  }
</script>

<style>

</style>