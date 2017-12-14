<template>
  <div class="app-container">
    <section class="router-view-box" :style="{ height: screenHeight, width: screenWidth }">
            <router-view class="router-view"></router-view>
    </section>
  </div>
</template>
<script>
export default {
  computed: {
    screenHeight () {
      return window.innerHeight
    },
    screenWidth () {
      return window.innerWidth
    }
  }
}
</script>

<style scoped>
.app-container{
background-color: #f5f5f5;
    display: flex;
    justify-content:center;
}
.router-view-box{
  width: 100%;
}
</style>
