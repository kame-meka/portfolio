<template>
  <section class="home-work">
    <div class="section__title">
      <div class="section__title-text">02. My Work</div>
    </div>
    <!-- ここから追加 -->
    <v-container>
      <v-row class="home-work__contents">
        <v-col sm="8" class="home-work__contents-img">
          <v-carousel
            hide-delimiters
            height="auto"
          >
            <v-carousel-item
              v-for="(workItem, index) in workItems"
              :key="index"
              :to="workItem.url"
            >
              <img :src="workItem.src" alt="" class="img-fluid">
            </v-carousel-item>
          </v-carousel>
        </v-col>
      </v-row>
    </v-container>
    <!-- ここまで追加 -->
  </section>
</template>
<script>
import constants from '../common/constants'

export default {
  data () {
    return {
      workItems: constants.workItems
    }
  }
}
</script>
<style  lang="scss" scoped>
/* ここから追加 */
.home-work__contents {
  margin-top: 20px;
  justify-content: center;
}

.home-work__contents-img {
  text-align: center;
}
/* ここまで追加 */
</style>