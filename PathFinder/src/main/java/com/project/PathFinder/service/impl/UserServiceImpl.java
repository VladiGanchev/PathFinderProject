package com.project.PathFinder.service.impl;

import com.project.PathFinder.entity.User;
import com.project.PathFinder.entity.dto.view.UserProfileViewModel;
import com.project.PathFinder.repository.UserRepository;
import com.project.PathFinder.service.UserService;
import com.project.PathFinder.service.session.LoggedUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final LoggedUser loggedUser;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(LoggedUser loggedUser, UserRepository userRepository, ModelMapper modelMapper) {
        this.loggedUser = loggedUser;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public User getLoggedUser() {
        return userRepository.findByUsername(loggedUser.getUsername());
    }

    @Override
    public UserProfileViewModel getUserProfile() {

        User user = userRepository.findByUsername(loggedUser.getUsername());

        return modelMapper.map(user, UserProfileViewModel.class);
    }
}
