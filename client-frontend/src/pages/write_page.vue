<template>
  <!-- 整体容器 -->
  <div class="write-container">
    <h1 class="page-title">撰写文章</h1>
    <div class="form-wrapper">
      <div class="form-group">
        <label for="title" class="form-label">标题:</label>
        <input id="title" v-model="title" type="text" class="form-input" placeholder="请输入文章标题" />
      </div>

      <!-- 分类选择框 -->
      <div class="form-group">
        <label for="category" class="form-label">分类:</label>
        <select id="category" v-model="category" class="form-select">
          <option v-for="cat in categories" :key="cat.id" :value="cat.id">{{ cat.name }}</option>
        </select>
      </div>

      <!-- 版权信息选择框 -->
      <div class="form-group">
        <label for="copyrightInfo" class="form-label">版权信息:</label>
        <select id="copyrightInfo" v-model="copyrightInfo" class="form-select">
          <option v-for="info in copyrightInfos" :key="info.id" :value="info.id">{{ info.name }}</option>
        </select>
      </div>
    </div>

    <!-- Vue Markdown Editor 组件 -->
    <v-md-editor v-model="markdownText" :height="500" class="md-editor"/>

    <br>
    <br>
    <br>

    <!-- 保存按钮，点击时调用 saveMarkdown 方法 -->
    <button @click="saveMarkdown" class="save-button">保存</button>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref } from 'vue';
import '@kangc/v-md-editor/lib/style/codemirror-editor.css';
// import githubTheme from '@kangc/v-md-editor/lib/theme/github.js';
import '@kangc/v-md-editor/lib/theme/style/github.css';
import { Executor } from '@/api';
import { ArticleController } from '@/api/services';
import {useRouter} from "vue-router";
import {userAccountInfo_localStorageKey} from "@/constants/keys.ts";

export default defineComponent({
  components: {
    // VueMarkdownEditor,
  },

  setup() {
    const router = useRouter();
    const userData = localStorage.getItem(userAccountInfo_localStorageKey);
    if (!userData) {
      router.push('/login');
    }
    return {
      // VueMarkdownEditor,
      markdownText
    };
  },
  data() {
    return {
      markdownText: '',
      title: '',
      category: '',
      copyrightInfo: '',
      // 假设分类和版权信息的选项列表
      categories: [
        { id: '1', name: '技术' },
        { id: '2', name: '生活' },
        { id: '3', name: '娱乐' },
      ],
      copyrightInfos: [
        { id: '1', name: '原创' },
        { id: '2', name: '转载' },
        { id: '3', name: '翻译' },
      ],
    };
  },
  methods: {
    async saveMarkdown() {
      const executor: Executor = async () => {
        const body = await fetch('/api/article/add', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify({
            title: 'test',
            content: this.markdownText,
            authorId: 'test',
            authorName: 'test',
            categoryId: 'test',
            categoryName: 'test',
            tagIds: ['test'],
            tagNames: ['test'],
          }),
        });
        return body.json();
      };

      const articleController = new ArticleController(executor);

      const response = await articleController.add({
        body: {
          title: 'test',
          content: this.markdownText,
          authorId: 1,
          category: 'test',
          copyrightInfo: 'test',
        },
      });

      console.log(response);
    },
  },
});

// 配置 VMdEditor 使用 github 主题
// VMdEditor.use(githubTheme);

// 定义一个响应式数据，用于存储 Markdown 文本
const markdownText = ref('');
</script>

<style scoped>
.write-container {
  padding: 40px;
  max-width: 1600px;
  margin: 0 auto;
  background-color: #f9f9f9;
  border-radius: 12px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}

.page-title {
  text-align: center;
  color: #333;
  margin-bottom: 30px;
  font-size: 28px;
}

.form-wrapper {
  display: grid;
  grid-template-columns: 1fr;
  gap: 20px;
  margin-bottom: 30px;
}

.form-group {
  display: flex;
  flex-direction: column;
}

.form-label {
  font-size: 16px;
  color: #555;
  margin-bottom: 8px;
}

.form-input,
.form-select {
  padding: 12px 16px;
  border: 1px solid #ddd;
  border-radius: 8px;
  font-size: 16px;
  transition: border-color 0.3s ease;
}

.form-input:focus,
.form-select:focus {
  outline: none;
  border-color: #007BFF;
}

.md-editor {
  border: 1px solid #ddd;
  border-radius: 8px;
  margin-bottom: 30px;
  overflow: hidden;
}

.save-button {
  padding: 14px 24px;
  background-color: #007BFF;
  color: white;
  border: none;
  border-radius: 8px;
  font-size: 16px;
  cursor: pointer;
  transition: background-color 0.3s ease;
  display: block;
  width: 100%;
}

.save-button:hover {
  background-color: #0056b3;
}
</style>