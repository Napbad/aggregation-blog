<template>
  <div class="register-container">
    <h1 class="register-title">注册</h1>
    <form @submit.prevent="handleRegister" class="register-form">
      <div class="form-group">
        <input type="text" v-model="authorName" placeholder="用户名" class="form-input" />
      </div>
      <div class="form-group">
        <input type="password" v-model="password" placeholder="密码" class="form-input" />
      </div>
      <div class="form-group">
        <input type="email" v-model="email" placeholder="邮箱" class="form-input" />
      </div>
      <div class="form-group">
        <input type="text" v-model="captcha" placeholder="验证码" class="form-input" />
      </div>
      <button type="button" class="register-button" @click="handleGetCaptcha">获取验证码</button>
      <p></p>
      <button type="submit" class="register-button">注册</button>
    </form>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue';
import { UserAccountController } from '@/api/services/UserAccountController';
import {AuthorRegisterInput} from "@/api/model/static";
import {Executor} from "@/api";
import {ElMessage} from "element-plus";

export default defineComponent({
  data() {
    return {
      authorName: '',
      password: '',
      email: '',
      captcha: '',
      authorId: undefined as number | undefined,
    };
  },
  methods: {
    async handleRegister() {
      const executor: Executor = async () => {
        const response = await fetch("/api/user-account/register", {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify({
            authorId: this.authorId,
            authorName: this.authorName,
            email: this.email,
            password: this.password,
            captcha: this.captcha,
          })
        });
        return response.json();
      };
      if (!executor) {
        console.error('Executor is not provided');
        return;
      }
      const userAccountController = new UserAccountController(executor);
      const registerData : AuthorRegisterInput = {
        authorId: this.authorId,
        authorName: this.authorName,
        password: this.password,
        email: this.email,
        captcha: this.captcha,
      };
      const response = await userAccountController.register({
        body: registerData,
      });
      console.log('注册成功', response);
    },
    async handleGetCaptcha() {
      const executor: Executor = async () => {
        const response = await fetch("http://localhost:8080/user-account/get-captcha", {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify({
            authorId: this.authorId,
            authorName: this.authorName,
            email: this.email,
            password: this.password,
          })
        });
        return response.json();
      };

      const userAccountController = new UserAccountController(executor);
      const response = await userAccountController.getCaptcha({
        body: {
          authorId: this.authorId,
          email: this.email,
        },
      });

      ElMessage.info(response);
    }
  },
});
</script>

<style scoped>
.register-container {
  max-width: 400px;
  margin: 0 auto;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 8px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  background-color: #fff;
}

.register-title {
  text-align: center;
  margin-bottom: 20px;
  color: #333;
}

.register-form {
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

.register-button {
  width: 100%;
  padding: 10px;
  background-color: #28a745;
  color: #fff;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 16px;
}

.register-button:hover {
  background-color: #218838;
}
</style>
<style scoped>
.register-container {
  max-width: 400px;
  margin: 0 auto;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 8px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  background-color: #fff;
}

.register-title {
  text-align: center;
  margin-bottom: 20px;
  color: #333;
}

.register-form {
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

.register-button {
  width: 100%;
  padding: 10px;
  background-color: #28a745;
  color: #fff;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 16px;
}

.register-button:hover {
  background-color: #218838;
}
</style>
