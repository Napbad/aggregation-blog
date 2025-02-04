<script lang="ts">
import {defineComponent} from "vue";

export default defineComponent({
  name: 'NotFound',
})
</script>

<template>
  <div class="not-found">
    <h1>404</h1>
    <p>Oops! The page you are looking for does not exist.</p>
    <router-link to="/">Back to Home</router-link>
  </div>
</template>

<style scoped>
.not-found {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  height: 100vh;
  text-align: center;
  background-color: #f4f4f9;
  color: #333;
}

.not-found h1 {
  font-size: 10rem;
  margin: 0;
  color: #e74c3c;
}

.not-found p {
  font-size: 1.5rem;
  margin: 1rem 0;
}

.not-found a {
  text-decoration: none;
  color: #3498db;
  font-size: 1.2rem;
  transition: color 0.3s ease;
}

.not-found a:hover {
  color: #2980b9;
}
</style>
