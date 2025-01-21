<template>
  <div class="login-container">
    <h1 class="login-title">登录</h1>
    <form @submit.prevent="handleLogin" class="login-form">
      <div class="form-group">
        <input type="text" v-model="username" placeholder="用户名" class="form-input"/>
      </div>
      <div class="form-group">
        <input type="password" v-model="password" placeholder="密码" class="form-input"/>
      </div>
      <button type="submit" class="login-button">登录</button>
    </form>
  </div>
</template>


<script lang="ts">
import {defineComponent, PropType} from 'vue';
import {UserAccountController} from "@/api/services";

export default defineComponent({
  props: {
    executor: {
      type: Object as PropType<any>,
      required: true,
    },
  },
  data() {
    return {
      data: {
        username: String,
        password: String,
      },
      methods: {
        password: "",
        username: "",

        async handleLogin() {
          const userAccountController = new UserAccountController(this.executor);
          const response = await userAccountController.login({
            body: {
              username: this.username,
              password: this.password,
            },
          });
          console.log('登录成功', response);
        },
      }
    }
  }
})
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
</style>
