package com.example.gerenciamento_de_participantes.dto;

import com.example.gerenciamento_de_participantes.entities.SignatureCard;

public class SignatureCardDTO {

    private Long id;
    private String cardNumber;

    public SignatureCardDTO() {
    }

    public SignatureCardDTO(Long id, String cardNumber) {
        this.id = id;
        this.cardNumber = cardNumber;
    }

    public SignatureCardDTO(SignatureCard entity) {
        id = entity.getId();
        cardNumber = entity.getCardNumber();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
}
