<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<script>

import "./assets/fonts.css"

export default {
  name: 'App',
  components: {}
}
</script>

<style>
#app {
  font-family: intelone, WenKai, Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #20463f;
  height: 100%;
  /* margin-top: 60px; */
}
</style>
