/**
 *
 * @author Thomas Basore
 * @version 1.0
 */
package com.mycompany.mealthymeplanner;

import java.util.*;

public class User {

    private ArrayList<String> likedCuisines;
    private ArrayList<String> restrictedIngredients;
    private ArrayList<IngredientTag> preferences;
    private HashMap<Recipe, Double> algorithmScore;
    private HashMap<String, Integer> userRatings;
    private HashMap<Recipe, Integer> timeSinceLastMade;
    private ArrayList<Recipe> prioritizedRecipes;
    private HashMap<String, Preference> ingredientPrefs;
    private HashMap<RecipeTag, Preference> attributePrefs;
    private boolean newUser;
    private  HashMap<String, Recipe> customRecipes;

    public User() {
        this.likedCuisines = new ArrayList<>();
        this.restrictedIngredients = new ArrayList<>();
        this.preferences = new ArrayList<>();
        this.algorithmScore = new HashMap<>();
        this.userRatings = new HashMap<>();
        this.timeSinceLastMade = new HashMap<>();
        this.prioritizedRecipes = new ArrayList<>();
        this.ingredientPrefs = new HashMap<>();
        this.attributePrefs = new HashMap<>();
        this.newUser = true;
        this.customRecipes = new HashMap<>();
    }

    public User(ArrayList<String> likedCuisines, ArrayList<String> restrictedIngredients, ArrayList<IngredientTag> preferences, HashMap<Recipe, Double> algorithmScore, HashMap<String, Integer> userRatings, HashMap<Recipe, Integer> timeSinceLastMade, ArrayList<Recipe> prioritizedRecipes, HashMap<String, Preference> ingredientPrefs, HashMap<RecipeTag, Preference> attributePrefs, boolean newUser, HashMap<String, Recipe> customRecipes) {
        this.likedCuisines = likedCuisines;
        this.restrictedIngredients = restrictedIngredients;
        this.preferences = preferences;
        this.algorithmScore = algorithmScore;
        this.userRatings = userRatings;
        this.timeSinceLastMade = timeSinceLastMade;
        this.prioritizedRecipes = prioritizedRecipes;
        this.ingredientPrefs = ingredientPrefs;
        this.attributePrefs = attributePrefs;
        this.newUser = newUser;
        this.customRecipes = customRecipes;
    }

    public ArrayList<IngredientTag> getPreferences() {
        return preferences;
    }

    public void setPreferences(ArrayList<IngredientTag> preferences) {
        this.preferences = preferences;
    }

    public HashMap<Recipe, Double> getAlgorithmScore() {
        return algorithmScore;
    }

    public void setAlgorithmScore(HashMap<Recipe, Double> algorithmScore) {
        this.algorithmScore = algorithmScore;
    }

    public HashMap<String, Integer> getUserRatings() {
        return userRatings;
    }

    public void setUserRatings(HashMap<String, Integer> userRatings) {
        this.userRatings = userRatings;
    }

    public HashMap<Recipe, Integer> getTimeSinceLastMade() {
        return timeSinceLastMade;
    }

    public void setTimeSinceLastMade(HashMap<Recipe, Integer> timeSinceLastMade) {
        this.timeSinceLastMade = timeSinceLastMade;
    }

    public ArrayList<Recipe> getPrioritizedRecipes() {
        return prioritizedRecipes;
    }

    public void setPrioritizedRecipes(ArrayList<Recipe> prioritizedRecipes) {
        this.prioritizedRecipes = prioritizedRecipes;
    }

    public HashMap<String, Preference> getIngredientPrefs() {
        return ingredientPrefs;
    }

    public HashMap<RecipeTag, Preference> getAttributePrefs() {
        return attributePrefs;
    }

    public void setIngredientPrefs(HashMap<String, Preference> ingredientPrefs) {
        this.ingredientPrefs = ingredientPrefs;
    }

    public void setAttributePrefs(HashMap<RecipeTag, Preference> attributePrefs) {
        this.attributePrefs = attributePrefs;
    }

    public boolean isNewUser() {
        return newUser;
    }

    public void setNewUser(boolean newUser) {
        this.newUser = newUser;
    }

    public ArrayList<String> getLikedCuisines() {
        return likedCuisines;
    }

    public void setLikedCuisines(ArrayList<String> likedCuisines) {
        this.likedCuisines = likedCuisines;
    }

    public ArrayList<String> getRestrictedIngredients() {
        return restrictedIngredients;
    }

    public void setRestrictedIngredients(ArrayList<String> restrictedIngredients) {
        this.restrictedIngredients = restrictedIngredients;
    }

    public HashMap<String, Recipe> getCustomRecipes() {
        return customRecipes;
    }

    public void setCustomRecipes(HashMap<String, Recipe> customRecipes) {
        this.customRecipes = customRecipes;
    }

    // method to add one cuisine to the liked cuisine list if it is not already in the list
    public void addLikedCuisine(String cuisine) {
        if (likedCuisines.contains(cuisine) == false) {
            likedCuisines.add(cuisine);
        }
    }

    /* Method to add one restricted ingredient (allergy or custome ingredient) 
    * to the restricted ingredient list if it is not already in the list
     */
    public void addRestrictedIngredient(String restrIngred) {
        if (restrictedIngredients.contains(restrIngred) == false) {
        restrictedIngredients.add(restrIngred);
        }
    }

    public void addCustomRecipe(Recipe rec)
    {
        customRecipes.put(rec.getName(), rec);
    }

    public ArrayList<Recipe> algorithm() {
        return new ArrayList<Recipe>();
    }

}
