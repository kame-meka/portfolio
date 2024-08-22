<template>
<section class="background-holder">
  <v-container fluid fill-height class="background-holder__content">
    <v-row>
      <v-col class="background-holder__content-text">
        <p>{{title}}</p>
      </v-col>
    </v-row>
  </v-container>
</section>
</template>
<script>
export default {
  props: {
    title: {
      type: String,
      required: true
    }
  }
}
</script>
<style  lang="scss" scoped>
.background-holder__content {
  background: url("../assets/img/home-hero.jpg");
  background-size: cover;
  background-attachment: fixed;
  background-position: center center;
  width: 100%;
  height: 30vh;

  &-text {
    color: white;
    text-align: center;
    font-size: 28px;
    font-weight: bold;

    @include display_pc {
      font-size: 40px;
    }
  }
}
</style>