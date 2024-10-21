package com.project.PathFinder.service.impl;

import com.project.PathFinder.entity.Role;
import com.project.PathFinder.entity.User;
import com.project.PathFinder.entity.dto.binding.UserLoginBindingModel;
import com.project.PathFinder.entity.dto.binding.UserRegisterBindingModel;
import com.project.PathFinder.entity.enums.Level;
import com.project.PathFinder.entity.enums.UserRoles;
import com.project.PathFinder.repository.UserRepository;
import com.project.PathFinder.service.AuthenticationService;
import com.project.PathFinder.service.session.LoggedUser;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final LoggedUser loggedUser;

    @PersistenceContext
    private EntityManager entityManager;

    public AuthenticationServiceImpl(UserRepository userRepository, ModelMapper modelMapper, LoggedUser loggedUser) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.loggedUser = loggedUser;
    }

    @Override
    @Transactional // Ensure that this method runs within a transaction
    public void register(UserRegisterBindingModel userRegisterBindingModel) {
        User user = modelMapper.map(userRegisterBindingModel, User.class);

        // Optionally set the user level
        user.setLevel(Level.BEGINNER); // or whichever level you want to assign

        // Fetch the existing role from the database
        Role existingRole = findRoleByName(UserRoles.USER); // Implement this method

        // Check if the role exists
        if (existingRole == null) {
            throw new IllegalArgumentException("Role USER does not exist");
        }

        // Set the role for the user
        Set<Role> roles = new HashSet<>();
        roles.add(existingRole); // Use the existing role
        user.setRole(roles);

        // Save the user
        userRepository.save(user);
    }

    @Override
    public boolean login(UserLoginBindingModel userLoginBindingModel) {
        String username = userLoginBindingModel.getUsername();
        User user = this.userRepository.findByUsername(username);

        if (user == null) {
            throw new IllegalArgumentException("User with username " + username + " not found");
        }

        if (!userLoginBindingModel.getPassword().equals(user.getPassword())) {
            throw new IllegalArgumentException("User entered incorrect password");
        }

        loggedUser.setUsername(user.getUsername());
        loggedUser.setEmail(user.getEmail());
        loggedUser.setFullName(user.getFullName());
        loggedUser.setLogged(true);

        return userLoginBindingModel.getPassword().equals(user.getPassword());
    }

    @Override
    public void logout() {
        loggedUser.logout();
    }

    public Role findRoleByName(UserRoles roleName) {
        return entityManager.createQuery("SELECT r FROM Role r WHERE r.name = :roleName", Role.class)
                .setParameter("roleName", roleName)
                .getSingleResult();
    }
}
