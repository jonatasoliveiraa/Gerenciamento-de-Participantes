package com.example.gerenciamento_de_participantes.repositories;

import com.example.gerenciamento_de_participantes.entities.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {


}
