package com.github.valchevgd.springrecipes.controllers;

import com.github.valchevgd.springrecipes.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    private final RecipeService recipeService;

    public HomeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({ "", "/", "index" })
    public String getIndexPage(Model model) {

        model.addAttribute("recipes", recipeService.getRecipes());
        return "index";
    }
}
