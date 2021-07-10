import { Direction } from '../util/direction.enum';

export interface StartGame {
  map: string[][];
}

export interface Update {
  map: string[][];
}

export interface GameOver {
  winnerName: string;
  highscoreList: string[];
}

export interface ErrorMessage {
  errorMessage: string;
}

export interface PlayerJoined {
  playerName: string;
  initialX: number;
  initialY: number;
}

export interface PlayerMoved {
  playerName: string;
  direction: Direction;
}

export interface PlayerHit {
  playerName: string;
}

export interface BombDropped {
  id: string;
  positionX: number;
  positionY: number;
}

export interface BombExploded {
  id: string;
}

export interface ServerMessage {
  key: 'StartGame' | 'Update' | 'GameOver' | 'ErrorMessage' | 'PlayerJoined' | 'PlayerMoved' | 'PlayerHit' | 'BombDropped' | 'BombExploded';
  value: StartGame | Update | GameOver | ErrorMessage | PlayerJoined | PlayerMoved | PlayerHit | BombDropped | BombExploded;
}
