import { Direction } from '../util/direction.enum';

export interface JoinGame {
  playerName: string;
}

export interface MovePlayer {
  playerName: string;
  direction: Direction;
}

export interface DropBomb {
  playerName: string;
  positionX: number;
  positionY: number;
}

export interface ClientMessage {
  key: 'JoinGame' | 'MovePlayer' | 'DropBomb';
  value: JoinGame | MovePlayer | DropBomb;
}
