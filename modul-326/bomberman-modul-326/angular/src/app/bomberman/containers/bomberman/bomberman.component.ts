import { Component, HostListener, OnInit } from '@angular/core';
import { Store } from '@ngrx/store';
import {
  DropBomb,
  JoinGame,
  MovePlayer,
} from '../../models/client-messages.model';
import { Player } from '../../models/player.model';
import { MessageService } from '../../services/message.service';
import { SocketService } from '../../services/socket.service';
import { SetOwnPlayerAction } from '../../store/actions/bomberman.actions';
import { BombermanState } from '../../store/models/bomberman-state.model';
import {
  getIsGameStarted,
  getOwnPlayer,
} from '../../store/selectors/bomberman.selectors';
import { Direction } from '../../util/direction.enum';

@Component({
  selector: 'app-bomberman',
  templateUrl: './bomberman.component.html',
  styleUrls: ['./bomberman.component.scss'],
})
export class BombermanComponent implements OnInit {
  ownPlayer: Player;
  isGameStarted: boolean;

  constructor(
    private socketService: SocketService,
    private store: Store<BombermanState>,
    private messageService: MessageService
  ) {}

  ngOnInit(): void {
    this.store.select(getOwnPlayer).subscribe((player) => {
      this.ownPlayer = player;
    });

    this.store.select(getIsGameStarted).subscribe((isStarted) => {
      this.isGameStarted = isStarted;
    });

    this.socketService.messages.subscribe((message) => {
      this.messageService.handleMessage(message);
    });
  }

  handleLogin(playerName: string): void {
    const message: JoinGame = { playerName };
    this.socketService.send({ key: 'JoinGame', value: message });
    this.store.dispatch(new SetOwnPlayerAction(playerName));
  }

  @HostListener('window:keydown', ['$event'])
  handleKeyDown(event: KeyboardEvent): void {
    if (!this.isGameStarted) {
      return;
    }

    if (event.key === ' ') {
      const dropBombMessage: DropBomb = {
        playerName: this.ownPlayer?.name,
        positionX: 1,
        positionY: 1,
      };

      this.socketService.send({ key: 'DropBomb', value: dropBombMessage });
      return;
    }

    const movePlayerMessage: MovePlayer = {
      playerName: this.ownPlayer?.name,
      direction: null,
    };

    switch (event.key) {
      case 'w': {
        movePlayerMessage.direction = Direction.UP;
        break;
      }
      case 's': {
        movePlayerMessage.direction = Direction.DOWN;
        break;
      }
      case 'a': {
        movePlayerMessage.direction = Direction.LEFT;
        break;
      }
      case 'd': {
        movePlayerMessage.direction = Direction.RIGHT;
        break;
      }
    }

    this.socketService.send({ key: 'MovePlayer', value: movePlayerMessage });
  }
}
