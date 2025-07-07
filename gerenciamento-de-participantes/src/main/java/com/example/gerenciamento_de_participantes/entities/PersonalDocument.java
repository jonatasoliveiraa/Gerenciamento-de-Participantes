package com.example.gerenciamento_de_participantes.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "tb_personal_document")
public class PersonalDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String documentType;

    @Column(unique = true)
    private String documentNumber;

    @ManyToOne
    @JoinColumn(name = "participant_id")
    private Participant participant;

    public PersonalDocument() {
    }

    public PersonalDocument(Long id, String documentType, String documentNumber, Participant participant) {
        this.id = id;
        this.documentType = documentType;
        this.documentNumber = documentNumber;
        this.participant = participant;
    }

    public Long getId() {
        return id;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public Participant getParticipant() {
        return participant;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PersonalDocument that = (PersonalDocument) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getDocumentNumber(), that.getDocumentNumber());
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(getId());
        result = 31 * result + Objects.hashCode(getDocumentNumber());
        return result;
    }
}
