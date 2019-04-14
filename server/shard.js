const [node, appName, callNumber, userNumber, opening, url, port] = process.argv;
const app = require('express')();
const expressWs = require('express-ws')(app);
const accountSid = process.env["TWILIO_SID"];
const authToken = process.env["TWILIO_TOKEN"];
const client = require('twilio')(accountSid, authToken);

var wsSingleton;

var sendQueue = [];
function send(msg){
    sendQueue.push(msg);
    if(wsSingle.readyState === 1){
        sendQueue.map((i) => wsSingleton.send(i))
        sendQueue = [];
    }else{
    }
    
}

var clientMsgs = ["sample ", "message", "owo"];

var startingMessage;

var die = false;

app.ws('/', async function(ws, req) {
    // TODO: Token Auth;
    wsSingleton = ws;
    const call = await client.calls
        .create({
            method:"GET",
         url: `http://${url}:${port}/start`,
         to: callNumber,
         from: '+19729967143'
        });
    wsSingleton.on('message', (msgRaw) => {
        const msg = JSON.parse(msgRaw);
        if(msg.type==="event"){
            if(msg.event === "disconnect"){
                die = true;
            }
        }else if(msg.type === "clientMessage"){
            send(msg.text);
        }
    });
    wsSingleton.on('close', closeSession);
});

app.get('/prompt', (req, res) => {
    if(req.query.CallStatus === "completed" || die){
        closeSession();
        res.send(end());
        return;
    }
    wsSingleton.send(JSON.stringify({
        type:"serverMessage",
        text: req.query.SpeechResult
    }));
    res.send(mainLoop(clientMsgs.join('\n')))
});
app.get('/start', (req, res) => {
    wsSingleton.send(JSON.stringify({
        type:"event",
        event:"connect",
    }));
    console.log(`Staring twilio at ${url}:${port}`);
    res.send(start(opening));
});
app.get('/partial', (req, res) => {});/*wsSingleton.send(JSON.stringify({
    type:"partialMessage",
    text:req.query.UnstableSpeechResult,
})));*/

function closeSession(){
    console.log("closing session");
    if(wsSingleton.readyState === 1){
        wsSingleton.send(JSON.stringify({
            type:"event",
            event:"disconnect",

        }));
        wsSingleton.close();
    }
    server.close();
}

const mainLoop = (msg, redirect='/prompt')=>`<?xml version="1.0" encoding="UTF-8"?>
<Response>
    <Say>${msg}</Say>
    <Gather action="${redirect}" method="GET" input="speech" actionOnEmptyResult="true">
    </Gather>
</Response>`;
const start = (say, redirect='/prompt')=>`<?xml version="1.0" encoding="UTF-8"?>
<Response>
    <Say>${say}</Say>
    <Redirect method='GET'>${redirect}</Redirect>
</Response>`;

const end = () => `<?xml version="1.0" encoding="UTF-8"?>
<Response>
    <Say>User has hung up. Goodbye!</Say>
    <Hangup/>
</Response>`;


const server = app.listen(port, () => console.log(`shard listening on port ${port}!`));
