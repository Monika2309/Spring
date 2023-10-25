package com.plannerapp.service.impl;

import com.plannerapp.model.dto.UserLoginDto;
import com.plannerapp.model.dto.UserRegisterDto;
import com.plannerapp.model.entity.User;
import com.plannerapp.repo.UserRepository;
import com.plannerapp.service.UserService;
import com.plannerapp.util.LoggedUser;
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
    public boolean login(UserLoginDto userLoginDto) {
        User user = userRepository.findByUsername(userLoginDto.getUsername());

        if (user != null && passwordEncoder.matches(userLoginDto.getPassword(), user.getPassword())){
            loggedUser.login(userLoginDto.getUsername());
            return true;
        }
        return false;
    }

    @Override
    public void logout() {
        this.loggedUser.logout();
    }
}
