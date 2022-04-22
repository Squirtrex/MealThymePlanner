package com.mycompany.mealthymeplanner;

import java.util.*;

public class Recipe {

    private String name;
    private ArrayList<RecipeIngredient> ingredients;
    private ArrayList<String> directions;
    private String servings;
    private double calories;
    private float cookTimeMinutes;
    private ArrayList<RecipeTag> recipeTags;

    public Recipe() {
        this.name = "no Name";
        this.ingredients = new ArrayList<>();
        this.directions = new ArrayList<>();
        this.servings = "Unknown";
        this.calories = 0;
        this.cookTimeMinutes = 0;
        this.recipeTags = new ArrayList<>();
    }

    public Recipe(String name, ArrayList<RecipeIngredient> ingredients, ArrayList<String> directions, String servings, double calories, float cookTimeMinutes, ArrayList<RecipeTag> recipeTags) {
        this.name = name;
        this.ingredients = ingredients;
        this.directions = directions;
        this.servings = servings;
        this.calories = calories;
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

    public ArrayList<String> getDirections() {
        return directions;
    }

    public void setDirections(ArrayList<String> directions) {
        this.directions = directions;
    }

    public String getServings() {
        return servings;
    }

    public void setServings(String servings) {
        this.servings = servings;
    }

    public double getCalories() {
        return calories;
    }
    
    public void setCalories(double calories) {
        this.calories = calories;
    }
    
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
