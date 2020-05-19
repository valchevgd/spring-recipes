package com.github.valchevgd.springrecipes.domain;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static junit.framework.TestCase.assertEquals;


class CategoryTest {

    private Category category;

    @BeforeEach
    public void setUp() {
        category = new Category();
    }

    @Test
    void setId() {
        Long idValue = 4L;

        category.setId(idValue);
        assertEquals(idValue, category.getId());
    }

    @Test
    void setDescription() {
    }

    @Test
    void setRecipes() {
    }
}