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
          <td v-for="item in desserts" :key="item.name" class="text-center">{{ item }}</td>
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
      desserts: [11, 22, 33, 44, 55],
    };
  },
};
</script>

<style scope>
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
