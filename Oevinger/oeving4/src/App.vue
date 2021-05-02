<template>
  <div id="app">
    <img alt="Vue logo" src="./assets/logo.png">
    <Calculator v-on:add-calculation="addCalculation"></Calculator>
    <Log ref="logs"></Log>
  </div>
</template>

<script>
import Calculator from "@/components/Calculator";
import Log from "./components/Log";

export default {
  name: 'App',
  components: {
    Calculator,
    Log,
  },
  methods: {
    addCalculation(equation) {
      this.$refs.logs.addToLog(equation)
    }
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #393b39;
  background-color: #1a1919;
  height: 100%;
  overflow: auto;
  padding-top: 7%;
}

html, body {
  height: 100%;
  margin: 0;
  padding: 0;
}
</style>
