<template>
  <div id="app">
    <header>
      <nav class="nav-container">
        <div class="nav-left">
          <router-link to="/blog" class="nav-link">博客</router-link>
          <router-link to="/write" class="nav-link">写作</router-link>
        </div>
        <div class="nav-middle">
          <input type="text" placeholder="搜索..." class="search-bar">
        </div>
        <div class="nav-right">
          <router-link to="/account" class="nav-link">个人账号</router-link>
        </div>
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

header {
  background: #f8f9fa;
  padding: 1em;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

main {
  padding: 1em;
}

footer {
  background: #f8f9fa;
  padding: 1em;
  border-top: 1px solid #e9ecef;
}

.nav-container {
  display: flex;
  justify-content: space-between;
  align-items: center;
  max-width: 1200px;
  margin: 0 auto;
}

.nav-left,
.nav-right {
  display: flex;
  align-items: center;
}

.nav-link {
  margin: 0 15px;
  text-decoration: none;
  color: #2c3e50;
  font-weight: 500;
  transition: color 0.3s ease;
}

.nav-link:hover {
  color: #42b983;
}

.search-bar {
  padding: 8px 12px;
  border: 1px solid #ced4da;
  border-radius: 4px;
  width: 250px;
  outline: none;
  transition: border-color 0.3s ease;
}

.search-bar:focus {
  border-color: #42b983;
}
</style>