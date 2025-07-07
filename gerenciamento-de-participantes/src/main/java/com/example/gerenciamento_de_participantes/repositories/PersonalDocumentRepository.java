package com.example.gerenciamento_de_participantes.repositories;

import com.example.gerenciamento_de_participantes.entities.PersonalDocument;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalDocumentRepository extends JpaRepository<PersonalDocument, Long> {
}
