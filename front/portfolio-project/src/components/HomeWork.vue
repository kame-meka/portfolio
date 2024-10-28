<template>
  <section class="home-service">
    <div id="works-head" class="head-margin"></div>
    <div class="section__title">
      <div class="section__title-text sixtyfour-font fadein fadein-left">Works</div>
    </div>
    <v-container>
      <div class="home-service__contents">
        <v-col md="3" class="fadein fadein-up">
          <v-card @click="portfolioDialog = true" hover class="card-frame float">
            <div class="home-service__card-title">
              <p class="mochiy-pop-one-regular">ポートフォリオ</p>
            </div>
            <div class="home-service__card-img">
              <img class="icon--service" :src="require('@/assets/img/profile.png')" alt="">
            </div>
            <div class="home-service__card-text">
              <p>このWebサイトです。</p>
            </div>
          </v-card>
        </v-col>
        <v-col md="3" class="fadein fadein-down">
          <v-card @click="catalogDialog = true" hover class="card-frame float">
            <div class="home-service__card-title">
              <p class="mochiy-pop-one-regular">図鑑登録サービス</p>
            </div>
            <div class="home-service__card-img">
              <img class="icon--service" :src="require('@/assets/img/hari.png')" alt="">
            </div>
            <div class="home-service__card-text">
              <p>オリジナルの図鑑を作成できるサービスです。</p>
            </div>
          </v-card>
        </v-col>

        <!-- ポートフォリオダイアログ -->
        <v-dialog v-model="portfolioDialog" width="auto">
          <v-card class="dialogue-frame m-plus-rounded-1c-regular" max-width="100%">
            <v-card-title class="card-title">ポートフォリオ</v-card-title>
            <v-card-text>
              <p class="section-title">【概要】</p>
              <p>自己紹介用のページです。</p>
              <p>Vue.jsでコーディングを行い、AWSのEC2インスタンス上のDockerコンテナで動かしています。</p>
              <a href="https://github.com/kame-meka/portfolio/tree/main/front/portfolio-project" target="_blank">GitHubリポジトリを開く</a>
              <div class="port-img-frame">
                <img :src="require('@/assets/img/main-title.png')" alt="" class="port-img">
              </div>
              <br>
              <p>下記は初期案としてFigmaのFigJamツールでデザインを大まかに描いたもので、</p>
              <p>見やすさや実装のしやすさを追求する中で現在のデザインへと変遷していきました。</p>
              <div class="port-img-frame">
                <img :src="require('@/assets/img/figjam.png')" alt="" class="port-img">
              </div>
              <br>
              <p class="section-title">【今後の改善項目】</p>
              <p>・HTTPS通信に対応させる</p>
              <p>・画像拡大機能の追加</p>
            </v-card-text>
            <v-card-actions>
              <v-btn class="ms-auto button-frame" text @click="portfolioDialog = false">閉じる</v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>

        <!-- 図鑑登録サービスダイアログ -->
        <v-dialog v-model="catalogDialog" width="auto">
          <v-card class="dialogue-frame m-plus-rounded-1c-regular" max-width="100%">
            <v-card-title class="card-title">図鑑登録サービス</v-card-title>
            <v-card-text>
              <p class="section-title">【概要】</p>
              <p>ポートフォリオ用に作成した、オリジナルの図鑑を作成できるサービスです。</p>
              <p>画像、タイトル、説明文、タグ情報を登録でき、キーワード検索・タグ検索機能で絞り込むことが可能です。</p>
              <a href="/login" target="_blank">別タブでページを開く</a>
              <div class="port-img-frame">
                <video class="port-img" controls>
                  <source :src="require('../assets/movie/howtouse.mp4')" type="video/mp4">
                  Your browser does not support the video tag.
                </video>
              </div>
              <br>
              <p class="section-title">【環境】</p>
              <p>EC2インスタンスで下記のDockerコンテナを立ち上げています。</p>
              <p>●フロントエンド：Vue.js（nginx）</p>
              <p class="sub-explain">→登録や検索をスムーズに行えるようSPAで構築</p>
              <p>●バックエンド：Kotlin + Spring Boot</p>
              <p class="sub-explain">→テスト駆動開発を実践し、コーディング前の段階で各種テストコードを記述・実行</p>
              <p class="sub-explain">→ドメイン駆動設計・クリーンアーキテクチャの思想に則って設計</p>
              <p>●データベース：MySQL</p>
              <p>●ファイルストレージ：MinIO</p>
              <div class="port-img-frame">
                <img :src="require('@/assets/img/architecture.png')" alt="" class="port-img">
              </div>
              <p></p>
              <br>
              <p class="section-title">【テスト】</p>
              <p>バックエンドの単体テスト、APIテスト、E2Eテストを実装しています。</p>
              <p>単体テストはKotlinのJUnitで記述し、APIテスト・E2Eテストはgaugeとselenideを組み合わせて記述しています。</p>
              <p>●gauge：日本語とテストコードを紐づけて書けるようにするフレームワーク</p>
              <p>●selenide：ブラウザ操作処理をコーディング可能にするフレームワーク</p>
              <div class="port-img-frame">
                <img :src="require('@/assets/img/testcode.png')" alt="" class="port-img">
              </div>
              <br>
              <p class="section-title">【今後の改善項目】</p>
              <p>・アカウント登録機能の追加</p>
              <p>・ECSを利用してコンテナ管理を行うように修正</p>
              <p>・ビルドからデプロイまでを自動化するCI/CDパイプラインの作成</p>
              <p>・検索手法をDB検索から全文検索エンジンElasticSearchを活用したものへ変更</p>
            </v-card-text>
            <v-card-actions>
              <v-btn class="ms-auto button-frame" text @click="catalogDialog = false">閉じる</v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
      </div>
    </v-container>
  </section>
</template>


<script>

export default {
  components: {

  },
  data() {
    return {
      portfolioDialog: false,
      catalogDialog: false,
    }
  }
}
</script>

<style lang="scss" scoped>
.home-service__contents {
  margin-top: 20px;
  justify-content: center;

  @include display_pc {
    display: flex;
  }
}

.card-frame.float:hover {
  cursor: pointer;
  transition: .2s cubic-bezier(0.45, 0, 0.55, 1);
  transform: translateY(-5px);
}

.dialogue-frame {
  padding: 16px;
}

.home-service__card-title {
  text-align: center;
  padding: 20px 10px 10px;
}

.home-service__card-img {
  text-align: center;
  padding: 0 8px;
}

.home-service__card-text {
  padding: 10px 20px;
  height: 75px;
}

.icon--service {
  width: 100%;
  height: 100%;
  max-height: 200px;
}

.v-window {
  height: 300px !important;

  @include display_pc {
    height: 500px !important;
  }
}

.card-title {
  font-weight: bold;
  padding: 8px;
  border-bottom: 1px solid;
}

.section-title {
  font-weight: bold;
}

.port-img-frame {
  padding: 8px 0px;
  display: flex;
  justify-content: center;
}

.port-img {
  max-height: 300px;
  max-width: 300px;
  border-radius: 5px;
  border: 2px solid lightGray;

  @include display_pc {
    max-width: 1000px !important;
  }
}

.button-frame {
  border: 2px solid lightGray;
}

.sub-explain {
  padding: 0px 16px;
}
</style>