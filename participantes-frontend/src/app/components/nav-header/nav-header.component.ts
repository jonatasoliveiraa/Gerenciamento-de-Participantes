import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-nav-header',
  imports: [],
  templateUrl: './nav-header.component.html',
  styleUrl: './nav-header.component.scss',
})
export class NavHeaderComponent {
  @Input()
  complement: string = '';
}
