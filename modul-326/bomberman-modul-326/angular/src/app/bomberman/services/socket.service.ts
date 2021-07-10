import { Injectable } from '@angular/core';
import { Socket } from 'ngx-socket-io';
import { ClientMessage } from '../models/client-messages.model';
import { ServerMessage } from '../models/server-messages.model';

@Injectable({
  providedIn: 'root',
})
export class SocketService {
  messages = this.socket.fromEvent<ServerMessage>('message');

  constructor(private socket: Socket) {}

  send(message: ClientMessage): void {
    this.socket.emit('message', message);
  }
}
