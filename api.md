#### Connecting:
```json
{
    url: url,
    token: string
}
```
#### Server Message:
```json
{
    type: "serverMessage";
    text: string;
    timestamp: number;
    audio: url(string);
}
```

#### Event:
```json
{
    type: "event";
    event: "connect" | "disconnect" ;
    timestamp: number;
}
```

#### Client Message:
```json
{
    type: "clientMessage";
    text: string;
    timestamp: number;
}
```
