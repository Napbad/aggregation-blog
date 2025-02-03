<script setup lang="ts">
import { ref, onMounted } from 'vue';
import { useRoute, useRouter } from 'vue-router';

// 假设你有一些博客文章的数据
const posts = [
  {
    id: 1,
    title: '我的第一篇博客',
    content: '这是我的第一篇博客内容。欢迎来到我的博客！',
  },
  {
    id: 2,
    title: '学习 Vue 3',
    content: '最近在学习 Vue 3，感觉非常棒！',
  },
];

// 获取路由实例
const route = useRoute();
const router = useRouter();

// 获取博客文章的 ID
const postId = parseInt(route.params.id as string, 10);

// 定义一个响应式变量来存储博客文章的数据
const post = ref({
  id: 0,
  title: '',
  content: '',
});

// 在组件挂载时获取博客文章的数据
onMounted(() => {
  const foundPost = posts.find(p => p.id === postId);
  if (foundPost) {
    post.value = foundPost;
  } else {
    // 如果没有找到博客文章，重定向到 404 页面
    router.push('/404');
  }
});
</script>

<template>
  <div class="single-blog">
    <h1 class="blog-title">{{ post.title }}</h1>
    <div class="blog-content">
      <p>{{ post.content }}</p>
    </div>
  </div>
</template>

<style scoped>
.single-blog {
  padding: 2em;
  max-width: 800px;
  margin: 0 auto;
  font-family: Arial, sans-serif;
  background-color: #fff;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  border-radius: 10px;
  overflow: hidden;
}

.blog-title {
  font-size: 2em;
  margin-bottom: 1em;
  color: #333;
}

.blog-content {
  color: #666;
  line-height: 1.6;
}
</style>
