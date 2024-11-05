package org.example.msuser.service.abstraction;

import org.example.msuser.model.request.UserRequest;
import org.example.msuser.model.response.UserResponse;

import java.util.List;

public interface UserService {
    void save(UserRequest request);
    void remove(Long id);
    UserResponse findUserById(Long id);
    List<UserResponse> findAllUsers();
}
