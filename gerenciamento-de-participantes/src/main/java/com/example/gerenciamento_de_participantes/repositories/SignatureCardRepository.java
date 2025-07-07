package com.example.gerenciamento_de_participantes.repositories;

import com.example.gerenciamento_de_participantes.entities.SignatureCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SignatureCardRepository extends JpaRepository<SignatureCard, Long> {
}
