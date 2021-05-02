let express = require('express');
let cors = require('cors');
let app = express();
let fs = require("fs");
const math = require("mathjs");
app.use(express.json())
app.use(cors())
app.use(function (req,res,next) {
    res.setHeader('Access-Control-Allow-Origin', '*')
    res.setHeader('Access-Control-Allow-Methods', 'GET, POST, OPTIONS, PUT, PATCH, DELETE');
    res.setHeader('Access-Control-Allow-Headers', 'X-Requested-With,content-type');
    res.setHeader('Access-Control-Allow-Credentials', true);

    next();
});

app.post('/calculate', cors(), (req, res) => {
    console.log("post to /calculate");
    console.log(req.body)
    let result;
    try{
        result = (math.evaluate(req.body.calculate));
        res.setHeader("Content-Type", "application/json");
        res.statusCode = 200;

    } catch (err){
        res.statusCode = 400;
        result = "Syntax Error; Invalid expression!";
        console.log(err);
    }
    const responseBody = {
        result: result
    }

    res.write(JSON.stringify(responseBody));
    res.end();

})

let server = app.listen(8081, function () {
    let host = server.address().address
    let port = server.address().port
})