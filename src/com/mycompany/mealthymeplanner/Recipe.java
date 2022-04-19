package com.mycompany.mealthymeplanner;

import java.util.*;

public class Recipe {

    private String name;
    private ArrayList<RecipeIngredient> ingredients;
    private ArrayList<String> steps;
    private String servings;
    //private int calories;
    private float cookTimeMinutes;
    private ArrayList<RecipeTag> recipeTags;

    public Recipe() {
        this("No Name", new ArrayList<RecipeIngredient>(), new ArrayList<String>(), "", 0, new ArrayList<RecipeTag>());
    }

    public Recipe(String name, ArrayList<RecipeIngredient> ingredients, ArrayList<String> steps, String servings, float cookTimeMinutes, ArrayList<RecipeTag> recipeTags) {
        this.name = name;
        this.ingredients = ingredients;
        this.steps = steps;
        this.servings = servings;
        //this.calories = calories;
        this.cookTimeMinutes = cookTimeMinutes;
        this.recipeTags = recipeTags;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<RecipeIngredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<RecipeIngredient> ingredients) {
        this.ingredients = ingredients;
    }

    public ArrayList<String> getSteps() {
        return steps;
    }

    public void setSteps(ArrayList<String> steps) {
        this.steps = steps;
    }

    public String getServings() {
        return servings;
    }

    public void setServings(String servings) {
        this.servings = servings;
    }

    //public int getCalories() {
    //    return calories;
    //}
    //public void setCalories(int calories) {
    //    this.calories = calories;
    //}
    public float getCookTimeMinutes() {
        return cookTimeMinutes;
    }

    public void setCookTimeMinutes(float cookTimeMinutes) {
        this.cookTimeMinutes = cookTimeMinutes;
    }

    public ArrayList<RecipeTag> getRecipeTags() {
        return recipeTags;
    }

    public void setRecipeTags(ArrayList<RecipeTag> recipeTags) {
        this.recipeTags = recipeTags;
    }

}
