<template>
  <section class="home-hero">
    <v-container fluid fill-height class="home-hero__content">
      <v-row>
        <div id="hero-head" class="head-margin"></div>
        <v-col class="home-hero__content-text sixtyfour-font fade-in-text">
          <div class="ababaaa">
            <div class="sentence">
              <div class="sentence-1">W</div>
              <div class="sentence-2">e</div>
              <div class="sentence-3">l</div>
              <div class="sentence-4">c</div>
              <div class="sentence-5">o</div>
              <div class="sentence-6">m</div>
              <div class="sentence-7">e</div>
              <div class="sentence-8"> </div>
              <div class="sentence-8"> </div>
              <div class="sentence-9">t</div>
              <div class="sentence-10">o</div>
            </div>
            <div class="sentence">
              <div class="sentence-5">K</div>
              <div class="sentence-6">a</div>
              <div class="sentence-7">m</div>
              <div class="sentence-8">e</div>
              <div class="sentence-8">y</div>
              <div class="sentence-10">a</div>
              <div class="sentence-11">m</div>
              <div class="sentence-12">a</div>
              <div class="sentence-13">'</div>
              <div class="sentence-14">s</div>
            </div>
            <div class="sentence">
              <div class="sentence-10">P</div>
              <div class="sentence-11">o</div>
              <div class="sentence-12">r</div>
              <div class="sentence-13">t</div>
              <div class="sentence-14">f</div>
              <div class="sentence-15">o</div>
              <div class="sentence-16">r</div>
              <div class="sentence-17">i</div>
              <div class="sentence-18">o</div>
            </div>
          </div>
        </v-col>
      </v-row>
    </v-container>
  </section>
</template>

<style lang="scss" scoped>
.home-hero__content {
  width: 100%;
  height: 80vh;

  @include display_pc {
    height: 100vh;
  }

  &-text {
    color: #e65f78;
    text-align: center;
    font-size: 32px;
    font-weight: bold;
    margin-top: 70%;

    @include display_pc {
      font-size: 40px;
      margin-top: 20%;
    }
  }
}

.sentence {
  @include display_sp {
    justify-content: center !important;
  }
}

.ababaaa {
  @include display_pc {
    margin-left: 15%;
  }
  @include display_sp {
    text-align: center;
  }
}

</style>