package com.project.PathFinder.entity.dto.binding;

import com.project.PathFinder.entity.enums.CategoryName;
import com.project.PathFinder.entity.enums.Level;

import java.util.Set;

public class AddRouteBindingModel {

    private String name;

    private String description;

    private Level level;

    private String videoUrl;

    private Set<CategoryName> categories;

    public Set<CategoryName> getCategories() {
        return categories;
    }

    public void setCategories(Set<CategoryName> categories) {
        this.categories = categories;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
}

