import { Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { CreateParticipantComponent } from './pages/content/create-participant/create-participant.component';
import { UpdateParticipantComponent } from './pages/content/update-participant/update-participant.component';

export const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
  },
  {
    path: 'create',
    component: CreateParticipantComponent,
  },
  {
    path: 'profile/:id',
    component: UpdateParticipantComponent,
  },
];
