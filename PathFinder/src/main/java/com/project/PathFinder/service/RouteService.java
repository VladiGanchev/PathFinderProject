package com.project.PathFinder.service;

import com.project.PathFinder.entity.dto.binding.AddRouteBindingModel;
import com.project.PathFinder.entity.dto.view.RouteDetailsViewModel;
import com.project.PathFinder.entity.dto.view.RouteViewModel;

import java.util.List;

public interface RouteService {

    void add(AddRouteBindingModel addRouteBindingModel);

    List<RouteViewModel> getAll();

    RouteDetailsViewModel getDetails(Long id);

}
