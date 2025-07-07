import { Component, ViewChild } from '@angular/core';
import { NavHeaderComponent } from '../../components/nav-header/nav-header.component';
import { TitleComponent } from '../../components/title/title.component';
import { FooterComponent } from '../../components/footer/footer.component';
import { Participant } from '../../models/participant';
import { ParticipantService } from '../../services/participant.service';
import { forkJoin } from 'rxjs';
import { ButtonComponent } from '../../components/button/button.component';
import { RouterLink } from '@angular/router';
import { ParticipantTableDataComponent } from '../../components/participant-table-data/participant-table-data.component';

@Component({
  selector: 'app-home',
  imports: [
    RouterLink,
    NavHeaderComponent,
    TitleComponent,
    FooterComponent,
    ButtonComponent,
    ParticipantTableDataComponent,
  ],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss',
})
export class HomeComponent {
  @ViewChild('list')
  list!: ParticipantTableDataComponent;
  selectedItems: Participant[] = [];

  constructor(private service: ParticipantService) {}

  handleSelection(items: Participant[]) {
    this.selectedItems = items;
  }

  deleteSelected() {
    if (!confirm('Tem certeza que deseja excluir este participante?')) {
      return;
    }
    const calls = this.selectedItems.map((participant) =>
      this.service.delete(participant.id)
    );
    forkJoin(calls).subscribe(() => {
      this.list.loadPage();
      this.selectedItems = [];
    });
  }
}
