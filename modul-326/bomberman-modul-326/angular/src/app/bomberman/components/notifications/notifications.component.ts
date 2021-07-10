import { Component, OnInit } from '@angular/core';
import { Store } from '@ngrx/store';
import { BombermanState } from '../../store/models/bomberman-state.model';
import { getNotifications } from '../../store/selectors/bomberman.selectors';

@Component({
  selector: 'app-notifications',
  templateUrl: './notifications.component.html',
  styleUrls: ['./notifications.component.scss'],
})
export class NotificationsComponent implements OnInit {
  notifications: string[];

  constructor(private store: Store<BombermanState>) {}

  ngOnInit(): void {
    this.store.select(getNotifications).subscribe((notifications) => {
      this.notifications = notifications;
    });
  }
}
