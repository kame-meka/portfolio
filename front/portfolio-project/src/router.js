import HomeComponent from './components/HomeComponent.vue'
import ServiceComponent from './components/ServiceComponent.vue'
import LoginComponent from './components/LoginComponent.vue'
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
  },
  {
    path: '/login',
    name: 'login',
    component: LoginComponent
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
