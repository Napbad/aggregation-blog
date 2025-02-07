<template>
  <div class="profile-container">
    <el-row :gutter="20">
      <!-- 个人信息卡片 -->
      <el-col :span="12">
        <el-card class="box-card">
          <template #header>
            <div class="clearfix">
              <span>个人信息</span>
              <el-button style="float: right; padding: 3px 0" type="text" @click="editProfile">编辑个人信息</el-button>
            </div>
          </template>
          <div class="text item">
            <!-- 用户名 -->
            <el-row :gutter="20">
              <el-col :span="24">
                <div class="grid-content">
                  <label>用户名:</label>
                  <span class="value">{{ authorInfo?.authorName }}</span>
                </div>
              </el-col>
            </el-row>
            <!-- 邮箱 -->
            <el-row :gutter="20">
              <el-col :span="24">
                <div class="grid-content">
                  <label>邮箱:</label>
                  <span class="value">{{ authorInfo?.email }}</span>
                </div>
              </el-col>
            </el-row>
            <!-- 注册时间 -->
            <el-row :gutter="20">
              <el-col :span="24">
                <div class="grid-content">
                  <label>注册时间:</label>
                  <span class="value">{{ authorInfo?.joinedTime }}</span>
                </div>
              </el-col>
            </el-row>
            <!-- 博客文章数量 -->
            <el-row :gutter="20">
              <el-col :span="24">
                <div class="grid-content">
                  <label>博客文章数量:</label>
                  <span class="value">{{ articles?.totalPageCount || 0 }}</span>
                </div>
              </el-col>
            </el-row>
          </div>
        </el-card>
      </el-col>

      <!-- 博客文章列表 -->
      <el-col :span="12">
        <el-card class="box-card">
          <template #header>
            <span>我的博客文章</span>
          </template>
          <el-table :data="articles?.rows" style="width: 100%">
            <el-table-column prop="title" label="标题" width="180"></el-table-column>
            <el-table-column prop="date" label="发布时间" width="180"></el-table-column>
            <el-table-column prop="views" label="浏览量"></el-table-column>
          </el-table>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>


<script lang="ts">
import { defineComponent } from 'vue';
import {ElCard, ElRow, ElCol, ElButton, ElTable, ElTableColumn} from 'element-plus';
import {Executor} from "@/api";
import {ArticleQueryOutput, Page} from "@/api/model/static";
import {ArticleController} from "@/api/services";
import {userAccountInfo_localStorageKey, userAccountToken_localStorageKey} from "@/api/constants/keys.ts";
import {AuthorLoginOutput} from "@/api/model/static/AuthorLoginOutput.ts";
import router from "@/router";

export default defineComponent({
  components: {
    ElCard,
    ElRow,
    ElCol,
    ElButton,
    ElTable,
    ElTableColumn,
  },
  data() {
    return {
      authorInfo: null as AuthorLoginOutput | null,
      articles: null as Page<ArticleQueryOutput> | null,
      token: null as String | null
    };
  },
  created() {
    this.authorInfo = JSON.parse(localStorage.getItem(userAccountInfo_localStorageKey)!) as AuthorLoginOutput | null;

    this.token = localStorage.getItem(userAccountToken_localStorageKey);

    if (this.authorInfo === null || this.token === null) {
      router.push("/login");
      return;
    }

    const executor: Executor = async () => {
      const response = await fetch("/api/article/query", {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': localStorage.getItem(userAccountToken_localStorageKey)!.toString(),
        },
        body: JSON.stringify({
          authorId: this.authorInfo!.authorId,
          page: 1,
          pageSize: 10,
        })
      });
      return response.json();
    };

    const articleController = new ArticleController(executor);
    articleController.getArticle({
      body: {
        authorId: this.authorInfo.authorId,
      }
    }).then((response) => {
      this.articles = response; // 假设响应数据在 response.data 中
    }).catch(() => {
      console.log("error");
    });
  },

  methods: {
    editProfile() {
      alert('编辑个人信息功能暂未实现');
    },
  },
});
</script>

S
<style scoped>
.profile-container {
  padding: 20px;
  background-color: #f8f9fa;
  border-radius: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.box-card {
  margin-bottom: 20px;
  border: 1px solid #ebeef5; /* 添加边框 */
  transition: transform 0.2s;
}

.box-card:hover {
  transform: translateY(-10px);
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}

.clearfix:after {
  clear: both;
}

.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}

.grid-content {
  border-bottom: 1px solid #ebeef5; /* 添加底部边框 */
  padding: 10px 0; /* 增加内边距 */
}

.label {
  font-weight: bold;
}

.value {
  margin-left: 10px;
}

.el-button {
  margin-top: 10px;
}

.el-table {
  margin-top: 20px; /* 增加表格与卡片内容的间距 */
}
</style>
