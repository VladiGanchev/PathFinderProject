package com.project.PathFinder.service;

import com.project.PathFinder.entity.User;
import com.project.PathFinder.entity.dto.UserProfileViewModel;

public interface UserService {
    User getLoggedUser();

    UserProfileViewModel getUserProfile();
}
