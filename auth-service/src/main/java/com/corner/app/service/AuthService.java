package com.corner.app.service;

import com.corner.app.dto.SignupRequest;
import com.corner.app.entity.UserCredential;

public interface AuthService {
    UserCredential createUser(SignupRequest signupRequest);
}
