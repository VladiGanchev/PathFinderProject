package com.project.PathFinder.service.impl;

import com.project.PathFinder.entity.Category;
import com.project.PathFinder.entity.Route;
import com.project.PathFinder.entity.User;
import com.project.PathFinder.entity.dto.AddRouteBindingModel;
import com.project.PathFinder.entity.dto.RouteDetailsViewModel;
import com.project.PathFinder.entity.dto.RouteViewModel;
import com.project.PathFinder.exceptions.RouteNotFoundException;
import com.project.PathFinder.repository.CategoryRepository;
import com.project.PathFinder.repository.RouteRepository;
import com.project.PathFinder.service.RouteService;
import com.project.PathFinder.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.boot.jackson.JsonComponentModule;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class RouteServiceImpl implements RouteService {

    private final RouteRepository routeRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final CategoryRepository categoryRepository;
    private final JsonComponentModule jsonComponentModule;

    public RouteServiceImpl(RouteRepository routeRepository, UserService userService, ModelMapper modelMapper, CategoryRepository categoryRepository, JsonComponentModule jsonComponentModule) {
        this.routeRepository = routeRepository;
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.categoryRepository = categoryRepository;
        this.jsonComponentModule = jsonComponentModule;
    }


    @Override
    public void add(AddRouteBindingModel addRouteBindingModel) {
        Route route = modelMapper.map(addRouteBindingModel, Route.class);
        route.getCategories().clear();


        Set<Category> categories = categoryRepository.findByNameIn(addRouteBindingModel.getCategories());
        route.addCategories(categories);

        User user =userService.getLoggedUser();
        route.setAuthor(user);

        String regex = "v=(.*)";
        Pattern compile = Pattern.compile(regex);
        Matcher matcher = compile.matcher(addRouteBindingModel.getVideoUrl());
        if (matcher.find()){
            String url = matcher.group(1);
            route.setVideoUrl(url);
        }
        routeRepository.save(route);
    }

    @Override
    public List<RouteViewModel> getAll() {
        return routeRepository.findAll()
                .stream()
                .map(route -> modelMapper.map(route, RouteViewModel.class))
                .toList();
    }

    @Override
    public RouteDetailsViewModel getDetails(Long id) {
        Route route = routeRepository.findById(id)
                .orElseThrow(() -> new RouteNotFoundException("Route with id: " + id + " was not found"));

        RouteDetailsViewModel routeDetailsViewModel = modelMapper.map(route, RouteDetailsViewModel.class);
        routeDetailsViewModel.setAuthorName(route.getAuthor().getFullName());

        return routeDetailsViewModel;
    }
}

