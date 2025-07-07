import { Component, OnInit, Inject } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import {
  MAT_DIALOG_DATA,
  MatDialogRef,
  MatDialogModule,
} from '@angular/material/dialog';
import { ButtonComponent } from '../button/button.component';
import { MatSelectModule } from '@angular/material/select';
import { MatFormFieldModule } from '@angular/material/form-field';
import { PersonalDocument } from '../../models/personalDocument';

@Component({
  selector: 'app-personal-document-modal',
  imports: [
    ReactiveFormsModule,
    MatInputModule,
    MatDialogModule,
    ButtonComponent,
    MatSelectModule,
    ReactiveFormsModule,
    MatFormFieldModule,
  ],
  templateUrl: './personal-document-modal.component.html',
  styleUrl: './personal-document-modal.component.scss',
})
export class PersonalDocumentModalComponent implements OnInit {
  form!: FormGroup;
  constructor(
    private fb: FormBuilder,
    private dialogRef: MatDialogRef<PersonalDocumentModalComponent>,
    @Inject(MAT_DIALOG_DATA) public data: PersonalDocument
  ) {}
  ngOnInit() {
    this.form = this.fb.group({
      id: [this.data.id],
      documentType: [this.data?.documentType || ''],
      documentNumber: [this.data?.documentNumber || ''],
    });
  }

  cancel() {
    this.dialogRef.close();
  }

  save() {
    if (this.form.invalid) return;
    this.dialogRef.close(this.form.value as PersonalDocument);
  }
}
