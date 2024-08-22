<template>
  <section class="home-hero">
    <v-container fluid fill-height class="home-Blog__content">
      <HomeHero/>
      <HomeAbout/>
      <HomeSkills/>
      <HomeWork/>
    </v-container>
  </section>
</template>

<script>
import HomeHero from './HomeHero.vue'
import HomeAbout from './HomeAbout.vue'
import HomeSkills from './HomeSkills.vue'
import HomeWork from './HomeWork.vue'

export default {
  components: {
    HomeHero,
    HomeAbout,
    HomeSkills,
    HomeWork
  }
}
</script>

<style lang="scss" scoped>
.home-Blog__content {
  background: url("../assets/img/home-hero.jpg");
  background-size: cover;
  background-attachment: fixed;
  background-position: center center;
  width: 100%;
}
</style>