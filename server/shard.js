const [callNumber, userNumber, opening, url, port] = process.argv;
const app = require('express')();
const expressWs = require('express-ws')(app);

var wsSingleton;

var wsProcessQueue = [];

var startingMessage;

app.ws('/', function(ws, req) {
    // TODO: Token Auth;
    wsSingleton = ws;
    wsSingleton.on('message', (msg) => wsProcessQueue.push(processWsMessage(JSON.parse(msg))));
    wsSingleton.on('close', closeSession);
});

app.get('/prompt', (req, res) => {
    wsSingle
});
app.get('/start', (req, res) => {

});
app.get('/partial', (req, res) => wsSingleton)

function processWsMessage(msg){
    
}
function closeSession(){
}

const mainLoop = (msg, redirect='/prompt')=>`
<?xml version="1.0" encoding="UTF-8"?>
<Response>
    <Gather action="/prompt" method="GET" input="speech">
        <Say>
            ${msg}
        </Say>
    </Gather>
    <Redirect method='GET'>${redirect}</Redirect>
</Response>`;
const start = (say, redirect='/prompt')=>`
<?xml version="1.0" encoding="UTF-8"?>
<Response>
    <Say>${say}</Say>
    <Redirect method='GET'>${redirect}</Redirect>
</Response>`;

const end = `<?xml version="1.0" encoding="UTF-8"?>
<Response>
    <Say>User has hung up. Goodbye!</Say>
    <Hangup/>
</Response>`


app.listen(port, () => console.log(`shard listening on port ${port}!`))
