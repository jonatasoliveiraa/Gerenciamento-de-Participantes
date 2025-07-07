import { Component, Input } from '@angular/core';
import { CommonModule, NgFor } from '@angular/common';
import { ReactiveFormsModule, FormArray, FormGroup } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';

@Component({
  selector: 'app-participant-cards-tab',
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    NgFor,
  ],
  templateUrl: './participant-cards-tab.component.html',
  styleUrl: './participant-cards-tab.component.scss',
})
export class ParticipantCardsTabComponent {
  @Input() form!: FormGroup;
  @Input() signatureCards!: FormArray;
}
