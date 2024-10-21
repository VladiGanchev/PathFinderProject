package com.project.PathFinder.service;

import com.project.PathFinder.entity.dto.AddRouteBindingModel;
import com.project.PathFinder.entity.dto.RouteDetailsViewModel;
import com.project.PathFinder.entity.dto.RouteViewModel;

import java.util.List;

public interface RouteService {

    void add(AddRouteBindingModel addRouteBindingModel);

    List<RouteViewModel> getAll();

    RouteDetailsViewModel getDetails(Long id);

}
