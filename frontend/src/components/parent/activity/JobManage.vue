<template>
  <div class="mx-6 mt-6">
    <!-- 직업 종류 관리 START -->
    <div>
      <h2>직업 종류 관리</h2>
      <p class="caption font-weight-normal">직업을 추가하거나, 기존 직업의 역할, 급여를 수정할 수 있어요.</p>
    </div>
    
    <!-- 아이 직업 설정하기 END -->
    <div>
      <h2>아이 직업 설정하기</h2>
      <p class="caption font-weight-normal">위에서 생성, 수정한 직업을 아이에게 부여해주세요.</p>
    </div> 
    <div>
      <v-divider></v-divider>
      <template v-for="(item, index) in items" class="ma-0 pa-0">
          <v-list-item v-bind:key="index" class="justify-space-between align-baseline" cols="12" >

            <v-col cols="3">
              <v-list-item-title cols="3" v-html="item.name"></v-list-item-title>
            </v-col>
            
            <v-select cols="5" class="ma-0 pa-0" v-model="item.job"
              :items="jobs" item-value="name" item-text="name"
              :color="`parent01`" :item-color="`parent01`" @change="selectJob"
              outlined solo align-center single-line disable-lookup dense return-object ref='select'
            ></v-select>
            
            <v-btn cols="4" color="parent01" class="ml-4 white--text">설정</v-btn>
          </v-list-item>
        </template>
        <v-divider></v-divider>
    </div>    
  </div>
</template>

<script>

export default {
  components: { 
  },
  data() {
    return {
      items: [
      {
        name: '김응철',
        job:  { name: '직업 설정', type: 'FL' },
      },
      {
        name: '우정연',
        job: ``,
      },
      {
        name: '유현수',
        job: ``,
      },
      {
        name: '황승연',
        job: ``,
      },
      {
        name: '김지언',
        job: ``,
      },
      ],
      jobs:[
        { name: '직업 설정', type: 'FL' },
        { name: '환경미화원', type: 'GA' },
        { name: '세탁소', type: 'NE' },
        { name: '기상청장', type: 'CA' },
      ]
    }
  },
  methods:{
    selectJob(event){
      console.log(event.name);
    }
  }
}
</script>

<style>

</style>