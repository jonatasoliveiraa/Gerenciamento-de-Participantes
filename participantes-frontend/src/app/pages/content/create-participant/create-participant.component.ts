import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { NavHeaderComponent } from '../../../components/nav-header/nav-header.component';
import { TitleComponent } from '../../../components/title/title.component';
import { FooterComponent } from '../../../components/footer/footer.component';
import { ParticipantFormComponent } from '../../../components/participant-form/participant-form.component';
import { ButtonComponent } from '../../../components/button/button.component';

@Component({
  selector: 'app-create-participant',
  imports: [
    RouterLink,
    NavHeaderComponent,
    TitleComponent,
    ParticipantFormComponent,
    FooterComponent,
    ButtonComponent,
  ],
  templateUrl: './create-participant.component.html',
  styleUrl: './create-participant.component.scss',
})
export class CreateParticipantComponent {}
