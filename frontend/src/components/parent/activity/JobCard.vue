<template>
  <div>
    <v-card v-if="customWidth"
      class="mx-auto ma-2"
      :max-width=customWidth
      :max-height=customHeight
    >
      <div>
        <v-img
        class="white--text align-end"
        src="https://cdn.vuetifyjs.com/images/cards/docks.jpg"
        >
        </v-img>
      </div>
      
      <v-card-title class="align-center justify-center">{{title}}</v-card-title>  
    </v-card>
  </div>
</template>

<script>
export default {
  name: 'JobCard',
  props:{
    customWidth:{
      type:Number,
      default:300
      },
    customHeight:{
      type:Number,
      default:300
    },
    title:{
      type:String,
      default: "직업"
    }
  }
}
</script>

<style>

</style>