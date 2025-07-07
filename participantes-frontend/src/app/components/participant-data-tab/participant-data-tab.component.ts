import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormGroup } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { Gender } from '../../enums/gender.enum';
import { MaritalStatus } from '../../enums/marital-status.enum';
import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'app-participant-data-tab',
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatCheckboxModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatIconModule,
  ],
  templateUrl: './participant-data-tab.component.html',
  styleUrl: './participant-data-tab.component.scss',
})
export class ParticipantDataTabComponent {
  @Input() form!: FormGroup;
  genderOptions = Gender;
  maritalStatusOptions = MaritalStatus;
}
