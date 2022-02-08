<template>
  <div>
    <h1>This is a Parent Activity page</h1>
  
      <v-btn 
        small 
        color="parent01" 
        class="white--text"
        @click="$router.push('/parent/activity/job')"
      >직업관리</v-btn>
      
  </div>
</template>

<script>
  export default {
    name: 'Activity',
  }
</script>
