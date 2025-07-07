package com.example.gerenciamento_de_participantes.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_signature_card")
public class SignatureCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cardNumber;

    @ManyToOne
    @JoinColumn(name = "participant_id")
    private Participant participant;

    public SignatureCard() {
    }

    public SignatureCard(Long id, String cardNumber, Participant participant) {
        this.id = id;
        this.cardNumber = cardNumber;
        this.participant = participant;
    }

    public Long getId() {
        return id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Participant getParticipant() {
        return participant;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }
}
