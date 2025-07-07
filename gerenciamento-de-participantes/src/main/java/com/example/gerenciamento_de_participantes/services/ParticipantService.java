package com.example.gerenciamento_de_participantes.services;

import com.example.gerenciamento_de_participantes.dto.ParticipantDTO;
import com.example.gerenciamento_de_participantes.entities.Participant;
import com.example.gerenciamento_de_participantes.mapper.ParticipantConverter;
import com.example.gerenciamento_de_participantes.repositories.AddressRepository;
import com.example.gerenciamento_de_participantes.repositories.ParticipantRepository;
import com.example.gerenciamento_de_participantes.repositories.PersonalDocumentRepository;
import com.example.gerenciamento_de_participantes.repositories.SignatureCardRepository;
import com.example.gerenciamento_de_participantes.services.exceptions.DatabaseException;
import com.example.gerenciamento_de_participantes.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ParticipantService {

    @Autowired
    private ParticipantRepository participantRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private SignatureCardRepository signatureCardRepository;

    @Autowired
    private PersonalDocumentRepository personalDocumentRepository;

    @Transactional(readOnly = true)
    public Page<ParticipantDTO> findAll(Pageable pageable) {
        return participantRepository.findAll(pageable).map(ParticipantDTO::new);
    }

    @Transactional(readOnly = true)
    public ParticipantDTO findById(Long id) {
        Participant entity = participantRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(id));
        return new ParticipantDTO(entity);
    }

    @Transactional
    public ParticipantDTO createParticipant(ParticipantDTO dto) {
        Participant participant = new Participant();
        ParticipantConverter.copyToDto(participant, dto);
        participantRepository.save(participant);
        addressRepository.saveAll(participant.getAddresses());
        signatureCardRepository.saveAll(participant.getSignatureCards());
        personalDocumentRepository.saveAll(participant.getPersonalDocuments());
        return new ParticipantDTO(participant);
    }

    @Transactional
    public ParticipantDTO updateParticipant(Long id, ParticipantDTO dto) {
        try {
            Participant entity = participantRepository.getReferenceById(id);
            ParticipantConverter.copyToDto(entity, dto);
            return new ParticipantDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void deleteParticipant(Long id) {
        if (!participantRepository.existsById(id)) {
            throw new ResourceNotFoundException(id);
        }
        try {
            participantRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

}
