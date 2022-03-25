/**
 *
 * @author Thomas Basore
 * @version 1.0
 */
package MealThymePlanner;

import java.util.*;

public class RecipeDatabase {
    
    private HashMap<String, Recipe> recipes;

    public RecipeDatabase(HashMap<String, Recipe> recipes) {
        this.recipes = recipes;
    }

    public HashMap<String, Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(HashMap<String, Recipe> recipes) {
        this.recipes = recipes;
    }
    
    
    

}
