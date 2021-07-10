import {
  AfterViewInit,
  Component,
  ElementRef,
  OnInit,
  ViewChild,
} from '@angular/core';
import { Store } from '@ngrx/store';
import { Bomb } from '../../models/bomb.model';
import { Player } from '../../models/player.model';
import { BombermanState } from '../../store/models/bomberman-state.model';
import { getLabyrinth } from '../../store/selectors/bomberman.selectors';

@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.scss'],
})
export class GameComponent implements OnInit, AfterViewInit {
  @ViewChild('game') canvas: ElementRef<HTMLCanvasElement>;
  context: CanvasRenderingContext2D;

  backgroundImage = new Image();
  solidWallImage = new Image();
  explodableWallImage = new Image();
  playerImage = new Image();
  enemyPlayerImage = new Image();
  bombImage = new Image();

  map: string[][];
  players: Player[];
  ownPlayer: string;
  bombs: Bomb[];

  size: number;
  tileSize = 64;

  constructor(private store: Store<BombermanState>) {}

  ngOnInit(): void {
    this.store.select(getLabyrinth).subscribe((labyrinth) => {
      this.map = labyrinth.map;
      this.players = labyrinth.players;
      this.ownPlayer = labyrinth.ownPlayer;
      this.bombs = labyrinth.bombs;

      if (this.size) {
        this.drawGame();
      }

      this.size = labyrinth.map.length;
    });
  }

  ngAfterViewInit(): void {
    this.canvas.nativeElement.width = this.size * this.tileSize;
    this.canvas.nativeElement.height = this.size * this.tileSize;
    this.context = this.canvas.nativeElement.getContext('2d');

    this.loadImages(() => {
      this.drawGame();
    });
  }

  loadImages(callback: () => void): void {
    const totalImages = 6;
    let loaded = 0;
    this.backgroundImage.onload = () => {
      loaded++;
      if (loaded === totalImages) {
        callback();
      }
    };
    this.solidWallImage.onload = () => {
      loaded++;
      if (loaded === totalImages) {
        callback();
      }
    };
    this.explodableWallImage.onload = () => {
      loaded++;
      if (loaded === totalImages) {
        callback();
      }
    };
    this.playerImage.onload = () => {
      loaded++;
      if (loaded === totalImages) {
        callback();
      }
    };
    this.enemyPlayerImage.onload = () => {
      loaded++;
      if (loaded === totalImages) {
        callback();
      }
    };
    this.bombImage.onload = () => {
      loaded++;
      if (loaded === totalImages) {
        callback();
      }
    };
    this.backgroundImage.src = '../../../../assets/img/background-tile.png';
    this.solidWallImage.src = '../../../../assets/img/solid-tile.png';
    this.explodableWallImage.src = '../../../../assets/img/explodable-tile.png';
    this.playerImage.src = '../../../../assets/img/player.png';
    this.enemyPlayerImage.src = '../../../../assets/img/enemy-player.png';
    this.bombImage.src = '../../../../assets/img/bomb.png';
  }

  drawGame(): void {
    this.context.clearRect(
      0,
      0,
      this.canvas.nativeElement.width,
      this.canvas.nativeElement.height
    );

    this.drawBackground();
    this.drawWalls();
    this.drawBombs();
    this.drawPlayers();
  }

  drawBackground(): void {
    for (let i = 0; i < this.size; i++) {
      for (let j = 0; j < this.size; j++) {
        this.context.drawImage(
          this.backgroundImage,
          j * this.tileSize,
          i * this.tileSize,
          this.tileSize,
          this.tileSize
        );
      }
    }
  }

  drawWalls(): void {
    for (let i = 0; i < this.size; i++) {
      for (let j = 0; j < this.size; j++) {
        if (this.map[i][j] === 'e') {
          this.drawExplodableWall(j, i);
        }
        if (this.map[i][j] === 's') {
          this.drawSolidWall(j, i);
        }
      }
    }
  }

  drawPlayers(): void {
    this.players
      .filter((p) => p.name !== this.ownPlayer)
      .forEach((player) => {
        this.context.drawImage(
          this.enemyPlayerImage,
          player.x * this.tileSize,
          player.y * this.tileSize
        );
      });

    const ownPlayer = this.players.find((p) => p.name === this.ownPlayer);
    this.context.drawImage(
      this.playerImage,
      ownPlayer.x * this.tileSize,
      ownPlayer.y * this.tileSize - this.tileSize
    );
  }

  drawBombs(): void {
    this.bombs.forEach((bomb) => {
      this.context.drawImage(
        this.bombImage,
        // + 8 because of size of bomb image (only 48px)
        bomb.x * this.tileSize + 8,
        bomb.y * this.tileSize + 8
      );
    });
  }

  drawExplodableWall(x: number, y: number): void {
    this.context.drawImage(
      this.explodableWallImage,
      x * this.tileSize,
      y * this.tileSize
    );
  }

  drawSolidWall(x: number, y: number): void {
    this.context.drawImage(
      this.solidWallImage,
      x * this.tileSize,
      y * this.tileSize
    );
  }
}
