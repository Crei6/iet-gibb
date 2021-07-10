import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { BombermanComponent } from './containers/bomberman/bomberman.component';
import { HighscoreComponent } from './containers/highscore/highscore.component';

const routes: Routes = [
  {
    path: 'bomberman',
    component: BombermanComponent,
  },
  {
    path: 'bomberman/highscore',
    component: HighscoreComponent,
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class BombermanRoutingModule {}
