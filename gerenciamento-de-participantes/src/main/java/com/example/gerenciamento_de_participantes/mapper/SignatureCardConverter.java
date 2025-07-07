package com.example.gerenciamento_de_participantes.mapper;

import com.example.gerenciamento_de_participantes.dto.SignatureCardDTO;
import com.example.gerenciamento_de_participantes.entities.SignatureCard;

public class SignatureCardConverter {

    public static void copyToDto(SignatureCard entity, SignatureCardDTO dto) {
        entity.setCardNumber(dto.getCardNumber() != null ? dto.getCardNumber() : entity.getCardNumber());
    }
}
