const express = require('express');
const cluster = require('cluster');
const { execFile } = require('child_process');
const app = express();
const port = 3000;
const canonHost = ;

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
        port: port+(shardIncrement++);
    });
    console.log(`spun up shard at ${canonHost}:${port + shardIncrement}`);
    res(`http://${canonHost}:${port + shardIncrement}`);
})

app.listen(port, () => console.log(`Master Server listening on port ${port}!`))

function createShard({number, opening}, {host, port}){
    const child = execFile('./shard.js', [number, opening, host, port], (error, stdout, stderr) => {
        if (error) {
            throw error;
        }
        console.log(stdout);
    });
}