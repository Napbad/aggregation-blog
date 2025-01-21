<template>
  <div id="app">
    <header>
      <nav>
        <router-link to="/login">登录</router-link> |
        <router-link to="/register">注册</router-link> |
        <router-link to="/blog">博客</router-link>
      </nav>
    </header>
    <main>
      <router-view />
    </main>
    <footer>
      <p>版权所有 &copy; 2023</p>
    </footer>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue';

export default defineComponent({
  name: 'App',

});


</script>

<style scoped>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

header, footer {
  background: #f8f9fa;
  padding: 1em;
}

main {
  padding: 1em;
}

nav {
  margin-bottom: 1em;
}

nav a {
  margin: 0 10px;
  text-decoration: none;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}
</style>
