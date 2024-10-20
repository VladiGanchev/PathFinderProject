package com.project.PathFinder.controller;

import com.project.PathFinder.entity.dto.UserLoginBindingModel;
import com.project.PathFinder.entity.dto.UserRegisterBindingModel;
import com.project.PathFinder.entity.dto.UserProfileViewModel;
import com.project.PathFinder.service.AuthenticationService;
import com.project.PathFinder.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/users")
public class UsersController {

    private final AuthenticationService authenticationService;

    private final UserService userService;

    public UsersController(AuthenticationService userService, UserService userService1) {
        this.authenticationService = userService;
        this.userService = userService1;
    }

    @GetMapping("/login")
    public ModelAndView login(){
        return new ModelAndView("login");
    }

    @PostMapping("/login")
    public ModelAndView login(UserLoginBindingModel userLoginBindingModel){

        boolean isLogged = authenticationService.login(userLoginBindingModel);

        if(isLogged){
            return new ModelAndView("redirect:/");
        }
        return new ModelAndView("login");

    }

    @GetMapping("/register")
    public ModelAndView register(){
        return new ModelAndView("register");
    }

    @PostMapping("/register")
    public ModelAndView register(UserRegisterBindingModel userRegisterBindingModel){
        this.authenticationService.register(userRegisterBindingModel);

        return new ModelAndView("redirect:login");
    }

    @GetMapping("/logout")
    public ModelAndView logout(){
        this.authenticationService.logout();

        return new ModelAndView("redirect:/");
    }

    @GetMapping("/profile")
    public ModelAndView profile(){
        UserProfileViewModel userProfileViewModel =  userService.getUserProfile();

        ModelAndView modelAndView = new ModelAndView("profile");
        modelAndView.addObject("userProfileViewModel", userProfileViewModel);

        return modelAndView;

    }


}
