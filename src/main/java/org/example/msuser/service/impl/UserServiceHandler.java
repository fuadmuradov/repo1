package org.example.msuser.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.msuser.entity.UserEntity;
import org.example.msuser.exception.NotFoundException;
import org.example.msuser.model.request.UserRequest;
import org.example.msuser.model.response.UserResponse;
import org.example.msuser.repositoy.UserRepository;
import org.example.msuser.service.abstraction.UserService;
import org.example.msuser.util.CacheUtil;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.example.msuser.constants.CacheConstants.MS_USER_USER_TABLE;
import static org.example.msuser.exception.ErrorConstants.USER_NOTFOUND_EXCEPTION;
import static org.example.msuser.mapper.UserMapper.USER_MAPPER;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceHandler implements UserService {
    private final UserRepository userRepository;
    private final CacheUtil cacheUtil;
    @Override
    public void save(UserRequest request) {
        log.info("Saving user started");
        var user = USER_MAPPER.mapToEntity(request);
        user.getAddresses().forEach(it -> it.setUser(user));
        user.setIsDeleted(false);
        log.info("Saving user: {}", user);
        userRepository.save(user);
    }

    @Override
    public void remove(Long id) {
        log.info("Removing user started id: {}", id);
        var user = getUserIfExists(id);
        log.info("Removing user: {}", user);
        userRepository.delete(user);
    }

    @Override
    public UserResponse findUserById(Long id) {
        log.info("Finding user by id: {}", id);
        String cacheKey = MS_USER_USER_TABLE + id;
        UserEntity user;
        try {
            user = cacheUtil.getBucket(cacheKey);
            if (user != null){
                log.info("User found in Redis cache for id: {}", id);
                return USER_MAPPER.mapToResponse(user);
            }
        }catch (Exception e) {
            log.error("Error fetching user from cache for id: {}", e.getMessage(), e);
        }
         user = getUserIfExists(id);
        try {
            cacheUtil.setBucket(cacheKey, user, 1L, ChronoUnit.MINUTES);
            log.info("User cached in Redis for id: {}", id);
        }catch (Exception e){
            log.error("Error storing user in Redis cache for id: {}", id, e);
        }
        return USER_MAPPER.mapToResponse(user);
    }

    @Override
    public List<UserResponse> findAllUsers() {
        log.info("Finding all users");
        var users = userRepository.findAllByIsDeletedFalse();
        return users.stream().map(USER_MAPPER::mapToResponse).toList();
    }

    public UserEntity getUserIfExists(Long id) {
        return userRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(()-> new NotFoundException(USER_NOTFOUND_EXCEPTION.getMessage(),
                        USER_NOTFOUND_EXCEPTION.getCode()));
    }
}
