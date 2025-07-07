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
import { SignatureCard } from '../../models/signatureCard';
@Component({
  selector: 'app-signature-card-modal',
  imports: [
    ReactiveFormsModule,
    MatInputModule,
    MatDialogModule,
    ButtonComponent,
    MatSelectModule,
    ReactiveFormsModule,
    MatFormFieldModule,
  ],
  templateUrl: './signature-card-modal.component.html',
  styleUrl: './signature-card-modal.component.scss',
})
export class SignatureCardModalComponent implements OnInit {
  form!: FormGroup;
  constructor(
    private fb: FormBuilder,
    private dialogRef: MatDialogRef<SignatureCardModalComponent>,
    @Inject(MAT_DIALOG_DATA) public data: SignatureCard
  ) {}
  ngOnInit() {
    this.form = this.fb.group({
      id: [this.data.id],
      cardNumber: [this.data?.cardNumber || ''],
    });
  }

  cancel() {
    this.dialogRef.close();
  }

  save() {
    if (this.form.invalid) return;
    this.dialogRef.close(this.form.value as SignatureCard);
  }
}
