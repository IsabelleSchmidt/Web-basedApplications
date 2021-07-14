import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'
import FotoGalerieView from '@/views/FotoGalerieView.vue'
import AboutView from '@/views/AboutView.vue'
import LoginView from '../views/LoginView.vue'

const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    name: 'FotoGalerieView',
    component: FotoGalerieView
  },
  {
    path: '/about',
    name: 'AboutView',
    component: AboutView
  },
  { 
    path: '/404', 
    component: () => import('../components/NotFound.vue') 
  },
  {
    path: '/:catchAll(.*)', redirect:'404'
  },
  {
    path: '/login',
    name: 'Login/Logout',
    component: LoginView
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
