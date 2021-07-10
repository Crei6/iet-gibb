import { Action } from '@ngrx/store';
import { Bomb } from '../../models/bomb.model';
import { Player } from '../../models/player.model';
import { Direction } from '../../util/direction.enum';

export enum BombermanActionTypes {
  UPDATE_MAP = '[Bomberman State] update map',
  ADD_NOTIFICATION = '[Bomberman State] add notification',
  ADD_PLAYER = '[Bomberman State] add player',
  MOVE_PLAYER = '[Bomberman State] move player',
  ADD_BOMB = '[Bomberman State] add bomb',
  REMOVE_BOMB = '[Bomberman State] remove bomb',
  START_GAME = '[Bomberman State] start game',
  GAME_OVER = '[Bomberman State] game over',
  REMOVE_PLAYER = '[Bomberman State] remove player',
  SET_OWN_PLAYER = '[Bomberman State] set own player',
}

export class RemovePlayerAction implements Action {
  readonly type = BombermanActionTypes.REMOVE_PLAYER;

  constructor(public playerName: string) {}
}

export class GameOverAction implements Action {
  readonly type = BombermanActionTypes.GAME_OVER;

  constructor(public winnerName: string, public highscoreList: string[]) {}
}

export class StartGameAction implements Action {
  readonly type = BombermanActionTypes.START_GAME;

  constructor(public map: string[][]) {}
}

export class UpdateMapAction implements Action {
  readonly type = BombermanActionTypes.UPDATE_MAP;

  constructor(public map: string[][]) {}
}

export class AddPlayerAction implements Action {
  readonly type = BombermanActionTypes.ADD_PLAYER;

  constructor(public player: Player) {}
}

export class MovePlayerAction implements Action {
  readonly type = BombermanActionTypes.MOVE_PLAYER;

  constructor(public playerName: string, public direction: Direction) {}
}

export class AddBombAction implements Action {
  readonly type = BombermanActionTypes.ADD_BOMB;

  constructor(public bomb: Bomb) {}
}

export class RemoveBombAction implements Action {
  readonly type = BombermanActionTypes.REMOVE_BOMB;

  constructor(public bombId: string) {}
}

export class AddNotificationAction implements Action {
  readonly type = BombermanActionTypes.ADD_NOTIFICATION;

  constructor(public notification: string) {}
}

export class SetOwnPlayerAction implements Action {
  readonly type = BombermanActionTypes.SET_OWN_PLAYER;

  constructor(public name: string) {}
}

export type BombermanAction =
  | RemovePlayerAction
  | GameOverAction
  | StartGameAction
  | UpdateMapAction
  | AddPlayerAction
  | MovePlayerAction
  | AddNotificationAction
  | AddBombAction
  | RemoveBombAction
  | SetOwnPlayerAction;
