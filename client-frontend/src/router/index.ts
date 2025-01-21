import { createRouter, createWebHistory } from 'vue-router';
import Login from '../pages/login.vue';
import Register from '../pages/register.vue';
import Blog from '../pages/blog.vue';

const routes = [
  { path: '/login', component: Login },
  { path: '/register', component: Register },
  { path: '/blog', component: Blog },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
