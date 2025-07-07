import { Component, Input } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-button',
  imports: [MatButtonModule],
  templateUrl: './button.component.html',
  styleUrl: './button.component.scss',
})
export class ButtonComponent {
  @Input()
  label: string = '';
  @Input()
  disabled = false;
  @Input()
  type: 'button' | 'submit' | 'reset' = 'button';
  @Input()
  form?: string;
}
