import { Gender } from '../enums/gender.enum';
import { MaritalStatus } from '../enums/marital-status.enum';
import { Address } from './address';
import { PersonalDocument } from './personalDocument';
import { SignatureCard } from './signatureCard';

export interface Participant {
  id: number;
  externalCode: string;
  fullName: string;
  email: string;
  cpf: string;
  gender: Gender;
  maritalStatus: MaritalStatus;
  identificationDocument: string;
  observation: string;
  phone: string;
  cellPhone: string;
  validityRegisteredForm: string;
  birthDate: string;
  addresses: Address[];
  signatureCards: SignatureCard[];
  personalDocuments: PersonalDocument[];
}
