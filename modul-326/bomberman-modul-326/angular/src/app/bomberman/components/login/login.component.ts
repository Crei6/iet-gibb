import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent implements OnInit {
  username: string;
  @Input() isInputDisabled = false;

  @Output() onLogin = new EventEmitter<string>();

  constructor() {}

  ngOnInit(): void {}

  login(): void {
    this.isInputDisabled = true;
    this.onLogin.emit(this.username);
  }
}
