package org.example.msuser.mapper;

import org.example.msuser.entity.AddressEntity;
import org.example.msuser.model.request.AddressRequest;
import org.example.msuser.model.response.AddressResponse;


public enum AddressMapper {
    ADDRESS_MAPPER;

    public AddressEntity mapToEntity(AddressRequest request) {
        return AddressEntity.builder()
                .city(request.getCity())
                .country(request.getCountry())
                .street(request.getStreet())
                .postalCode(request.getPostalCode())
                .build();
    }

    public AddressResponse mapToResponse(AddressEntity entity) {
        return AddressResponse.builder()
                .id(entity.getId())
                .city(entity.getCity())
                .country(entity.getCountry())
                .street(entity.getStreet())
                .postalCode(entity.getPostalCode())
                .build();
    }
}
