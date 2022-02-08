<!-- 
  author : 정은이
-->
<template>
  <div class="mx-9 mt-10">
    <div class="mb-12"> 
      <v-row class="align-center justify-center">
        <v-avatar
      color="grey"
      size="200"
      ></v-avatar>
      </v-row>
      
    </div>
    <v-form>
      <!-- 직업 -->
      <v-list-item cols="12" class="justify-space-between align-baseline  ma-0 pa-0">
        <v-text-field cols="8"
          v-model="newJob.name"
          hide-details
          label="직업명"
          required 
          color="parent01"
        ></v-text-field>
      </v-list-item>

      <!-- 급여 -->
      <v-list-item cols="12" class="justify-space-between align-baseline ma-0 pa-0">
        <v-text-field cols="8"
          v-model="newJob.salary"
          label="급여"
          required hide-details
          color="parent01"
        ></v-text-field>
        <!-- 급여 지급 단위 --> 
        <v-btn-toggle  tile mandatory v-model="newJob.termSalary"  color="parent01" class="align-center ml-2">
          <v-row dense>
            <v-col>
              <v-btn small value="주급" color="parent01" class="white--text">주급</v-btn>
            </v-col>
            <v-col>
              <v-btn small value="월급" color="parent01" class="white--text">월급</v-btn>
            </v-col>
          </v-row>
        </v-btn-toggle>
      </v-list-item>


      <!-- 할일 추가 -->
      <v-list-item cols="12" class="justify-space-between align-baseline  ma-0 pa-0">
        <!-- <v-col cols="4 ma-0 pa-0">
          <v-list-item class="font-weight-bold text-title">할일 추가</v-list-item>
        </v-col> -->
        <v-text-field cols="8"
          v-model="newJob.todoLists"
          label="할일 추가"
          required
          color="parent01"
        ></v-text-field>
      </v-list-item>

      <!-- 직업 생성 submit -->
      <v-btn
        block
        color="parent01"
        class="white--text mt-4"
      >직업 생성 </v-btn>
    </v-form>

  </div>
</template>

<script>
export default {
  components: { },
  name: 'AddJob',
  data() {
    return {
      newJob: {
        name: null,
        salary: null,
        termSalary:null,
        todoLists:[]          
      },
      passwordConfirm: null
    }
  },
}
</script>

<style>

</style>