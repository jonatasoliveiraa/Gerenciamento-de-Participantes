package com.example.gerenciamento_de_participantes.dto;

import com.example.gerenciamento_de_participantes.entities.Address;

public class AddressDTO {

    private Long id;
    private String street;
    private String neighborhood;
    private String city;
    private String state;
    private Integer number;
    private String complement;
    private String zipCode;
    private ParticipantDTO participantId;

    public AddressDTO() {
    }

    public AddressDTO(Long id, String street, String neighborhood, String city, String state, Integer number, String complement, String zipCode) {
        this.id = id;
        this.street = street;
        this.neighborhood = neighborhood;
        this.city = city;
        this.state = state;
        this.number = number;
        this.complement = complement;
        this.zipCode = zipCode;
    }

    public AddressDTO(Address entity) {
        id = entity.getId();
        street = entity.getStreet();
        neighborhood = entity.getNeighborhood();
        city = entity.getCity();
        state = entity.getState();
        number = entity.getNumber();
        complement = entity.getComplement();
        zipCode = entity.getZipCode();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

}
