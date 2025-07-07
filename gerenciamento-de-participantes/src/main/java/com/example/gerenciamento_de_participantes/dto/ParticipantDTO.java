package com.example.gerenciamento_de_participantes.dto;

import com.example.gerenciamento_de_participantes.entities.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class ParticipantDTO {

    private Long id;
    private String externalCode;
    private String fullName;
    private String email;
    private String cpf;
    private Gender gender;
    private MaritalStatus maritalStatus;
    private String identificationDocument;
    private String observation;
    private String phone;
    private String cellPhone;
    private LocalDate validityRegisteredForm;
    private LocalDate birthDate;
    private String status;
    private String digitalSignature;

    private Set<AddressDTO> addresses = new HashSet<>();
    private Set<SignatureCardDTO> signatureCards = new HashSet<>();
    private Set<PersonalDocumentDTO> personalDocuments = new HashSet<>();

    public ParticipantDTO() {
    }

    public ParticipantDTO(Long id, String externalCode, String fullName, String email, String cpf, Gender gender, MaritalStatus maritalStatus, String identificationDocument, String observation, String phone, String cellPhone, LocalDate validityRegisteredForm, LocalDate birthDate, boolean isActive, boolean isDigitalSignature) {
        this.id = id;
        this.externalCode = externalCode;
        this.fullName = fullName;
        this.email = email;
        this.cpf = cpf;
        this.gender = gender;
        this.maritalStatus = maritalStatus;
        this.identificationDocument = identificationDocument;
        this.observation = observation;
        this.phone = phone;
        this.cellPhone = cellPhone;
        this.validityRegisteredForm = validityRegisteredForm;
        this.birthDate = birthDate;
        this.status = isActive ? "Ativo" : "Inativo";
        this.digitalSignature = isDigitalSignature ? "Sim" : "Não";
    }

    public ParticipantDTO(Participant entity) {
        id = entity.getId();
        externalCode = entity.getExternalCode();
        fullName = entity.getFullName();
        email = entity.getEmail();
        cpf = entity.getCpf();
        gender = entity.getGender();
        maritalStatus = entity.getMaritalStatus();
        identificationDocument = entity.getIdentificationDocument();
        observation = entity.getObservation();
        phone = entity.getPhone();
        cellPhone = entity.getCellPhone();
        validityRegisteredForm = entity.getValidityRegisteredForm();
        birthDate = entity.getBirthDate();
        status = entity.isActive() ? "Ativo" : "Inativo";
        digitalSignature = entity.isDigitalSignature() ? "Sim" : "Não";
        for (Address address : entity.getAddresses()) {
            addresses.add(new AddressDTO(address));
        }
        for (SignatureCard card : entity.getSignatureCards()) {
            signatureCards.add(new SignatureCardDTO(card));
        }
        for (PersonalDocument document : entity.getPersonalDocuments()) {
            personalDocuments.add(new PersonalDocumentDTO(document));
        }
    }

    public Long getId() {
        return id;
    }

    public String getExternalCode() {
        return externalCode;
    }

    public void setExternalCode(String externalCode) {
        this.externalCode = externalCode;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public MaritalStatus getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(MaritalStatus maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getIdentificationDocument() {
        return identificationDocument;
    }

    public void setIdentificationDocument(String identificationDocument) {
        this.identificationDocument = identificationDocument;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public LocalDate getValidityRegisteredForm() {
        return validityRegisteredForm;
    }

    public void setValidityRegisteredForm(LocalDate validityRegisteredForm) {
        this.validityRegisteredForm = validityRegisteredForm;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDigitalSignature() {
        return digitalSignature;
    }

    public void setDigitalSignature(String digitalSignature) {
        this.digitalSignature = digitalSignature;
    }

    public Set<AddressDTO> getAddresses() {
        return addresses;
    }

    public Set<SignatureCardDTO> getSignatureCards() {
        return signatureCards;
    }

    public Set<PersonalDocumentDTO> getPersonalDocuments() {
        return personalDocuments;
    }

}
