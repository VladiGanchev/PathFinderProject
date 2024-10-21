package com.project.PathFinder.controller;

import com.project.PathFinder.entity.dto.binding.AddRouteBindingModel;
import com.project.PathFinder.entity.dto.view.RouteDetailsViewModel;
import com.project.PathFinder.entity.dto.view.RouteViewModel;
import com.project.PathFinder.entity.enums.CategoryName;
import com.project.PathFinder.entity.enums.Level;
import com.project.PathFinder.service.RouteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/routes")
public class RouteController {

    private final RouteService routeService;

    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping
    public ModelAndView getAll() {
        List<RouteViewModel> routes = routeService.getAll();
        ModelAndView modelAndView = new ModelAndView("routes");
        modelAndView.addObject("routes", routes);

        return modelAndView;
    }

    @GetMapping("/add")
    public ModelAndView add(AddRouteBindingModel addRouteBindingModel) {

        ModelAndView modelAndView = new ModelAndView("add-route");

        modelAndView.addObject("levels", Level.values());
        modelAndView.addObject("categories", CategoryName.values());
        modelAndView.addObject("addRouteBindingModel", addRouteBindingModel);

        return modelAndView;
    }

    @GetMapping("/details/{id}")
    public ModelAndView getDetails(@PathVariable("id") Long id, Model model) {
        RouteDetailsViewModel route = routeService.getDetails(id);

        ModelAndView modelAndView = new ModelAndView("route-details");
        modelAndView.addObject("route", route);

        return modelAndView;
    }

    @PostMapping("/add")
    public ModelAndView add(AddRouteBindingModel addRouteBindingModel,
                            BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        routeService.add(addRouteBindingModel);

        return new ModelAndView("redirect:/");
    }
}

