const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,

  pluginOptions: {
    vuetify: {
        // https://github.com/vuetifyjs/vuetify-loader/tree/next/packages/vuetify-loader
    }
  },
  transpileDependencies: [
    'vuetify'
  ],

  css: {
      loaderOptions: {
        scss: {
          additionalData: '@import "./src/styles/common/common.scss";'
        }
      }
  }
})
