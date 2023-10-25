package com.plannerapp.service;

import com.plannerapp.model.dto.UserLoginDto;
import com.plannerapp.model.dto.UserRegisterDto;

public interface UserService {

    boolean register(UserRegisterDto userRegisterDto);
    boolean login(UserLoginDto userLoginDto);

    void logout();
}
