<template>
  <div id="app">
    <WmsIndex></WmsIndex>
  </div>
</template>

<script>

import WmsIndex from "./components/WmsIndex.vue"
import "./assets/fonts.css"

export default {
  name: 'App',
  components: {
    WmsIndex
  }
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
