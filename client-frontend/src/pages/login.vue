<template>
  <div class="login-container">
    <h1 class="login-title">登录</h1>
    <form @submit.prevent="handleLogin" class="login-form">
      <div class="form-group">
        <label>
          <input type="radio" v-model="loginType" value="username" checked /> 用户名
        </label>
        <label>
          <input type="radio" v-model="loginType" value="email" /> 邮箱
        </label>
      </div>
      <div class="form-group" v-if="loginType === 'username'">
        <input type="text" v-model="authorName" placeholder="用户名" class="form-input" />
      </div>
      <div class="form-group" v-if="loginType === 'email'">
        <input type="email" v-model="email" placeholder="邮箱" class="form-input" />
      </div>
      <div class="form-group">
        <input type="password" v-model="password" placeholder="密码" class="form-input" />
      </div>
      <button type="submit" class="login-button">登录</button>
      <div class="register-link">
        <router-link to="/register">还没有账号？去注册</router-link>
      </div>
    </form>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue';
import { UserAccountController } from "@/api/services";
import { Executor } from "@/api";
import {userAccountInfo_localStorageKey} from "@/constants/keys.ts";

export default defineComponent({
  data() {
    return {
      authorName: '' as string,
      email: '' as string,
      password: '' as string,
      authorId: undefined as number | undefined,
      loginType: 'username' as 'username' | 'email',
    };
  },
  methods: {
    async handleLogin() {
      const executor: Executor = async () => {
        const body = this.loginType === 'username'
          ? {
              authorId: this.authorId,
              authorName: this.authorName,
              password: this.password,
            }
          : {
              authorId: this.authorId,
              email: this.email,
              password: this.password,
            };

        const response = await fetch("/api/user-account/login", {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify(body)
        });
        return response.json();
      };

      const userAccountController = new UserAccountController(executor);

      const loginInput = this.loginType === 'username'
        ? {
            authorId: this.authorId,
            authorName: this.authorName,
            password: this.password,
          }
        : {
            authorId: this.authorId,
            email: this.email,
            password: this.password,
          };

      const response = await userAccountController.login({
        body: loginInput
      });

      if (response.authorId) {
        console.log('登录成功', response);
        localStorage.setItem(userAccountInfo_localStorageKey, JSON.stringify(response));
        // 跳转到主页或其他页面
        this.$router.push('/blog');
      } else {
        console.error('登录失败', response);
      }
    }
  }
});
</script>

<style scoped>
.login-container {
  max-width: 400px;
  margin: 0 auto;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 8px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  background-color: #fff;
}

.login-title {
  text-align: center;
  margin-bottom: 20px;
  color: #333;
}

.login-form {
  display: flex;
  flex-direction: column;
}

.form-group {
  margin-bottom: 15px;
}

.form-input {
  width: 100%;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
}

.login-button {
  width: 100%;
  padding: 10px;
  background-color: #007bff;
  color: #fff;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 16px;
}

.login-button:hover {
  background-color: #0056b3;
}

.register-link {
  text-align: center;
  margin-top: 10px;
}

.register-link a {
  color: #007bff;
  text-decoration: none;
}

.register-link a:hover {
  text-decoration: underline;
}
</style>
