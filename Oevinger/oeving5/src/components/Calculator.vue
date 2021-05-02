<template>
  <div class="calculator">
    <div id="result" class="result"> {{ equation }}</div>
    <div id="buttons" class="grid-container">
      <button type="button" class= "function" @click="clear">C</button>
      <button type="button" class= "function" @click="ans">ANS</button>
      <button type="button" class= "function" @click="del">DEL</button>
      <button type="button" class= "operator" @click="number">+</button>
      <button type="button" @click="number">1</button>
      <button type="button" @click="number">2</button>
      <button type="button" @click="number">3</button>
      <button type="button" class= "operator" @click="number">-</button>
      <button type="button" @click="number">4</button>
      <button type="button" @click="number">5</button>
      <button type="button" @click="number">6</button>
      <button type="button" class= "operator" @click="number">*</button>
      <button type="button" @click="number">7</button>
      <button type="button" @click="number">8</button>
      <button type="button" @click="number">9</button>
      <button type="button" class= "operator" @click="number">/</button>
      <div></div>
      <button type="button" @click="number">0</button>
      <button type="button" @click="number">.</button>
      <button type="button" class= "operator" @click="equals">=</button>
    </div>
  </div>
</template>

<script>


export default {
  name: "Calculator",
  data() {
    return {
      equation: "",
      previousAnswer: null
    }
  },
  methods: {
    clear() {
      this.equation = ""
    },
    del() {
      this.equation = this.equation.substr(0, this.equation.length - 1)
    },
    ans() {
      this.equation += this.previousAnswer.toString()
    },
    number(a) {
      this.equation += (a.target.innerHTML)
    },
    equals(a) {
      try {
        this.$emit("add-calculation", this.equation + "=" + a)
        this.equation = a
        this.previousAnswer = parseFloat(this.equation)
      } catch (e) {
        this.equation = e.toString()
      }
    },
    async calculate() {
      const requestOptions = {
        method: "POST",
        headers: { "Content-Type": "application-json" },
        body: JSON.stringify({ calculate: this.equation })
      };

      const response = await fetch("http://localhost:8081/calculate")
          .then(async response => {
            const data = await response.json();

            if(!response.ok){
              const error = (data && data.result) || response.status;
              return Promise.reject(error);
            }

            this.equals(data.result);

          }).catch(error => {
            console.error('Something went wrong;', error)
          })

    }
  }
}

</script>

<style scoped>
button {
  min-height: 50px;
  min-width: 50px;
  max-height: 100px;
  max-width: 100px;
  width: 50%;
  border: #000000;
  border-radius: 15%;

  text-align: center;
  background-color: #35495e;
  color: #e3eadf;
}

.function{
  background-color: #ced4d2;
  color: #1a1919;
}

.operator{
  background-color: #41b883;
}

.grid-container {
  display: grid;
  grid-template-columns: auto auto auto auto;
  padding: 10px;
  row-gap: 5px;
  column-gap: 5px;
  max-width: 250px;
  text-align: center;
  margin: auto;
  justify-content: center;
}

.result {
  height: 20px;
  background: lightgrey;
  padding: 15px;
  margin: 10px;
}

#result {
  margin: auto;
  max-width: 250px;
}
</style>