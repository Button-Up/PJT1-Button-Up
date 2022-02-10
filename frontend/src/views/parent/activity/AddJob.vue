<!-- 
  author : 정은이
-->
<template>
  <div class="mx-9 mt-2">
    <!-- selectedImageIdx는 images list의 인덱스 -->
    <v-slide-group v-model="selectedImageIdx" class="pa-0 ma-0" center-active>
      <v-slide-item v-for="(img, index) in images" :key="index" v-slot="{ active, toggle }">
        <v-card class="ma-4" height="180" width="180" @click="toggle">
          <v-img class="fill-height" :src="img.imageUrl"> </v-img>

          <v-fade-transition>
            <v-overlay v-if="active" absolute color="parent01">
              <v-icon
                v-if="active"
                color="white"
                size="48"
                v-text="'mdi-checkbox-marked-circle'"
              ></v-icon>
            </v-overlay>
          </v-fade-transition>
        </v-card>
      </v-slide-item>
    </v-slide-group>
    <v-form>
      <!-- 직업 -->
      <v-list-item cols="12" class="justify-space-between align-baseline ma-0 pa-0">
        <v-text-field
          cols="8"
          v-model="newJob.name"
          hide-details
          label="직업명"
          required
          color="parent01"
        ></v-text-field>
      </v-list-item>
      <!-- 급여 -->
      <v-list-item cols="12" class="justify-space-between align-baseline ma-0 pa-0">
        <v-col class="ma-0 pa-0 flex-grow-1 flex-shrink-1">
          <v-text-field
            v-model="newJob.pay"
            label="급여"
            required
            hide-details
            color="parent01"
          ></v-text-field>
        </v-col>

        <v-col cols="5" class="ma-0 pa-0 align-center" align="end">
          <!-- 급여 지급 단위 -->
          <v-btn-toggle
            tile
            mandatory
            v-model="newJob.payTerm"
            color="ma-0 pa-0 parent01 align-center justify-end"
            class="ml-2"
          >
            <v-row dense class="ma-0 pa-0 justify-end" style="flex-wrap: nowrap">
              <v-col>
                <v-btn small value="주급" color="parent01" class="white--text">주급</v-btn>
              </v-col>
              <v-col>
                <v-btn small value="월급" color="parent01" class="white--text">월급</v-btn>
              </v-col>
            </v-row>
          </v-btn-toggle>
        </v-col>
      </v-list-item>

      <!-- 할일 추가 -->
      <v-list-item
        v-for="(todo, t) in newJob.toDoContents"
        :key="t"
        cols="12"
        class="justify-space-between align-baseline ma-0 pa-0"
      >
        <v-text-field
          :key="`inoput-${t}`"
          cols="8"
          v-model="newJob.toDoContents[t]"
          label="할일 추가"
          required
          color="parent01"
        ></v-text-field>
        <v-col cols="1" class="ml-2 pa-0">
          <v-btn icon color="parent01" @click="addTodo">
            <v-icon dark> mdi-plus </v-icon>
          </v-btn>
        </v-col>
        <v-col cols="1" class="ml-2 pa-0">
          <v-btn icon color="parent01" @click="removeTodo(t)">
            <v-icon dark> mdi-minus </v-icon>
          </v-btn>
        </v-col>
      </v-list-item>

      <!-- 직업 생성 submit -->
      <v-btn @click="clickAddJob" block color="parent01" class="white--text mt-4">직업 생성 </v-btn>
    </v-form>
  </div>
</template>

<script>
import { apiAddJob } from '@/api/jobsAPI.js';
export default {
  components: {},
  name: 'AddJob',
  data() {
    return {
      newJob: {
        jobImageSeq: 0,
        name: 'd',
        parentSeq: 5,
        pay: null,
        payTerm: null,
        toDoContents: [''],
      },
      passwordConfirm: null,
      images: [
        { no: 1, imageUrl: 'https://cdn.vuetifyjs.com/images/parallax/material.jpg' },
        { no: 2, imageUrl: 'https://picsum.photos/id/11/500/300' },
        { no: 3, imageUrl: 'https://cdn.vuetifyjs.com/images/parallax/material2.jpg' },
        { no: 4, imageUrl: 'https://picsum.photos/350/165?random' },
      ],
      selectedImageIdx: 0,
    };
  },
  methods: {
    addTodo() {
      if (this.newJob.toDoContents.length < 3) {
        this.newJob.toDoContents.push('');
      }
    },
    removeTodo(index) {
      if (this.newJob.toDoContents.length > 1) {
        this.newJob.toDoContents.splice(index, 1);
      }
    },
    clickAddJob() {
      var param = this.newJob;
      console.log(param);
      apiAddJob(
        param,
        (response) => {
          console.log(response.data);
          console.log('성공');
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
};
</script>

<style></style>
