import { Component, OnInit, Inject } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import {
  MAT_DIALOG_DATA,
  MatDialogRef,
  MatDialogModule,
} from '@angular/material/dialog';
import { ButtonComponent } from '../button/button.component';
import { MatSelectModule } from '@angular/material/select';
import { Address } from '../../models/address';
import { MatFormFieldModule } from '@angular/material/form-field';

@Component({
  selector: 'app-address-modal',
  imports: [
    ReactiveFormsModule,
    MatInputModule,
    MatDialogModule,
    ButtonComponent,
    MatSelectModule,
    ReactiveFormsModule,
    MatFormFieldModule,
  ],
  templateUrl: './address-modal.component.html',
  styleUrl: './address-modal.component.scss',
})
export class AddressModalComponent implements OnInit {
  form!: FormGroup;
  constructor(
    private fb: FormBuilder,
    private dialogRef: MatDialogRef<AddressModalComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Address
  ) {}
  ngOnInit() {
    this.form = this.fb.group({
      id: [this.data.id],
      street: [this.data?.street || '', Validators.required],
      neighborhood: [this.data?.neighborhood || ''],
      city: [this.data?.city || ''],
      state: [this.data?.state || ''],
      number: [this.data?.number || ''],
      complement: [this.data?.complement || ''],
      zipCode: [this.data?.zipCode || ''],
    });
  }

  cancel() {
    this.dialogRef.close();
  }

  save() {
    if (this.form.invalid) return;
    this.dialogRef.close(this.form.value as Address);
  }
}
