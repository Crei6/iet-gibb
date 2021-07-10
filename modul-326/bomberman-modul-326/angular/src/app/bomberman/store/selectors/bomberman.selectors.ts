import { createFeatureSelector, createSelector } from '@ngrx/store';
import { BombermanState } from '../models/bomberman-state.model';

const getBombermanState = createFeatureSelector<BombermanState>('bomberman');

export const getLabyrinth = createSelector(
  getBombermanState,
  (bombermanState: BombermanState) => bombermanState.labyrinth
);

export const getNotifications = createSelector(
  getBombermanState,
  (bombermanState: BombermanState) => bombermanState.notifications
);

export const getOwnPlayer = createSelector(
  getBombermanState,
  (bombermanState: BombermanState) =>
    bombermanState.labyrinth.players.find(
      (p) => p.name === bombermanState.labyrinth.ownPlayer
    )
);

export const getIsGameStarted = createSelector(
  getBombermanState,
  (bombermanState: BombermanState) => bombermanState.isGameStarted
);

export const getWinner = createSelector(
  getBombermanState,
  (bombermanState: BombermanState) => bombermanState.winnerName
);

export const getHighscoreList = createSelector(
  getBombermanState,
  (bombermanState: BombermanState) => bombermanState.highscoreList
);
