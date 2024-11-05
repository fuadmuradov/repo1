package org.example.msuser.model.request;

import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.msuser.entity.UserEntity;

@Getter
@Setter
@Builder
public class AddressRequest {
    private String street;
    private String city;
    private String country;
    private String postalCode;
    private Long userId;
}
