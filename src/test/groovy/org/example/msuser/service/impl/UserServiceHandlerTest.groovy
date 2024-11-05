package org.example.msuser.service.impl;

import io.github.benas.randombeans.EnhancedRandomBuilder;
import io.github.benas.randombeans.api.EnhancedRandom;
import org.example.msuser.repositoy.UserRepository;
import org.example.msuser.util.CacheUtil;
import spock.lang.Specification;

class UserServiceHandlerTest extends Specification {
    EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()
    UserRepository userRepository
    CacheUtil cacheUtil
    UserServiceHandler userServiceHandler
    def setup() {
        userRepository = Mock()
        cacheUtil = Mock()
        userServiceHandler = new UserServiceHandler(userRepository, cacheUtil)
    }

}
