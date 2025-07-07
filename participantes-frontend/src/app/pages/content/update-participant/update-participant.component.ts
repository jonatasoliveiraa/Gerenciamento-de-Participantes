import { Component } from '@angular/core';
import { NavHeaderComponent } from '../../../components/nav-header/nav-header.component';
import { FooterComponent } from '../../../components/footer/footer.component';
import { ButtonComponent } from '../../../components/button/button.component';
import { RouterLink } from '@angular/router';
import { ParticipantProfileComponent } from '../../../components/participant-profile/participant-profile.component';
import { TitleComponent } from '../../../components/title/title.component';

@Component({
  selector: 'app-update-participant',
  imports: [
    RouterLink,
    NavHeaderComponent,
    FooterComponent,
    ButtonComponent,
    ParticipantProfileComponent,
    TitleComponent,
  ],
  templateUrl: './update-participant.component.html',
  styleUrl: './update-participant.component.scss',
})
export class UpdateParticipantComponent {}
