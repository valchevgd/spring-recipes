package com.github.valchevgd.springrecipes.repositories;

import com.github.valchevgd.springrecipes.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
