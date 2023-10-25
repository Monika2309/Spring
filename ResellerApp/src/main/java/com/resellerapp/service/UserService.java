package com.resellerapp.service;

import com.resellerapp.model.dto.UserLoginDto;
import com.resellerapp.model.dto.UserRegisterDto;

public interface UserService {
    boolean login(UserLoginDto userLoginDto);

    boolean register(UserRegisterDto userRegisterDto);

    void logout();
}
