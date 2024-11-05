package org.example.msuser.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class UserRequest {
@NotBlank(message = "name can not be empty")
    private String name;
    private String surname;
    @Email()
    private String email;
    private String phone;
    private List<AddressRequest> addresses;
}
