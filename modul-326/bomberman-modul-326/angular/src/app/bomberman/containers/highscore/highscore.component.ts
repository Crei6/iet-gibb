import { Component, OnInit } from '@angular/core';
import { Store } from '@ngrx/store';
import { BombermanState } from '../../store/models/bomberman-state.model';
import {
  getHighscoreList,
  getWinner,
} from '../../store/selectors/bomberman.selectors';

@Component({
  selector: 'app-highscore',
  templateUrl: './highscore.component.html',
  styleUrls: ['./highscore.component.scss'],
})
export class HighscoreComponent implements OnInit {
  winnerName: string;
  highscoreList: string[];

  constructor(private store: Store<BombermanState>) {}

  ngOnInit(): void {
    this.store.select(getWinner).subscribe((winner) => {
      this.winnerName = winner;
    });
    this.store.select(getHighscoreList).subscribe((list) => {
      this.highscoreList = list;
    });
  }
}
