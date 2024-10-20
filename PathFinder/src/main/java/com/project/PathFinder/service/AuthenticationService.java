package com.project.PathFinder.service;

import com.project.PathFinder.entity.dto.UserLoginBindingModel;
import com.project.PathFinder.entity.dto.UserRegisterBindingModel;

public interface AuthenticationService {

    void register(UserRegisterBindingModel userRegisterBindingModel);

    boolean login(UserLoginBindingModel userLoginBindingModel);

    void logout();
}
