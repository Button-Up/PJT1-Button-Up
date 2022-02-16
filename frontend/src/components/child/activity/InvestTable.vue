<!--
  author : 우정연
-->
<template>
  <v-simple-table align="center">
    <template v-slot:default>
      <thead>
        <tr>
          <th
            style="background-color: #fcf1e2"
            v-for="(item, idx) in headers"
            :key="idx"
            class="text-center px-0"
          >
            {{ item }}
          </th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td class="text-center">{{ nowPrice }}</td>
          <td class="text-center">{{ count }}</td>
          <td class="text-center">{{ avaragePrice }}</td>
          <td class="text-center">{{ profit }}</td>
          <td class="text-center">{{ Math.round(profitRate) }}%</td>
        </tr>
      </tbody>
    </template>
  </v-simple-table>
</template>

<script>
export default {
  data() {
    return {
      headers: ["현재 가격", "보유 주식 수", "평균 가격", "손익", "수익률"],
    };
  },
  computed: {
    profit() {
      return this.nowPrice * this.count - this.avaragePrice * this.count;
    },
    profitRate() {
      if (this.count == 0) return 0;
      else
        return (
          ((this.nowPrice * this.count - this.avaragePrice * this.count) * 100) /
          (this.avaragePrice * this.count)
        );
    },
  },
  props: {
    nowPrice: {
      type: Number,
      default: 0,
    },
    count: {
      type: Number,
      default: 0,
    },
    avaragePrice: {
      type: Number,
      default: 0,
    },
  },
};
</script>

<style scoped>
table {
  width: 100%;
  border: 1px solid #c4c4c4;
  border-collapse: collapse;
}
th,
td {
  border: 1px solid #c4c4c4;
}
</style>
