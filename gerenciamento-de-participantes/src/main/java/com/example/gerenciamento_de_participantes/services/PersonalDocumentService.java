package com.example.gerenciamento_de_participantes.services;

import com.example.gerenciamento_de_participantes.dto.PersonalDocumentDTO;
import com.example.gerenciamento_de_participantes.entities.Participant;
import com.example.gerenciamento_de_participantes.entities.PersonalDocument;
import com.example.gerenciamento_de_participantes.mapper.PersonalDocumentConverter;
import com.example.gerenciamento_de_participantes.repositories.ParticipantRepository;
import com.example.gerenciamento_de_participantes.repositories.PersonalDocumentRepository;
import com.example.gerenciamento_de_participantes.services.exceptions.DatabaseException;
import com.example.gerenciamento_de_participantes.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonalDocumentService {

    @Autowired
    private ParticipantRepository participantRepository;

    @Autowired
    private PersonalDocumentRepository personalDocumentRepository;


    @Transactional(readOnly = true)
    public PersonalDocumentDTO findById(Long id) {
        PersonalDocument entity = personalDocumentRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(id));
        return new PersonalDocumentDTO(entity);
    }

    @Transactional
    public PersonalDocumentDTO insertPersonalDocument(Long id, PersonalDocumentDTO dto) {
        Participant participant = participantRepository.getReferenceById(id);
        PersonalDocument personalDocument = new PersonalDocument();
        PersonalDocumentConverter.copyToDto(personalDocument, dto);
        personalDocument.setParticipant(participant);
        personalDocumentRepository.save(personalDocument);
        participant.addPersonalDocument(personalDocument);
        return new PersonalDocumentDTO(personalDocument);
    }

    @Transactional
    public PersonalDocumentDTO updatePersonalDocument(Long id, PersonalDocumentDTO dto) {
        try {
            PersonalDocument entity = personalDocumentRepository.getReferenceById(id);
            PersonalDocumentConverter.copyToDto(entity, dto);
            return new PersonalDocumentDTO(personalDocumentRepository.save(entity));
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void deletePersonalDocument(Long id) {
        if (!personalDocumentRepository.existsById(id)) {
            throw new ResourceNotFoundException(id);
        }
        try {
            personalDocumentRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

}
