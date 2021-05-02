let express = require('express');
let app = express();
let fs = require("fs");
const math = require("mathjs");
app.use(express.json())

app.post('/calculate', (req, res) => {
    console.log("post to /calculate");
    let result;
    try{
        result = (math.evaluate(req.body.calculate));
        res.setHeader("Content-Type", "application/json");
        res.statusCode = 200;

    } catch (err){
        res.statusCode = 400;
        result = "syntax error, invalid expression";
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