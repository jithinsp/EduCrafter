package com.corner.user.service.user;

import com.corner.user.dto.RegisterParentRequest;
import com.corner.user.dto.RegisterStudentRequest;
import com.corner.user.dto.RegisterTeacherRequest;
import com.corner.user.entity.Role;
import com.corner.user.entity.UserEntity;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface UserCredentialService {
    UserEntity addAdmin(RegisterStudentRequest registerStudentRequest);
    UserEntity addParent(RegisterParentRequest registerParentRequest);
    UserEntity addTeacher(RegisterTeacherRequest registerTeacherRequest);

    List<UserEntity> getAllByRole(Role role);

    public String extractUsername(HttpServletRequest request);

    UserEntity getUserByEmail(String username);
}