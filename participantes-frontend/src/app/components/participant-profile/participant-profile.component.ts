import { Component, OnInit, viewChild, inject, signal } from '@angular/core';
import { CommonModule, NgFor } from '@angular/common';
import { ParticipantService } from '../../services/participant.service';
import { Participant } from '../../models/participant';
import { provideNativeDateAdapter } from '@angular/material/core';
import { MatAccordion, MatExpansionModule } from '@angular/material/expansion';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { ActivatedRoute } from '@angular/router';
import { switchMap, map } from 'rxjs/operators';
import { ButtonComponent } from '../button/button.component';
import { FormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatDialog } from '@angular/material/dialog';
import { AddressService } from '../../services/address.service';
import { SignatureCardService } from '../../services/signature-card.service';
import { PersonalDocumentService } from '../../services/personal-document.service';
import { AddressModalComponent } from '../address-modal/address-modal.component';
import { Address } from '../../models/address';
import { SignatureCard } from '../../models/signatureCard';
import { SignatureCardModalComponent } from '../signature-card-modal/signature-card-modal.component';
import { PersonalDocumentModalComponent } from '../personal-document-modal/personal-document-modal.component';
import { PersonalDocument } from '../../models/personalDocument';
import { ParticipantDataModalComponent } from '../participant-data-modal/participant-data-modal.component';
@Component({
  selector: 'app-participant-profile',
  standalone: true,
  providers: [provideNativeDateAdapter()],
  imports: [
    CommonModule,
    MatExpansionModule,
    MatIconModule,
    MatListModule,
    NgFor,
    ButtonComponent,
    MatFormFieldModule,
    MatInputModule,
    FormsModule,
  ],
  templateUrl: './participant-profile.component.html',
  styleUrl: './participant-profile.component.scss',
})
export class ParticipantProfileComponent implements OnInit {
  accordion = viewChild.required(MatAccordion);
  participant?: Participant;
  address?: Address;
  step = signal(0);
  readonly dialog = inject(MatDialog);

  constructor(
    private route: ActivatedRoute,
    private participantService: ParticipantService,
    private addressService: AddressService,
    private cardService: SignatureCardService,
    private docService: PersonalDocumentService
  ) {}

  ngOnInit() {
    this.route.paramMap
      .pipe(
        map((param) => Number(param.get('id'))),
        switchMap((id) => this.participantService.getById(id))
      )
      .subscribe((profile) => {
        this.participant = profile;
      });
  }

  openEditDataModal(): void {
    const dialogRef = this.dialog.open(ParticipantDataModalComponent, {
      data: this.participant,
    });

    dialogRef.afterClosed().subscribe((updateData: Participant) => {
      if (updateData !== undefined) {
        this.participant = updateData;
      }
    });
  }

  openNewAddressModal() {
    const ref = this.dialog.open(AddressModalComponent, {
      data: {} as Address,
    });

    ref.afterClosed().subscribe((newAddress: Address) => {
      if (!newAddress) return;

      this.addressService.create(this.participant!.id, newAddress).subscribe({
        next: (address: Address) => {
          this.participant!.addresses.push(address);
        },
        error: (err) => {
          console.error('Erro ao inserir endereço:', err);
        },
      });
    });
  }

  openEditAddressModal(address: Address) {
    const dialogRef = this.dialog.open(AddressModalComponent, {
      data: address,
    });
    dialogRef.afterClosed().subscribe((updatedAddress: Address) => {
      if (!updatedAddress) return;

      this.addressService
        .update(updatedAddress.id, updatedAddress)
        .subscribe((addressDto) => {
          const index = this.participant!.addresses.findIndex(
            (address) => address.id === addressDto.id
          );
          this.participant!.addresses[index] = addressDto;
        });
    });
  }

  onDeleteAddress(addressId: number) {
    if (!confirm('Tem certeza que deseja excluir este endereço?')) {
      return;
    }
    this.addressService.delete(addressId).subscribe({
      next: () => {
        this.participant!.addresses = this.participant!.addresses.filter(
          (address) => address.id !== addressId
        );
      },
      error: (err) => {
        console.error('Erro ao excluir endereço', err);
        alert('Não foi possível excluir o endereço.');
      },
    });
  }

  openNewCardModal() {
    const ref = this.dialog.open(SignatureCardModalComponent, {
      data: {} as SignatureCard,
    });

    ref.afterClosed().subscribe((newCard: SignatureCard) => {
      if (!newCard) return;

      this.cardService.create(this.participant!.id, newCard).subscribe({
        next: (card: SignatureCard) => {
          this.participant!.signatureCards.push(card);
        },
        error: (err) => {
          console.error('Erro ao inserir cartão de assinatura:', err);
        },
      });
    });
  }

  openEditCardModal(card: SignatureCard) {
    const dialogRef = this.dialog.open(SignatureCardModalComponent, {
      data: card,
    });
    dialogRef.afterClosed().subscribe((updatedCard: SignatureCard) => {
      if (!updatedCard) return;

      this.cardService
        .update(updatedCard.id, updatedCard)
        .subscribe((cardDto) => {
          const index = this.participant!.signatureCards.findIndex(
            (card) => card.id === cardDto.id
          );
          this.participant!.signatureCards[index] = cardDto;
        });
    });
  }

  onDeleteCard(cardId: number) {
    if (!confirm('Tem certeza que deseja excluir este cartão?')) {
      return;
    }
    this.cardService.delete(cardId).subscribe({
      next: () => {
        this.participant!.signatureCards =
          this.participant!.signatureCards.filter((card) => card.id !== cardId);
      },
      error: (err) => {
        console.error('Erro ao excluir cartão', err);
        alert('Não foi possível excluir o cartão.');
      },
    });
  }

  openNewDocumentModal() {
    const ref = this.dialog.open(PersonalDocumentModalComponent, {
      data: {} as PersonalDocument,
    });

    ref.afterClosed().subscribe((newDocument: PersonalDocument) => {
      if (!newDocument) return;

      this.docService.create(this.participant!.id, newDocument).subscribe({
        next: (document: PersonalDocument) => {
          this.participant!.personalDocuments.push(document);
        },
        error: (err) => {
          console.error('Erro ao inserir documento:', err);
        },
      });
    });
  }

  openEditDocumentModal(document: PersonalDocument) {
    const dialogRef = this.dialog.open(PersonalDocumentModalComponent, {
      data: document,
    });
    dialogRef.afterClosed().subscribe((documentCard: PersonalDocument) => {
      if (!documentCard) return;

      this.docService
        .update(documentCard.id, documentCard)
        .subscribe((documentDto) => {
          const index = this.participant!.personalDocuments.findIndex(
            (document) => document.id === documentDto.id
          );
          this.participant!.personalDocuments[index] = documentDto;
        });
    });
  }

  onDeleteDocument(documentId: number) {
    if (!confirm('Tem certeza que deseja excluir este documento?')) {
      return;
    }
    this.docService.delete(documentId).subscribe({
      next: () => {
        this.participant!.personalDocuments =
          this.participant!.personalDocuments.filter(
            (document) => document.id !== documentId
          );
      },
      error: (err) => {
        console.error('Erro ao excluir documento', err);
        alert('Não foi possível excluir o documento.');
      },
    });
  }

  setStep(index: number) {
    this.step.set(index);
  }

  nextStep() {
    this.step.update((i) => i + 1);
  }

  prevStep() {
    this.step.update((i) => i - 1);
  }
}
