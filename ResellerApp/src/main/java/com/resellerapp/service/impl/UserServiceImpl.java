package com.resellerapp.service.impl;

import com.resellerapp.model.dto.UserLoginDto;
import com.resellerapp.model.dto.UserRegisterDto;
import com.resellerapp.model.entity.User;
import com.resellerapp.repository.UserRepository;
import com.resellerapp.service.UserService;
import com.resellerapp.util.LoggedUser;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final LoggedUser loggedUser;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, LoggedUser loggedUser) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.loggedUser = loggedUser;
    }

    @Override
    public boolean login(UserLoginDto userLoginDto) {

        User user = userRepository.findByUsername(userLoginDto.getUsername());

        if (user != null && passwordEncoder.matches(userLoginDto.getPassword(), user.getPassword())){
            loggedUser.login(userLoginDto.getUsername());
            return true;
        }
        return false;
    }

    @Override
    public boolean register(UserRegisterDto userRegisterDto) {
        if (!userRegisterDto.getPassword().equals(userRegisterDto.getConfirmPassword())) {
            return false;
        }
        boolean exist = userRepository.existsByUsernameOrEmail(userRegisterDto.getUsername(), userRegisterDto.getEmail());

        if (exist) {
            return false;
        }

        User user = new User();
        user.setUsername(userRegisterDto.getUsername());
        user.setPassword(passwordEncoder.encode(userRegisterDto.getPassword()));
        user.setEmail(userRegisterDto.getEmail());

        userRepository.save(user);
        return true;
    }

    @Override
    public void logout() {
        this.loggedUser.logout();
    }
}
