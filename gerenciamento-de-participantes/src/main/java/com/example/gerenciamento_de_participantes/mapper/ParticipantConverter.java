package com.example.gerenciamento_de_participantes.mapper;

import com.example.gerenciamento_de_participantes.dto.AddressDTO;
import com.example.gerenciamento_de_participantes.dto.ParticipantDTO;
import com.example.gerenciamento_de_participantes.dto.PersonalDocumentDTO;
import com.example.gerenciamento_de_participantes.dto.SignatureCardDTO;
import com.example.gerenciamento_de_participantes.entities.Address;
import com.example.gerenciamento_de_participantes.entities.Participant;
import com.example.gerenciamento_de_participantes.entities.PersonalDocument;
import com.example.gerenciamento_de_participantes.entities.SignatureCard;

public class ParticipantConverter {

    public static void copyToDto(Participant entity, ParticipantDTO dto) {
        entity.setExternalCode(dto.getExternalCode() != null ? dto.getExternalCode() : entity.getExternalCode());
        entity.setFullName(dto.getFullName() != null ? dto.getFullName() : entity.getFullName());
        entity.setEmail(dto.getEmail() != null ? dto.getEmail() : entity.getEmail());
        entity.setCpf(dto.getCpf() != null ? dto.getCpf() : entity.getCpf());
        entity.setGender(dto.getGender() != null ? dto.getGender() : entity.getGender());
        entity.setMaritalStatus(dto.getMaritalStatus() != null ? dto.getMaritalStatus() : entity.getMaritalStatus());
        entity.setIdentificationDocument(dto.getIdentificationDocument() != null ? dto.getIdentificationDocument() : entity.getIdentificationDocument());
        entity.setObservation(dto.getObservation() != null ? dto.getObservation() : entity.getObservation());
        entity.setPhone(dto.getPhone() != null ? dto.getPhone() : entity.getPhone());
        entity.setCellPhone(dto.getCellPhone() != null ? dto.getCellPhone() : entity.getCellPhone());
        entity.setValidityRegisteredForm(dto.getValidityRegisteredForm() != null ? dto.getValidityRegisteredForm() : entity.getValidityRegisteredForm());
        entity.setBirthDate(dto.getBirthDate() != null ? dto.getBirthDate() : entity.getBirthDate());
        entity.setActive(dto.getStatus() != null ? (dto.getStatus().equals("Ativo")) : entity.isActive());
        entity.setDigitalSignature(dto.getDigitalSignature() != null ? (dto.getDigitalSignature().equals("Sim")) : entity.isActive());
        for (AddressDTO addressDTO : dto.getAddresses()) {
            Address address = new Address();
            AddressConverter.copyToDto(address, addressDTO);
            address.setParticipant(entity);
            entity.addAddress(address);
        }
        for (SignatureCardDTO signatureCardDTO : dto.getSignatureCards()) {
            SignatureCard card = new SignatureCard();
            SignatureCardConverter.copyToDto(card, signatureCardDTO);
            card.setParticipant(entity);
            entity.addSignatureCard(card);
        }
        for (PersonalDocumentDTO personalDocumentDTO : dto.getPersonalDocuments()) {
            PersonalDocument document = new PersonalDocument();
            PersonalDocumentConverter.copyToDto(document, personalDocumentDTO);
            document.setParticipant(entity);
            entity.addPersonalDocument(document);
        }
    }
}
