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
import { Participant } from '../../models/participant';
import { ButtonComponent } from '../button/button.component';
import { ParticipantService } from '../../services/participant.service';
import { Gender } from '../../enums/gender.enum';
import { MaritalStatus } from '../../enums/marital-status.enum';
import { MatSelectModule } from '@angular/material/select';
import { ParticipantDataTabComponent } from '../participant-data-tab/participant-data-tab.component';
@Component({
  selector: 'app-modal',
  imports: [
    ReactiveFormsModule,
    MatInputModule,
    MatDialogModule,
    ButtonComponent,
    MatSelectModule,
    ParticipantDataTabComponent,
  ],
  templateUrl: './modal.component.html',
  styleUrl: './modal.component.scss',
})
export class ModalComponent implements OnInit {
  form!: FormGroup;
  genderOptions = Gender;
  maritalStatusOptions = MaritalStatus;

  constructor(
    private fb: FormBuilder,
    private service: ParticipantService,
    private dialogRef: MatDialogRef<ModalComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Participant
  ) {}

  ngOnInit() {
    this.createParticipantForm();
  }

  private createParticipantForm(): FormGroup {
    return (this.form = this.fb.group({
      externalCode: [this.data.externalCode],
      fullName: [this.data.fullName, Validators.required],
      email: [this.data.email, [Validators.email]],
      cpf: [this.data.cpf, Validators.required],
      gender: [null],
      maritalStatus: [null],
      identificationDocument: [this.data.identificationDocument],
      observation: [this.data.observation],
      phone: [this.data.phone],
      cellPhone: [this.data.cellPhone],
      validityRegisteredForm: [this.data.validityRegisteredForm],
      birthDate: [this.data.birthDate],
      status: [false],
      digitalSignature: [false],
    }));
  }

  cancel(): void {
    this.dialogRef.close();
  }

  update(): void {
    if (this.form.invalid) return;

    const raw = this.form.value;

    const payload = {
      ...raw,
      status: raw.status ? 'Ativo' : 'Inativo',
      digitalSignature: raw.digitalSignature ? 'Sim' : 'Não',
    };

    this.service.update(this.data.id, payload).subscribe({
      next: (updated: Participant) => this.dialogRef.close(updated),
      error: (err) => console.error('Erro ao atualizar:', err),
    });
  }
}
