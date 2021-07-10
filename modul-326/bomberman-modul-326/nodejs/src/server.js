const net = require('net');
const app = require('express')();
const http = require('http').Server(app);

const io = require('socket.io')(http, {
  cors: {
    origin: 'http://localhost:4200',
  },
});

io.on('connection', (webSocket) => {
  console.log('[Socket] Client connected');

  const tcpSocket = net.createConnection({ port: 6969 }, () => {
    console.log('[Socket] Connected to server');
  });

  tcpSocket.on('data', (message) => {
    console.log('[Socket] Received message from server');

    webSocket.emit('message', JSON.parse(message));
  });

  webSocket.on('message', (message) => {
    console.log('[Socket] Received message from client');

    tcpSocket.write(JSON.stringify(message));
  });
});

http.listen(5000, () => {
  console.log('[Server] Listening on http://localhost:5000');
});
