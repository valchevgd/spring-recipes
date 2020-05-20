package com.github.valchevgd.springrecipes.services;

import com.github.valchevgd.springrecipes.domain.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();

    Recipe findById(Long id);
}
