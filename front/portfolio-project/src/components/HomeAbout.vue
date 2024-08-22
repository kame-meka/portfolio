<template>
  <section class="home-about">
    <div id="about-head" class="head-margin"></div>
    <div class="section__title">
      <div class="section__title-text sixtyfour-font fadein fadein-left">Profile</div>
    </div>
    <v-container>
      <div class="home-about__contents">
        <div class="home-about__contents-img fadein fadein-down">
          <img :src="require('@/assets/img/prof-1.jpg')" alt="" class="img-fluid">
        </div>
        <div class="home-about__contents-text">
          <div class="profile-content fadein fadein-left">
            <p class="sixtyfour-font profile-title">Name</p>
            <div class="profile-comment">
              <p class="mochiy-pop-one-regular font-white">亀山 航士（かめやま こうし）</p>
            </div>
          </div>
          <div class="profile-content fadein fadein-right">
            <p class="sixtyfour-font profile-title">Age</p>
            <div class="profile-comment">
              <p class="mochiy-pop-one-regular font-white">30（1994/07/31 生まれ）</p>
            </div>
          </div>
          <div class="profile-content fadein fadein-left">
            <p class="sixtyfour-font profile-title">Summary</p>
            <div class="profile-comment">
              <p class="mochiy-pop-one-regular font-white">IT業界歴6年目のシステムエンジニアです。</p>
              <br>
            </div>
            <div class="profile-comment">
              <p class="mochiy-pop-one-regular font-white">新卒入社した会社では主にデスクトップアプリの保守に従事し、設計からコーディング、テストまでを担当しておりました。</p>
              <p class="mochiy-pop-one-regular font-white">転職後はアジャイル開発の現場でWebアプリ開発に従事し、顧客との要件定義から設計・コーディング・テスト・運用までの全工程を担当いたしました。</p>
            </div>
          </div>
          <div class="profile-content fadein fadein-right">
            <p class="sixtyfour-font profile-title">Hobbies</p>
            <div class="profile-comment">
              <p class="mochiy-pop-one-regular font-white">★ プロ野球観戦</p>
              <p class="mochiy-pop-one-regular font-white">☆ ゲーム全般（アナログ・デジタル）</p>
              <p class="mochiy-pop-one-regular font-white">★ カラオケ</p>
            </div>
          </div>
        </div>
      </div>
    </v-container>
  </section>
</template>

<script>
export default {
  data () {
    return {
      buttonText: '詳しくみる',
      url: '/about'
    }
  }
}
</script>

<style lang="scss" scoped>
.section__title {
  padding-top: 120px;
  text-align: center;

}

.profile-content {
  padding: 8px;
}
.profile-title {
  color: #e65f78;
  font-size: 24px !important;
}
.profile-comment {
  padding: 0 0 0 16px;
}

.font-white {
  color: white;
}

.home-about__contents {
  margin-top: 20px;
  justify-content: center;
  gap: 32px;

  @include display_pc {
    display: flex;
  }

  img {
    box-shadow: 20px 20px 0 cadetblue;
    max-height: 500px;
  }

  &-text {
    word-wrap: break-word;
    border-radius: 16px;
    margin-bottom: 15px;

    @include display_pc {
      width: 40%;
      max-height: 500px;
    }
  }
}
.home-about__contents-img {
  padding: 16px;
}
.home-about__contents-text {
  padding: 16px;
}

</style>