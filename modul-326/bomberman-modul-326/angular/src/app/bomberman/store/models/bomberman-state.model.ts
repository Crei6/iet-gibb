import { Labyrinth } from '../../models/labyrinth.model';

export interface BombermanState {
  labyrinth: Labyrinth;
  notifications: string[];
  isGameStarted: boolean;
  winnerName: string;
  highscoreList: string[];
}
