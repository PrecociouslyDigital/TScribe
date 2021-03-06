const express = require('express');
const cluster = require('cluster');
const { exec } = require('child_process');
const app = express();
const port = 8080;
const canonHost = "tscribe-precociouslydigital.c9.io";

var shardIncrement = 1;

app.use(express.json({
    strict: false,
    type:()=>true
}))

app.post('/call', async (req, res) => {
    console.log("Got request");
    console.log(req.body);
    await createShard(req.body, {
        host: canonHost,
        port: port+shardIncrement,
        token: "idk",
    });
    console.log(`spun up shard at ${canonHost}:${port + shardIncrement}`);
    res.json(`ws://${canonHost}:${port + shardIncrement}`);
    shardIncrement++;
})

app.listen(port, () => console.log(`Master Server listening on port ${port}!`))

function createShard({callNumber, userNumber, opening}, {host, port, token}){
    const child = exec('node ./shard.js', [callNumber, userNumber, opening, host, port, token], (error, stdout, stderr) => {
        if (error) {
            throw error;
        }
        console.log(stdout);
    });
}