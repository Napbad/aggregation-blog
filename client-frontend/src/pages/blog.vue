<template>
  <div class="blog">
    <h1>博客</h1>
    <article v-for="post in posts" :key="post.id" class="blog-post">
      <h2>{{ post.title }}</h2>
      <p>{{ post.content }}</p>
    </article>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue';

export default defineComponent({
  name: 'Blog',
  data() {
    return {
      posts: [
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
      ],
    };
  },
});
</script>

<style scoped>
.blog {
  padding: 2em;
}

.blog-post {
  margin-bottom: 2em;
  padding: 1em;
  border: 1px solid #ddd;
  border-radius: 5px;
}

.blog-post h2 {
  margin-top: 0;
}
</style>
