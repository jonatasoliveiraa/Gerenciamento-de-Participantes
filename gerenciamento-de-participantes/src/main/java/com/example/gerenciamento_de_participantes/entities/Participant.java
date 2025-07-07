package com.example.gerenciamento_de_participantes.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_participant")
public class Participant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20)
    private String externalCode;

    @Column(nullable = false)
    private String fullName;

    @Column(length = 100, nullable = false)
    private String email;

    @Column(length = 14, unique = true, nullable = false)
    private String cpf;

    private Gender gender;

    private MaritalStatus maritalStatus;

    @Column(length = 50)
    private String identificationDocument;

    @Column(columnDefinition = "TEXT")
    private String observation;

    @Column(length = 20)
    private String phone;

    @Column(length = 20)
    private String cellPhone;

    private LocalDate validityRegisteredForm;
    private LocalDate birthDate;

    private boolean active;

    private boolean digitalSignature;

    @OneToMany(mappedBy = "participant", cascade = CascadeType.ALL)
    private Set<Address> addresses = new HashSet<>();

    @OneToMany(mappedBy = "participant", cascade = CascadeType.ALL)
    private Set<SignatureCard> signatureCards = new HashSet<>();

    @OneToMany(mappedBy = "participant", cascade = CascadeType.ALL)
    private Set<PersonalDocument> personalDocuments = new HashSet<>();

    public Participant() {
    }

    public Participant(Long id, String externalCode, String fullName, String email, String cpf, Gender gender,
                       MaritalStatus maritalStatus, String identificationDocument, String observation, String phone,
                       String cellPhone, LocalDate validityRegisteredForm, LocalDate birthDate) {
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isDigitalSignature() {
        return digitalSignature;
    }

    public void setDigitalSignature(boolean digitalSignature) {
        this.digitalSignature = digitalSignature;
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    public void addAddress(Address address) {
        addresses.add(address);
    }

    public Set<SignatureCard> getSignatureCards() {
        return signatureCards;
    }

    public void addSignatureCard(SignatureCard signatureCard) {
        signatureCards.add(signatureCard);
    }

    public Set<PersonalDocument> getPersonalDocuments() {
        return personalDocuments;
    }

    public void addPersonalDocument(PersonalDocument document) {
        personalDocuments.add(document);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Participant participant = (Participant) o;
        return Objects.equals(getId(), participant.getId()) && Objects.equals(getCpf(), participant.getCpf());
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(getId());
        result = 31 * result + Objects.hashCode(getCpf());
        return result;
    }
}
