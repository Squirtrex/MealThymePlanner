/**
 *
 * @author Thomas Basore
 * @version 1.0
 */
package com.mycompany.mealthymeplanner;

import java.util.*;

public class User {
    
    private String name;
    private ArrayList<IngredientTag> hard_no;
    private ArrayList<IngredientTag> preferences;
    private HashMap<Recipe, Double> algorithmScore;
    private HashMap<Recipe, Integer> userRatings;
    private HashMap<Recipe, Integer> timeSinceLastMade;
    private ArrayList<Recipe> prioritizedRecipes;    
    private HashMap<String, Preference> ingredientPrefs;
    private HashMap<RecipeTag, Preference> attributePrefs;
    private boolean newUser;
    private ArrayList<String> likedCuisines;
            
public User()
    {
        this.name = "No Name";
        this.hard_no = new ArrayList<>();
        this.preferences = new ArrayList<>();
        this.algorithmScore = new HashMap<>();
        this.userRatings = new HashMap<>();
        this.timeSinceLastMade = new HashMap<>();
        this.prioritizedRecipes = new ArrayList<>();
        this.ingredientPrefs =  new HashMap<>();
        this.attributePrefs =  new HashMap<>();
        this.newUser = true;
        this.likedCuisines = new ArrayList<>();
    }

    public User(String name, ArrayList<IngredientTag> hard_no, ArrayList<IngredientTag> preferences, 
            HashMap<Recipe, Double> algorithmScore, HashMap<Recipe, Integer> userRatings, 
            HashMap<Recipe, Integer> timeSinceLastMade, ArrayList<Recipe> prioritizedRecipes, 
            HashMap<String, Preference> ingredientPrefs, HashMap<RecipeTag, Preference> attributePrefs,
            boolean newUser, ArrayList<String> likedCuisines) {
        this.name = name;
        this.hard_no = hard_no;
        this.preferences = preferences;
        this.algorithmScore = algorithmScore;
        this.userRatings = userRatings;
        this.timeSinceLastMade = timeSinceLastMade;
        this.prioritizedRecipes = prioritizedRecipes;
        this.newUser = newUser;
        this.likedCuisines = likedCuisines;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<IngredientTag> getHard_no() {
        return hard_no;
    }

    public void setHard_no(ArrayList<IngredientTag> hard_no) {
        this.hard_no = hard_no;
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

    public HashMap<Recipe, Integer> getUserRatings() {
        return userRatings;
    }

    public void setUserRatings(HashMap<Recipe, Integer> userRatings) {
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
    
    
    
    
    
    
    
    
    public ArrayList<Recipe> algorithm()
    {
        return new ArrayList<Recipe>();
    }
    

}
