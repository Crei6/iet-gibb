import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Store } from '@ngrx/store';
import { Bomb } from '../models/bomb.model';
import { Player } from '../models/player.model';
import {
  BombDropped,
  BombExploded,
  ErrorMessage,
  GameOver,
  PlayerHit,
  PlayerJoined,
  PlayerMoved,
  ServerMessage,
  StartGame,
  Update,
} from '../models/server-messages.model';
import {
  AddBombAction,
  AddNotificationAction,
  AddPlayerAction,
  GameOverAction,
  MovePlayerAction,
  RemoveBombAction,
  RemovePlayerAction,
  StartGameAction,
  UpdateMapAction,
} from '../store/actions/bomberman.actions';
import { BombermanState } from '../store/models/bomberman-state.model';

@Injectable({
  providedIn: 'root',
})
export class MessageService {
  constructor(
    private store: Store<BombermanState>,
    private router: Router
  ) {}

  handleMessage(message: ServerMessage): void {
    switch (message.key) {
      case 'StartGame': {
        this.store.dispatch(
          new StartGameAction((message.value as StartGame).map)
        );
        this.store.dispatch(new AddNotificationAction('Spiel started'));
        break;
      }
      case 'Update': {
        this.store.dispatch(new UpdateMapAction((message.value as Update).map));
        break;
      }
      case 'GameOver': {
        const msg = message.value as GameOver;
        this.store.dispatch(
          new GameOverAction(msg.winnerName, msg.highscoreList)
        );
        this.router.navigate(['bomberman/highscore']);
        break;
      }
      case 'ErrorMessage': {
        this.store.dispatch(
          new AddNotificationAction(
            (message.value as ErrorMessage).errorMessage
          )
        );
        break;
      }
      case 'PlayerJoined': {
        const msg = message.value as PlayerJoined;
        const player: Player = {
          name: msg.playerName,
          x: msg.initialX,
          y: msg.initialY,
        };
        this.store.dispatch(new AddPlayerAction(player));
        this.store.dispatch(
          new AddNotificationAction(`${player.name} hat sich angemeldet`)
        );
        break;
      }
      case 'PlayerMoved': {
        const msg = message.value as PlayerMoved;
        this.store.dispatch(
          new MovePlayerAction(msg.playerName, msg.direction)
        );
        break;
      }
      case 'PlayerHit': {
        const msg = message.value as PlayerHit;
        this.store.dispatch(new RemovePlayerAction(msg.playerName));
        break;
      }
      case 'BombDropped': {
        const msg = message.value as BombDropped;
        const bomb: Bomb = {
          id: msg.id,
          x: msg.positionX,
          y: msg.positionY,
        };
        this.store.dispatch(new AddBombAction(bomb));
        break;
      }
      case 'BombExploded': {
        const msg = message.value as BombExploded;
        this.store.dispatch(new RemoveBombAction(msg.id));
        break;
      }
    }
  }
}
