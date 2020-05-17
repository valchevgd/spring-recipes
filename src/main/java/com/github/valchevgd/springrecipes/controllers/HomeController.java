package com.github.valchevgd.springrecipes.controllers;

import com.github.valchevgd.springrecipes.domain.Category;
import com.github.valchevgd.springrecipes.domain.UnitOfMeasure;
import com.github.valchevgd.springrecipes.repositories.CategoryRepository;
import com.github.valchevgd.springrecipes.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class HomeController {

    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public HomeController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @RequestMapping({ "", "/", "index" })
    public String getIndexPage() {

        Optional<Category> optionalCategory = categoryRepository.findByDescription("American");
        Optional<UnitOfMeasure> optionalUnitOfMeasure = unitOfMeasureRepository.findByDescription("Teaspoon");

        System.out.printf("Category: Id - %d Desc - %s%n", optionalCategory.get().getId(), optionalCategory.get().getDescription());
        System.out.printf("Unit Of Measure: Id - %d Desc - %s%n", optionalUnitOfMeasure.get().getId(), optionalUnitOfMeasure.get().getDescription());

        return "index";
    }
}
