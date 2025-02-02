<template>
  <div class="blog">
    <h1 class="blog-title">博客</h1>

    <div class="blog-container">
      <!-- 目录 -->
      <nav class="blog-toc">
        <ul>
          <li v-for="post in posts" :key="post.id">
            <a :href="`#post-${post.id}`" class="toc-link">{{ post.title }}</a>
          </li>
        </ul>
      </nav>

      <!-- 博客文章 -->
      <div class="blog-posts">
        <article v-for="post in posts" :key="post.id" :id="`post-${post.id}`" class="blog-post" v-show="post.show" @click="hidePost(post)">
          <h2 class="post-title">{{ post.title }}</h2>
          <p class="post-content">{{ post.content }}</p>
        </article>
      </div>
    </div>
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
          show: true
        },
        {
          id: 2,
          title: '学习 Vue 3',
          content: '最近在学习 Vue 3，感觉非常棒！',
          show: true
        },
      ],
    };
  },
  methods: {
    hidePost(post) {
      post.show = false;
    }
  }
});
</script>

<style scoped>
.blog {
  padding: 2em;
  display: flex;
  flex-direction: column;
  align-items: center;
  font-family: Arial, sans-serif;
  background-color: #f9f9f9;
}

.blog-title {
  font-size: 3em;
  margin-bottom: 2em;
  color: #333;
  text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.1);
  animation: fadeIn 1s ease-in-out;
}

.blog-container {
  display: flex;
  max-width: 1200px;
  width: 100%;
  margin: 0 auto;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  border-radius: 10px;
  background-color: #fff;
  overflow: hidden;
}

.blog-toc {
  width: 25%;
  padding: 2em;
  box-sizing: border-box;
  background-color: #f0f0f0;
  border-right: 1px solid #ddd;
  transition: width 0.3s ease;
}

.blog-toc:hover {
  width: 30%;
}

.blog-toc ul {
  list-style-type: none;
  padding: 0;
}

.blog-toc li {
  margin-bottom: 1.5em;
  font-size: 1.1em;
}

.toc-link {
  text-decoration: none;
  color: #007bff;
  font-weight: bold;
  transition: color 0.3s ease, transform 0.3s ease;
}

.toc-link:hover {
  color: #0056b3;
  text-decoration: underline;
  transform: translateX(5px);
}

.blog-posts {
  width: 75%;
  box-sizing: border-box;
  padding: 2em;
  animation: fadeIn 1s ease-in-out;
}

.blog-post {
  margin-bottom: 2em;
  padding: 2em;
  border: 1px solid #ddd;
  border-radius: 10px;
  background-color: #fff;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  transition: transform 0.3s ease, box-shadow 0.3s ease;
  opacity: 0;
  animation: fadeInPost 1s ease-in-out forwards;
  animation-delay: 0.5s;
}

@keyframes fadeInPost {
  to {
    opacity: 1;
  }
}

.blog-post:hover {
  transform: translateY(-5px);
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.15);
}

.post-title {
  font-size: 1.8em;
  margin-top: 0;
  color: #333;
}

.post-content {
  color: #666;
  line-height: 1.6;
}

@keyframes fadeIn {
  from {
    opacity: 0;
  }
  to {
    opacity: 1;
  }
}
</style>