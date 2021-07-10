import { Direction } from '../../util/direction.enum';
import {
  BombermanAction,
  BombermanActionTypes,
} from '../actions/bomberman.actions';
import { BombermanState } from '../models/bomberman-state.model';

export const initialState: BombermanState = {
  labyrinth: {
    map: [],
    players: [],
    bombs: [],
    ownPlayer: '',
  },
  notifications: [],
  isGameStarted: false,
  winnerName: '',
  highscoreList: [],
};

export const testState: BombermanState = {
  labyrinth: {
    map: [
      ['s', 's', 's', 's', 's', 's', 's', 's', 's'],
      ['s', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 's'],
      ['s', ' ', 's', ' ', 's', ' ', 's', ' ', 's'],
      ['s', ' ', ' ', 'e', ' ', 'e', ' ', ' ', 's'],
      ['s', ' ', 's', ' ', 's', ' ', 's', ' ', 's'],
      ['s', ' ', ' ', 'e', ' ', 'e', ' ', ' ', 's'],
      ['s', ' ', 's', ' ', 's', ' ', 's', ' ', 's'],
      ['s', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 's'],
      ['s', 's', 's', 's', 's', 's', 's', 's', 's'],
    ],
    players: [
      {
        name: 'own',
        x: 1,
        y: 1,
      },
      {
        name: 'enemy',
        x: 1,
        y: 4,
      },
    ],
    bombs: [
      {
        id: 'dsd3r8z8-4rjbfwr',
        x: 5,
        y: 6,
      },
    ],
    ownPlayer: 'own',
  },
  notifications: [],
  isGameStarted: false,
  winnerName: 'IG',
  highscoreList: ['fdfhsdf', 'dfhsd', 'fd', 'jfh34f'],
};

export function BombermanReducer(
  state: BombermanState = initialState,
  action: BombermanAction
): BombermanState {
  switch (action.type) {
    case BombermanActionTypes.GAME_OVER: {
      return {
        ...state,
        isGameStarted: false,
        winnerName: action.winnerName,
        highscoreList: action.highscoreList,
      };
    }

    case BombermanActionTypes.START_GAME: {
      return {
        ...state,
        labyrinth: {
          ...state.labyrinth,
          map: action.map,
        },
        isGameStarted: true,
      };
    }

    case BombermanActionTypes.UPDATE_MAP: {
      return {
        ...state,
        labyrinth: {
          ...state.labyrinth,
          map: action.map,
        },
      };
    }

    case BombermanActionTypes.ADD_PLAYER: {
      return {
        ...state,
        labyrinth: {
          ...state.labyrinth,
          players: [...state.labyrinth.players, action.player],
        },
      };
    }

    case BombermanActionTypes.REMOVE_PLAYER: {
      return {
        ...state,
        labyrinth: {
          ...state.labyrinth,
          players: state.labyrinth.players.filter(
            (p) => p.name !== action.playerName
          ),
        },
      };
    }

    case BombermanActionTypes.MOVE_PLAYER: {
      const players = state.labyrinth.players.map((p) => {
        if (p.name === action.playerName) {
          switch (action.direction) {
            case Direction.DOWN: {
              return {
                name: p.name,
                x: p.x,
                y: p.y + 1,
              };
            }
            case Direction.UP: {
              return {
                name: p.name,
                x: p.x,
                y: p.y - 1,
              };
            }
            case Direction.LEFT: {
              return {
                name: p.name,
                x: p.x - 1,
                y: p.y,
              };
            }
            case Direction.RIGHT: {
              return {
                name: p.name,
                x: p.x + 1,
                y: p.y,
              };
            }
          }
        }
        return p;
      });
      return {
        ...state,
        labyrinth: {
          ...state.labyrinth,
          players,
        },
      };
    }

    case BombermanActionTypes.ADD_NOTIFICATION: {
      const notifications = [...state.notifications, action.notification];
      if (notifications.length === 7) {
        notifications.shift();
      }
      return {
        ...state,
        notifications,
      };
    }

    case BombermanActionTypes.ADD_BOMB: {
      return {
        ...state,
        labyrinth: {
          ...state.labyrinth,
          bombs: [...state.labyrinth.bombs, action.bomb],
        },
      };
    }

    case BombermanActionTypes.REMOVE_BOMB: {
      return {
        ...state,
        labyrinth: {
          ...state.labyrinth,
          bombs: state.labyrinth.bombs.filter((b) => b.id !== action.bombId),
        },
      };
    }

    case BombermanActionTypes.SET_OWN_PLAYER: {
      return {
        ...state,
        labyrinth: {
          ...state.labyrinth,
          ownPlayer: action.name,
        },
      };
    }

    default: {
      return { ...state };
    }
  }
}
