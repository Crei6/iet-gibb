import { Bomb } from './bomb.model';
import { Player } from './player.model';

export interface Labyrinth {
  map: string[][];
  players: Player[];
  bombs: Bomb[];
  ownPlayer: string;
}
