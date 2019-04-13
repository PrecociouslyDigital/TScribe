const [callNumber, userNumber, opening, url, port] = process.argv;
const app = require('express')();
const expressWs = require('express-ws')(app);
const accountSid = process.env["TWILIO_SID"];
const authToken = process.env["TWILIO_TOKEN"];
const client = require('twilio')(accountSid, authToken);




var wsSingleton;

var wsProcessQueue = [];

var startingMessage;

app.ws('/', function(ws, req) {
    // TODO: Token Auth;
    wsSingleton = ws;
    const call = await client.calls
        .create({
         url: `http://${url}/start`,
         to: callNumber,
         from: '+19729967143'
        });
    wsSingleton.on('message', (msg) => wsProcessQueue.push(processWsMessage(JSON.parse(msg))));
    wsSingleton.on('close', closeSession);
});

app.get('/prompt', (req, res) => {
    wsSingle
});
app.get('/start', (req, res) => {
    wsSingleton.send(JSON.stringify({
        type:"event",
        event:"connect",
    }));
    res.send(start(opening));
});
app.get('/partial', (req, res) => wsSingleton)

function processWsMessage(msg){
    
}
function closeSession(){
    if(wsSingleton.readyState === 1){
        wsSingleton.send(JSON.stringify({
            type:"event",
            event:"disconnect",

        }));
        wsSingleton.close();
    }
}

const mainLoop = (msg, redirect='/prompt')=>`
<?xml version="1.0" encoding="UTF-8"?>
<Response>
    <Gather action="${redirect}" method="GET" input="speech" actionOnEmptyResult=true>
        <Say>
            ${msg}
        </Say>
    </Gather>
</Response>`;
const start = (say, redirect='/prompt')=>`
<?xml version="1.0" encoding="UTF-8"?>
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
