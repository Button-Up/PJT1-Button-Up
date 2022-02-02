<template>
  <div>
    <div class="part1">
      <div class="content text-center white--text">
        <h1>우리 아이 경제 교육 <span class="yellow--text">첫단추</span> <br> 제대로 끼우기!</h1>
        <p class="mt-3">첫단추로 기르는 금융 리터러시 <br> 용돈 관리부터 투자 교육까지, 첫단추로 해결해보세요.</p>
      
      <div class="signup-container">
        <v-btn elevation="0" block class="signup-btn__parent font-weight-bold">부모님 회원 시작하기</v-btn>
        <v-btn elevation="0" block outlined color="white" class="mt-2 font-weight-bold">자녀 회원 시작하기</v-btn>
      </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Index',
}
</script>

<style lang="scss" scoped>
  .part1 {
    position: relative;
    background-color: #3DBF69;
    width: 100%;
    height: 800px;
    .content {
      display: block;
      justify-content: center;
      padding: 140px 15px 176px;
    }
    .signup-container {
      margin-top: 90px;
      .signup-btn__parent {
        color: #3DBF69;
      }
    }
  }
</style>