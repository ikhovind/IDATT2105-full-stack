<template>
  <div class="calculator">
    <div id="result" class="result"> {{ equation }}</div>
    <div id="buttons" class="grid-container">
      <button type="button" @click="clear">C</button>
      <button type="button" @click="ans">ANS</button>
      <button type="button" @click="del">DEL</button>
      <button type="button" @click="number">+</button>
      <button type="button" @click="number">1</button>
      <button type="button" @click="number">2</button>
      <button type="button" @click="number">3</button>
      <button type="button" @click="number">-</button>
      <button type="button" @click="number">4</button>
      <button type="button" @click="number">5</button>
      <button type="button" @click="number">6</button>
      <button type="button" @click="number">*</button>
      <button type="button" @click="number">7</button>
      <button type="button" @click="number">8</button>
      <button type="button" @click="number">9</button>
      <button type="button" @click="number">/</button>
      <div></div>
      <button type="button" @click="number">0</button>
      <button type="button" @click="number">.</button>
      <button type="button" @click="equals">=</button>
    </div>
  </div>
</template>

<script>


export default {
  name: "Calculator",
  data(){
    return{
      equation: "",
      previousAnswer: null
    }
  },
  methods:{
    clear(){
      this.equation = ""
    },
    del(){
      this.equation = this.equation.substr(0,this.equation.length-1)
    },
    ans(){
      this.equation += this.previousAnswer.toString()
    },
    number(a){
      this.equation += (a.target.innerHTML)
    },
    equals(){
      try{
        this.equation = eval(this.equation)
        this.previousAnswer = parseFloat(this.equation)
      } catch (e) {
        this.equation = e.toString()
      }
    }
  }
}

</script>

<style scoped>
.grid-container {
  display: grid;
  grid-template-columns: auto auto auto auto;
  background-color: #2196F3;
  padding: 10px;
  column-gap: 5px;
  row-gap: 5px;
}
.result{
  height: 20px ;
  background: lightgrey;
  padding: 15px;
  margin: 10px;
}
</style>