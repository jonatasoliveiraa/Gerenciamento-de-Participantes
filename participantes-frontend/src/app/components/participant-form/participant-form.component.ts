import { ChangeDetectionStrategy, Component, OnInit } from '@angular/core';
import {
  FormsModule,
  FormBuilder,
  FormGroup,
  Validators,
  ReactiveFormsModule,
  FormArray,
} from '@angular/forms';
import { MatIconModule } from '@angular/material/icon';
import { MatTabsModule } from '@angular/material/tabs';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { ParticipantService } from '../../services/participant.service';
import { HttpErrorResponse } from '@angular/common/http';
import { Gender } from '../../enums/gender.enum';
import { MaritalStatus } from '../../enums/marital-status.enum';
import { ParticipantDataTabComponent } from '../participant-data-tab/participant-data-tab.component';
import { ParticipantAddressesTabComponent } from '../participant-addresses-tab/participant-addresses-tab.component';
import { ParticipantCardsTabComponent } from '../participant-cards-tab/participant-cards-tab.component';
import { ParticipantDocsTabComponent } from '../participant-docs-tab/participant-docs-tab.component';
import {
  MatSnackBar,
  MatSnackBarHorizontalPosition,
  MatSnackBarVerticalPosition,
} from '@angular/material/snack-bar';
import { Router } from '@angular/router';
@Component({
  selector: 'app-participant-form',
  imports: [
    FormsModule,
    ReactiveFormsModule,
    MatTabsModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatCheckboxModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatIconModule,
    ParticipantDataTabComponent,
    ParticipantAddressesTabComponent,
    ParticipantCardsTabComponent,
    ParticipantDocsTabComponent,
  ],
  templateUrl: './participant-form.component.html',
  styleUrl: './participant-form.component.scss',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class ParticipantFormComponent implements OnInit {
  form!: FormGroup;
  genderOptions = Gender;
  maritalStatusOptions = MaritalStatus;
  horizontalPosition: MatSnackBarHorizontalPosition = 'end';
  verticalPosition: MatSnackBarVerticalPosition = 'top';

  constructor(
    private fb: FormBuilder,
    private service: ParticipantService,
    private snackbar: MatSnackBar,
    private route: Router
  ) {}

  ngOnInit() {
    this.createParticipantForm();
  }

  private createParticipantForm(): FormGroup {
    return (this.form = this.fb.group({
      externalCode: [''],
      fullName: ['', Validators.required],
      email: ['', [Validators.email]],
      cpf: ['', Validators.required],
      gender: [null],
      maritalStatus: [null],
      identificationDocument: [''],
      observation: [''],
      phone: [''],
      cellPhone: [''],
      validityRegisteredForm: [''],
      birthDate: [''],
      addresses: this.fb.array([this.createAddressGroup()]),
      signatureCards: this.fb.array([this.createSignatureCardsGroup()]),
      personalDocuments: this.fb.array([this.createPersonalDocumentsGroup()]),
      status: [true],
      digitalSignature: [false],
    }));
  }

  private createAddressGroup(): FormGroup {
    return this.fb.group({
      street: [''],
      neighborhood: [''],
      city: [''],
      state: [''],
      number: [''],
      complement: [''],
      zipCode: [''],
    });
  }

  private createSignatureCardsGroup(): FormGroup {
    return this.fb.group({
      cardNumber: [''],
    });
  }

  private createPersonalDocumentsGroup(): FormGroup {
    return this.fb.group({
      documentType: [''],
      documentNumber: [''],
    });
  }

  get addresses() {
    return this.form.get('addresses') as FormArray;
  }

  get signatureCards() {
    return this.form.get('signatureCards') as FormArray;
  }

  get personalDocuments() {
    return this.form.get('personalDocuments') as FormArray;
  }

  onSubmit() {
    if (this.form.invalid) {
      return;
    }
    const raw = this.form.value;

    const payload = {
      ...raw,
      status: raw.status ? 'Ativo' : 'Inativo',
      digitalSignature: raw.digitalSignature ? 'Sim' : 'Não',
    };

    this.service.create(payload).subscribe({
      next: () => {
        this.snackbar.open('Participante criado com sucesso!', 'Fechar', {
          horizontalPosition: this.horizontalPosition,
          verticalPosition: this.verticalPosition,
          duration: 3000,
        });
        this.form.reset();
        this.route.navigate(['']);
      },
      error: (error: HttpErrorResponse) => {
        if (error.status === 409) {
          this.snackbar.open('Este participante já existe.', 'Fechar', {
            duration: 3000,
          });
        } else {
          this.snackbar.open('Erro ao criar participante.', 'Fechar', {
            duration: 3000,
          });
        }
      },
    });
  }
}
