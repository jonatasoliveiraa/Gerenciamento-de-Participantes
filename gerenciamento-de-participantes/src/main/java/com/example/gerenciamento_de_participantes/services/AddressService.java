package com.example.gerenciamento_de_participantes.services;

import com.example.gerenciamento_de_participantes.dto.AddressDTO;
import com.example.gerenciamento_de_participantes.entities.Address;
import com.example.gerenciamento_de_participantes.entities.Participant;
import com.example.gerenciamento_de_participantes.mapper.AddressConverter;
import com.example.gerenciamento_de_participantes.repositories.AddressRepository;
import com.example.gerenciamento_de_participantes.repositories.ParticipantRepository;
import com.example.gerenciamento_de_participantes.services.exceptions.DatabaseException;
import com.example.gerenciamento_de_participantes.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AddressService {

    @Autowired
    private ParticipantRepository participantRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Transactional(readOnly = true)
    public AddressDTO findById(Long id) {
        Address entity = addressRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(id));
        return new AddressDTO(entity);
    }

    @Transactional
    public AddressDTO insertAddress(Long id, AddressDTO dto) {
        Participant participant = participantRepository.getReferenceById(id);
        Address address = new Address();
        AddressConverter.copyToDto(address, dto);
        address.setParticipant(participant);
        addressRepository.save(address);
        participant.addAddress(address);
        return new AddressDTO(address);
    }

    @Transactional
    public AddressDTO updateAddress(Long id, AddressDTO dto) {
        try {
            Address entity = addressRepository.getReferenceById(id);
            AddressConverter.copyToDto(entity, dto);
            return new AddressDTO(addressRepository.save(entity));
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void deleteAddress(Long id) {
        if (!addressRepository.existsById(id)) {
            throw new ResourceNotFoundException(id);
        }
        try {
            addressRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

}
