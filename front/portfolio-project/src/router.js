import HomeComponent from './components/HomeComponent.vue'
import ServiceComponent from './components/ServiceComponent.vue'
import { createRouter, createWebHistory } from 'vue-router'

const routes = [
  {
    path: '/',
    name: 'Home',
    component: HomeComponent
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
