const express = require('express');
const cluster = require('cluster');
const { execFile } = require('child_process');
const app = express();
const port = 3000;
const canonHost = "tscribe.crabdance.com";

var shardIncrement = 1;

app.use(express.json({
    strict: false,
    type:()=>true
}))

app.post('/call', (req, res) => {
    console.log("Got request");
    console.log(req.body);
    await createShard(req.body, {
        host: canonHost,
        port: port+(shardIncrement++),
        token: "idk",
    });
    console.log(`spun up shard at ${canonHost}:${port + shardIncrement}`);
    res(JSON.stringify(`ws://${canonHost}:${port + shardIncrement}`));
})

app.listen(port, () => console.log(`Master Server listening on port ${port}!`))

function createShard({callNumber, userNumber, opening}, {host, port, token}){
    const child = execFile('./shard.js', [callNumber, userNumber, opening, host, port, token], (error, stdout, stderr) => {
        if (error) {
            throw error;
        }
        console.log(stdout);
    });
}