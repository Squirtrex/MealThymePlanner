/**
 *
 * @author Thomas Basore
 * @version 1.0
 */
package com.mycompany.mealthymeplanner;

import java.util.*;

public class Recipe {
    
    private String name;
    private ArrayList<RecipeIngredient> ingredients;
    private ArrayList<String> steps;
    private String description;
    private int servings;
    private int calories;
    private int prepTimeMinutes;
    private int cookTimeMinutes;
    private int totalTimeMinutes;
    private ArrayList<RecipeTag> recipeTags;
        
    
    public Recipe()
    {
        this("No Name", new ArrayList<RecipeIngredient>(), new ArrayList<String>(), "No description", 0, 0, 0, 0, 0, new ArrayList<RecipeTag>());
    }

    public Recipe(String name, ArrayList<RecipeIngredient> ingredients, ArrayList<String> steps, String description, int servings, int calories, int prepTimeMinutes, int cookTimeMinutes, int totalTimeMinutes, ArrayList<RecipeTag> recipeTags) {
        this.name = name;
        this.ingredients = ingredients;
        this.steps = steps;
        this.description = description;
        this.servings = servings;
        this.calories = calories;
        this.prepTimeMinutes = prepTimeMinutes;
        this.cookTimeMinutes = cookTimeMinutes;
        this.totalTimeMinutes = totalTimeMinutes;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getPrepTimeMinutes() {
        return prepTimeMinutes;
    }

    public void setPrepTimeMinutes(int prepTimeMinutes) {
        this.prepTimeMinutes = prepTimeMinutes;
    }

    public int getCookTimeMinutes() {
        return cookTimeMinutes;
    }

    public void setCookTimeMinutes(int cookTimeMinutes) {
        this.cookTimeMinutes = cookTimeMinutes;
    }

    public int getTotalTimeMinutes() {
        return totalTimeMinutes;
    }

    public void setTotalTimeMinutes(int totalTimeMinutes) {
        this.totalTimeMinutes = totalTimeMinutes;
    }

    public ArrayList<RecipeTag> getRecipeTags() {
        return recipeTags;
    }

    public void setRecipeTags(ArrayList<RecipeTag> recipeTags) {
        this.recipeTags = recipeTags;
    }
    
    
    
    

}
