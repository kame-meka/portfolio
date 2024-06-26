<template>
  <section class="home">
    <HomeHero/>
    <HomeAbout/>
    <HomeWork/>
    <HomeService/>
  </section>
</template>
<script>
import HomeHero from './HomeHero.vue'
import HomeAbout from './HomeAbout.vue'
import HomeWork from './HomeWork.vue'
import HomeService from './HomeService.vue'

export default {
  components: {
    HomeHero,
    HomeAbout,
    HomeWork,
    HomeService
  }
}
</script>
<style lang="scss" scoped>
.home {
  padding-bottom: 0px;
}
</style>