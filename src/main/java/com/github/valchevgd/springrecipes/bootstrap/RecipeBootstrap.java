package com.github.valchevgd.springrecipes.bootstrap;

import com.github.valchevgd.springrecipes.domain.*;
import com.github.valchevgd.springrecipes.repositories.CategoryRepository;
import com.github.valchevgd.springrecipes.repositories.RecipeRepository;
import com.github.valchevgd.springrecipes.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final RecipeRepository recipeRepository;
    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public RecipeBootstrap(RecipeRepository recipeRepository,
                           CategoryRepository categoryRepository,
                           UnitOfMeasureRepository unitOfMeasureRepository) {
        this.recipeRepository = recipeRepository;
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        recipeRepository.saveAll(getRecipes());
        log.debug("Logging Bootstrap Data");
    }

    private List<Recipe> getRecipes() {

        List<Recipe> recipes = new ArrayList<>(2);

        Optional<UnitOfMeasure> eachUnitOptional = unitOfMeasureRepository.findByDescription("Each");

        if(! eachUnitOptional.isPresent()) {
            throw new RuntimeException("Expected UOM not found! 404...");
        }

        Optional<UnitOfMeasure> tablespoonUnitOptional = unitOfMeasureRepository.findByDescription("Tablespoon");

        if(! tablespoonUnitOptional.isPresent()) {
            throw new RuntimeException("Expected UOM not found! 404...");
        }

        Optional<UnitOfMeasure> teaspoonUnitOptional = unitOfMeasureRepository.findByDescription("Teaspoon");

        if(! teaspoonUnitOptional.isPresent()) {
            throw new RuntimeException("Expected UOM not found! 404...");
        }

        Optional<UnitOfMeasure> dashUnitOptional = unitOfMeasureRepository.findByDescription("Dash");

        if(! dashUnitOptional.isPresent()) {
            throw new RuntimeException("Expected UOM not found! 404...");
        }

        Optional<UnitOfMeasure> pintUnitOptional = unitOfMeasureRepository.findByDescription("Pint");

        if(! pintUnitOptional.isPresent()) {
            throw new RuntimeException("Expected UOM not found! 404...");
        }

        Optional<UnitOfMeasure> cupsUnitOptional = unitOfMeasureRepository.findByDescription("Cups");

        if(! cupsUnitOptional.isPresent()) {
            throw new RuntimeException("Expected UOM not found! 404...");
        }

        UnitOfMeasure eachUom = eachUnitOptional.get();
        UnitOfMeasure tablespoonUom = tablespoonUnitOptional.get();
        UnitOfMeasure teaspoonUom = teaspoonUnitOptional.get();
        UnitOfMeasure dashUom = dashUnitOptional.get();
        UnitOfMeasure pintUom = pintUnitOptional.get();
        UnitOfMeasure cupsUom = cupsUnitOptional.get();

        Optional<Category> americanCategoryOptional = categoryRepository.findByDescription("American");

        if (! americanCategoryOptional.isPresent()) {
            throw new RuntimeException("Expected Category not found! 404...");
        }

        Optional<Category> mexicanCategoryOptional = categoryRepository.findByDescription("Mexican");

        if (! mexicanCategoryOptional.isPresent()) {
            throw new RuntimeException("Expected Category not found! 404...");
        }

        Category americanCategory = americanCategoryOptional.get();
        Category mexicanCategory = mexicanCategoryOptional.get();

        Recipe guacRecipe = new Recipe();
        guacRecipe.setDescription("Perfect Guacamole");
        guacRecipe.setPrepTime(10);
        guacRecipe.setCookTime(0);
        guacRecipe.setDifficulty(Difficulty.EASY);
        guacRecipe.setDirections("A lot of things...");

        Notes guacaNotes = new Notes();
        guacaNotes.setRecipeNotes("A lot of notes...");

        guacRecipe.setNotes(guacaNotes);

        guacRecipe.addIngredient(new Ingredient("ripe avocados", new BigDecimal(2), eachUom));
        guacRecipe.addIngredient(new Ingredient("Kosher salt", new BigDecimal(".5"), teaspoonUom));
        guacRecipe.addIngredient(new Ingredient("fresh lime juice", new BigDecimal(2), tablespoonUom));
        guacRecipe.addIngredient(new Ingredient("minced red onion", new BigDecimal(2), tablespoonUom));
        guacRecipe.addIngredient(new Ingredient("serrano chiles", new BigDecimal(2), eachUom));
        guacRecipe.addIngredient(new Ingredient("Cilantro", new BigDecimal(2), tablespoonUom));
        guacRecipe.addIngredient(new Ingredient("black pepper", new BigDecimal(2), dashUom));
        guacRecipe.addIngredient(new Ingredient("ripe tomato", new BigDecimal(".5"), eachUom));

        guacRecipe.getCategories().add(americanCategory);
        guacRecipe.getCategories().add(mexicanCategory);

        recipes.add(guacRecipe);

        Recipe tacoRecipe = new Recipe();
        tacoRecipe.setDescription("Perfect Taco");
        tacoRecipe.setPrepTime(10);
        tacoRecipe.setCookTime(0);
        tacoRecipe.setDifficulty(Difficulty.HARD);
        tacoRecipe.setDirections("A lot of things...");

        Notes tacoNotes = new Notes();
        tacoNotes.setRecipeNotes("A lot of notes...");

        tacoRecipe.setNotes(tacoNotes);

        tacoRecipe.addIngredient(new Ingredient("ripe avocados", new BigDecimal(2), eachUom));
        tacoRecipe.addIngredient(new Ingredient("Kosher salt", new BigDecimal(".5"), teaspoonUom));
        tacoRecipe.addIngredient(new Ingredient("fresh lime juice", new BigDecimal(2), tablespoonUom));
        tacoRecipe.addIngredient(new Ingredient("minced red onion", new BigDecimal(2), tablespoonUom));
        tacoRecipe.addIngredient(new Ingredient("black pepper", new BigDecimal(2), dashUom));
        tacoRecipe.addIngredient(new Ingredient("ripe tomato", new BigDecimal(".5"), eachUom));

        tacoRecipe.getCategories().add(americanCategory);
        tacoRecipe.getCategories().add(mexicanCategory);

        recipes.add(tacoRecipe);

        return recipes;
    }

}
