import { Component, Input } from '@angular/core';
import { CommonModule, NgFor } from '@angular/common';
import { ReactiveFormsModule, FormArray, FormGroup } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';

@Component({
  selector: 'app-participant-addresses-tab',
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    NgFor,
  ],
  templateUrl: './participant-addresses-tab.component.html',
  styleUrl: './participant-addresses-tab.component.scss',
})
export class ParticipantAddressesTabComponent {
  @Input() form!: FormGroup;
  @Input() addresses!: FormArray;
}
