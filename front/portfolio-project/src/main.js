import { createApp } from 'vue'
import App from './App.vue'
import vuetify from './plugins/vuetify'
import { loadFonts } from './plugins/webfontloader'
import router from './router'
import vueSmoothScroll from 'vue-smooth-scroll'

loadFonts()

createApp(App)
  .use(router)
  .use(vuetify)
  .use(vueSmoothScroll)
  .mount('#app');