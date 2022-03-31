/**
 *
 * @author Thomas Basore
 * @version 1.0
 */
package MealThymePlanner;

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
    
public User()
    {
        this("No Name", new ArrayList<IngredientTag>(), new ArrayList<IngredientTag>(), new HashMap<Recipe, Double>(), new HashMap<Recipe, Integer>(), new HashMap<Recipe, Integer>(), new ArrayList<Recipe>(), new HashMap<String, Preference>(), new HashMap<RecipeTag, Preference>());
    }

    public User(String name, ArrayList<IngredientTag> hard_no, ArrayList<IngredientTag> preferences, HashMap<Recipe, Double> algorithmScore, HashMap<Recipe, Integer> userRatings, HashMap<Recipe, Integer> timeSinceLastMade, ArrayList<Recipe> prioritizedRecipes, HashMap<String, Preference> ingredientPrefs, HashMap<RecipeTag, Preference> attributePrefs) {
        this.name = name;
        this.hard_no = hard_no;
        this.preferences = preferences;
        this.algorithmScore = algorithmScore;
        this.userRatings = userRatings;
        this.timeSinceLastMade = timeSinceLastMade;
        this.prioritizedRecipes = prioritizedRecipes;
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
    
    
    
    
    public ArrayList<Recipe> algorithm()
    {
        return new ArrayList<Recipe>();
    }
    

}
