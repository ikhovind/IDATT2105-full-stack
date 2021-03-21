<template>
  <div id="log">
    <div id="logContent"  v-for="log in logs" v-bind:key="log">
    <p ref="logs"> {{ log }} </p>
    </div>
  </div>
</template>

<script>
export default{
  name: "Log",
  data(){
    return{
      logs: [],
    }
  },
  methods:{
    addToLog(equation){
      console.log("equation:" + equation)
      this.logs.push(equation);
    }
  }

}
</script>

<style scoped>

</style>