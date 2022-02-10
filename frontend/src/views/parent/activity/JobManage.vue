<!--
  author : 정은이
-->
<template>
  <div class="mx-6 mt-6">
    <!-- 직업 종류 관리 START -->
    <div>
      <h2>직업 종류 관리</h2>
      <p class="caption font-weight-normal">
        직업을 추가하거나, 기존 직업의 역할, 급여를 수정할 수 있어요.
      </p>
    </div>
    <v-sheet class="mx-auto" elevation="" max-width="1200">
      <v-slide-group class="pa-1" center-active>
        <!--
            아이 정보 카드 클릭 시 activity로 이동하게 되어있습니다.
            추후 아이 상세 페이지로 이동하도록 수정 필요
          -->
        <v-slide-item justify-center v-for="(job, j) in jobs" :key="j">
          <JobWithTodoListCard
            :isParent="true"
            :job="job"
            :checkboxOn="false"
          ></JobWithTodoListCard>
        </v-slide-item>
      </v-slide-group>
    </v-sheet>

    <v-btn :to="{ name: 'AddJob' }" block color="parent01" class="white--text"
      >직업 추가하기
    </v-btn>

    <!-- 아이 직업 설정하기 END -->
    <div class="mt-8">
      <h2>아이 직업 설정하기</h2>
      <p class="caption font-weight-normal">위에서 생성, 수정한 직업을 아이에게 부여해주세요.</p>
    </div>
    <div></div>
    <div>
      <v-divider></v-divider>
      <template v-for="(child, c) in childrenInfo" class="ma-0 pa-0">
        <v-list-item v-bind:key="c" class="justify-space-between align-baseline" cols="12">
          <v-col cols="3">
            <v-list-item-title cols="3" v-html="child.name"></v-list-item-title>
          </v-col>

          <v-select
            cols="5"
            class="ma-0 pa-0"
            v-model="child.job"
            :items="jobs"
            item-value="seq"
            item-text="name"
            :color="`parent01`"
            :item-color="`parent01`"
            @change="selectJob"
            outlined
            solo
            align-center
            single-line
            disable-lookup
            dense
            return-object
            ref="select"
          ></v-select>

          <v-btn @click="setChildJob(child)" cols="4" color="parent01" class="ml-4 white--text"
            >설정</v-btn
          >
        </v-list-item>
      </template>
      <v-divider></v-divider>
    </div>
  </div>
</template>

<script>
import JobWithTodoListCard from '@/components/common/JobWithTodoListCard.vue';
import { apiGetJobsList, apiModifyJob } from '@/api/jobsAPI.js';
import { mapGetters } from 'vuex';

export default {
  components: {
    JobWithTodoListCard,
  },
  data() {
    return {
      children: [
        {
          name: '김응철',
          job: { name: '직업 설정', seq: null },
        },
        {
          name: '우정연',
          job: { name: '직업 설정', seq: null },
        },
        {
          name: '유현수',
          job: { name: '직업 설정', seq: null },
        },
        {
          name: '황승연',
          job: { name: '직업 설정', seq: null },
        },
        {
          name: '김지언',
          job: { name: '직업 설정', seq: null },
        },
      ],
      jobs: [
        {
          name: '청소부',
          jobImagePath: 'https://cdn.vuetifyjs.com/images/john.jpg',
          payTerm: '주급',
          pay: 3000,
          toDos: [
            {
              content: '방 정리 하기',
            },
            {
              content: '먼지 털기',
            },
            {
              content: '청소기 돌리기',
            },
          ],
        },
        {
          name: '환경미화원',
          jobImagePath: 'https://cdn.vuetifyjs.com/images/john.jpg',
          pay: 3000,
          payTerm: '주급',
          toDos: [
            {
              content: '고양이 화장실 치우기',
            },
            {
              content: '음식물 쓰레기 버리기',
            },
            {
              content: '분리수거 하기',
            },
          ],
        },
        {
          name: '기상청장',
          jobImagePath: 'https://cdn.vuetifyjs.com/images/john.jpg',
          pay: 3000,
          payTerm: '주급',
          toDos: [
            {
              content: '환기 시키기',
            },
            {
              content: '날씨 알려주기',
            },
          ],
        },
      ],
    };
  },
  created() {
    apiGetJobsList(
      this.checkUserInfo.seq,
      (response) => {
        console.log(response.data);
        this.jobs = response.data;
      },
      (error) => {
        console.log(error);
      }
    );
  },
  computed: {
    ...mapGetters('parentStore', ['childrenInfo']),
    ...mapGetters('userStore', ['checkUserInfo']),
  },
  methods: {
    selectJob(event) {
      console.log(event.name);
    },
    setChildJob(child) {
      console.log('childreninfo');
      console.log(child);
      console.log(child.job.name);
      var param = {
        childSeq: child.seq,
        jobSeq: child.job.seq,
      };
      apiModifyJob(
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
