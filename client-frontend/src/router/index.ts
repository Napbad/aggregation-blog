import { createRouter, createWebHistory } from 'vue-router';
import Login from '../pages/login.vue';
import Register from '../pages/register.vue';
import Blog from '../pages/blog.vue';
import WritePage  from "../pages/write_page.vue";
import NotFound from "../pages/error_pages/not_found.vue"

const routes = [
  { path: '/login', component: Login },
  { path: '/register', component: Register },
  { path: '/blog', component: Blog },
  { path: '/', component: Blog },
  { path: '/write', component: WritePage},
  { path: '/not_found', component: NotFound},
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
