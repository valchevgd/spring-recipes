package com.github.valchevgd.springrecipes.repositories;

import com.github.valchevgd.springrecipes.domain.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Long> {

    Optional<Category> findByDescription(String description);
}
