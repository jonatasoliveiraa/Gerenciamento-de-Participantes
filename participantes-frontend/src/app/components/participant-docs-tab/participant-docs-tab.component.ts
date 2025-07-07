import { Component, Input } from '@angular/core';
import { CommonModule, NgFor } from '@angular/common';
import { ReactiveFormsModule, FormArray, FormGroup } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';

@Component({
  selector: 'app-participant-docs-tab',
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    NgFor,
  ],
  templateUrl: './participant-docs-tab.component.html',
  styleUrl: './participant-docs-tab.component.scss',
})
export class ParticipantDocsTabComponent {
  @Input() form!: FormGroup;
  @Input() personalDocuments!: FormArray;
}
