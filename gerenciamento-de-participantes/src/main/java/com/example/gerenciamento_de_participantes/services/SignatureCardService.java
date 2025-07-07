package com.example.gerenciamento_de_participantes.services;

import com.example.gerenciamento_de_participantes.dto.SignatureCardDTO;
import com.example.gerenciamento_de_participantes.entities.Participant;
import com.example.gerenciamento_de_participantes.entities.SignatureCard;
import com.example.gerenciamento_de_participantes.mapper.SignatureCardConverter;
import com.example.gerenciamento_de_participantes.repositories.ParticipantRepository;
import com.example.gerenciamento_de_participantes.repositories.SignatureCardRepository;
import com.example.gerenciamento_de_participantes.services.exceptions.DatabaseException;
import com.example.gerenciamento_de_participantes.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SignatureCardService {

    @Autowired
    private ParticipantRepository participantRepository;

    @Autowired
    private SignatureCardRepository signatureCardRepository;

    @Transactional(readOnly = true)
    public SignatureCardDTO findById(Long id) {
        SignatureCard entity = signatureCardRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(id));
        return new SignatureCardDTO(entity);
    }

    @Transactional
    public SignatureCardDTO insertSignatureCard(Long id, SignatureCardDTO dto) {
        Participant participant = participantRepository.getReferenceById(id);
        SignatureCard signatureCard = new SignatureCard();
        SignatureCardConverter.copyToDto(signatureCard, dto);
        signatureCard.setParticipant(participant);
        signatureCardRepository.save(signatureCard);
        participant.addSignatureCard(signatureCard);
        return new SignatureCardDTO(signatureCard);
    }

    @Transactional
    public SignatureCardDTO updateSignatureCard(Long id, SignatureCardDTO dto) {
        try {
            SignatureCard entity = signatureCardRepository.getReferenceById(id);
            SignatureCardConverter.copyToDto(entity, dto);
            return new SignatureCardDTO(signatureCardRepository.save(entity));
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void deleteSignatureCard(Long id) {
        if (!signatureCardRepository.existsById(id)) {
            throw new ResourceNotFoundException(id);
        }
        try {
            signatureCardRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

}
