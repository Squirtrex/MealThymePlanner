package com.mycompany.mealthymeplanner;
import java.util.*;

public class Ingredient {

    private String name;
    private ArrayList<IngredientTag> tags;

    public Ingredient() {
        this("No Name", new ArrayList<IngredientTag>());
    }

    public Ingredient(String name, ArrayList<IngredientTag> tags) {
        this.name = name;
        this.tags = tags;
    }

    public Ingredient(String name) {
        this(name, new ArrayList<IngredientTag>());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<IngredientTag> getTags() {
        return tags;
    }

    public void setTags(ArrayList<IngredientTag> tags) {
        this.tags = tags;
    }

    public void addTag(IngredientTag newTag) {
        tags.add(newTag);
    }

}