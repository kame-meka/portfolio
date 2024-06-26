//import Vue from 'vue'
//import Router from 'vue-router'
import HomeComponent from './components/HomeComponent.vue'
import AboutComponent from './components/AboutComponent.vue'
import WorkComponent from './components/WorkComponent.vue'
import ServiceComponent from './components/ServiceComponent.vue'
import { createRouter, createWebHistory } from 'vue-router'
//Vue.use(Router)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: HomeComponent
  },
  {
    path: '/about',
    name: 'About',
    component: AboutComponent
  },
  {
    path: '/work',
    name: 'work',
    component: WorkComponent
  },
  {
    path: '/service',
    name: 'service',
    component: ServiceComponent
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router

//export default new Router({
//  routes: [
//    {
//      path: '/',
//      name: 'home',
//      component: HomeComponent
//    },
//    {
//      path: '/about',
//      name: 'about',
//      component: AboutComponent
//    }
//  ]
//})