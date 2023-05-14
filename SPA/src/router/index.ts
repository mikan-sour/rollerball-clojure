import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/files',
      name: 'files',
      component: () => import('../views/FilesView.vue')
    },
    {
      path: '/files/:l1',
      name: 'files-l1',
      component: () => import('../views/FilesView.vue')
    },
    {
      path: '/files/:l1/:2',
      name: 'files-l2',
      component: () => import('../views/FilesView.vue')
    },
    {
      path: '/files/:l1/:2/:3',
      name: 'files-l3',
      component: () => import('../views/FilesView.vue')
    }
  ]
})

export default router
