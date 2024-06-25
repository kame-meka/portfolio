const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
 devServer: {
   host: 'localhost',
   port: 8888
 },
// transpileDependencies: [
//   'vuetify'
// ],
 css: {
   loaderOptions: {
     scss: {
       prependData: '@import "./src/styles/common/common.scss";'
     }
   }
 }

   pluginOptions: {
     vuetify: {
 			// https://github.com/vuetifyjs/vuetify-loader/tree/next/packages/vuetify-loader
 		}
   }
})

//module.exports = defineConfig({
//  devServer: {
//    host: 'localhost',
//    port: 8888
//  },
//
//  transpileDependencies: true,
//
//  pluginOptions: {
//    vuetify: {
//			// https://github.com/vuetifyjs/vuetify-loader/tree/next/packages/vuetify-loader
//		}
//  }
//})