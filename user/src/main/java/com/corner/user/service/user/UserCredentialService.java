package com.corner.user.service.user;

import com.corner.user.dto.RegisterParentRequest;
import com.corner.user.dto.RegisterStudentRequest;
import com.corner.user.dto.RegisterTeacherRequest;
import com.corner.user.entity.UserEntity;

public interface UserCredentialService {
    UserEntity addAdmin(RegisterStudentRequest registerStudentRequest);
    UserEntity addParent(RegisterParentRequest registerParentRequest);
    UserEntity addTeacher(RegisterTeacherRequest registerTeacherRequest);
}