import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BombermanRoutingModule } from './bomberman-routing.module';
import { BombermanComponent } from './containers/bomberman/bomberman.component';
import { LoginComponent } from './components/login/login.component';
import { GameComponent } from './components/game/game.component';
import { NotificationsComponent } from './components/notifications/notifications.component';
import { StoreModule } from '@ngrx/store';
import { BombermanReducer } from './store/reducers/bomberman.reducers';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import { FormsModule } from '@angular/forms';
import { HighscoreComponent } from './containers/highscore/highscore.component';
import { MatDividerModule } from '@angular/material/divider';
import { MatListModule } from '@angular/material/list';

@NgModule({
  declarations: [
    BombermanComponent,
    LoginComponent,
    GameComponent,
    NotificationsComponent,
    HighscoreComponent,
  ],
  imports: [
    CommonModule,
    FormsModule,
    BombermanRoutingModule,
    StoreModule.forFeature('bomberman', BombermanReducer),

    // Material
    MatCardModule,
    MatButtonModule,
    MatInputModule,
    MatDividerModule,
    MatListModule,
  ],
})
export class BombermanModule {}
