package com.mycompany.mealthymeplanner;

import java.util.*;

public class PriorityMachine {
    //private constructor because utility class atm

    private PriorityMachine() {
        throw new UnsupportedOperationException();
    }

    public static double calcPriority(User user, Recipe recipe) {
        int user_rating = user.getUserRatings().get(recipe);
        ArrayList<RecipeIngredient> ingredients = recipe.getIngredients();
        HashMap<String, Preference> i_prefs = user.getIngredientPrefs();
        HashMap<RecipeTag, Preference> a_prefs = user.getAttributePrefs(); //note: look at what recipetag is
        ArrayList<RecipeTag> recipe_attributes = recipe.getRecipeTags();
        double i_score = 0.0;
        double a_score = 0.0;
        double priority = 0.0;
        //double recency_bias = 1 - (1/days_since_making); //how to get days since last make?

        if (user_rating == 0) {
            user_rating = 3; //unrated should assume average ~~ 3. unrated !=0 stars (terrible rating!)
        }

        for (RecipeIngredient ri : ingredients) {
//      if (user.violatesRestrictions(ri.ingredient)) {
//        //System.out.println("Recipe vioalted dietary restrictions"); //debug
//        return -1;
//      }
            i_score += i_prefs.get(ri.ingredient.getName()).getPreference();
        }

        for (RecipeTag rt : recipe_attributes) {
//      if (user.violatesRestrictions(rt)) {
//        //System.out.println("Recipe vioalted dietary restrictions"); //debug
//        return -1;
//      }
            a_score += a_prefs.get(rt).getPreference();
        }

        i_score /= ingredients.size(); //Average of ingredient preferences
        a_score /= recipe_attributes.size(); //Average of attribute preferences

        //priority = (a_score + i_score + user_rating) * recency_bias;
        priority = (a_score + i_score + user_rating);
        return priority;
    }

    //Returns top 3 priority recipes from provided recipes array, according to user preferences.
    private Recipe[] getTopThree(User user, Recipe[] recipes) {
        Recipe first = new Recipe(); //default init
        Recipe second = new Recipe(); //default init
        Recipe third = new Recipe(); //default init
        double first_p = -1;
        double second_p = -1;
        double third_p = -1;

        double current_p;

        Recipe[] top_three = new Recipe[3];

        for (Recipe r : recipes) {
            current_p = calcPriority(user, r);
            if (current_p > first_p) {
                third_p = second_p;
                second_p = first_p;
                first_p = current_p;

                third = second;
                second = first;
                first = r;
            } else if (current_p > second_p) {
                third_p = second_p;
                second_p = current_p;

                third = second;
                second = r;
            } else if (current_p > third_p) {
                third_p = current_p;

                third = r;
            }
        }

        top_three[0] = first;
        top_three[1] = second;
        top_three[2] = third;

        return top_three;

    }

    public Recipe[] simpleRecThree(User user, int sample_size) {
        //ArrayList<Recipe> sample = new ArrayList(sample_size);
        HashMap<String, Recipe> all_recipes = new HashMap<String, Recipe>(); //Note! Obtain this!
        Object[] keys = all_recipes.keySet().toArray();
        String[] keys_copy = new String[all_recipes.size()];
        String[] sampled_keys = new String[sample_size];
        Recipe[] sampled_recipes = new Recipe[sample_size];
        Recipe[] reccomendations = new Recipe[3];

        //Copy all recipe names into new array to prevent editing original data by mistake.
        for (int i = 0; i < all_recipes.size(); i++) {
            keys_copy[i] = (String) keys[i];
        }

        //Create pool of recipe names as arraylist for dynamic properties (from keys_copy)
        ArrayList<String> key_pool = new ArrayList<>(Arrays.asList(keys_copy));
        Random rand = new Random();
        String random_key;

        //Randomly sample #sample_size recipe names WITHOUT replacement (hence arraylist)
        for (int i = 0; i < sample_size; i++) {
            //get a random recipe name from pool
            random_key = key_pool.get(rand.nextInt(key_pool.size()));
            //put this recipe name into array of sampled names
            sampled_keys[i] = random_key;
            //remove sampled recipe name from pool so future iterations will not sample it
            //(sampling without replacement)
            key_pool.remove(random_key);
        }

        //now, index allrecipes using key samples to get the recipes
        //in no particular order yet.
        for (int i = 0; i < sample_size; i++) {
            sampled_recipes[i] = all_recipes.get(sampled_keys[i]);
        }

        //Now we call getTopThree on the sample, which returns three recipes
        //whose priorities were highest in the sample (greatest to least).
        reccomendations = getTopThree(user, sampled_recipes);

        return reccomendations;
    }

    //whenever a user rates a recipe this should be called to update the preferences accordingly
    public static void updatePrefs(User user, Recipe recipe, int rating) {
        HashMap<String, Preference> i_prefs = user.getIngredientPrefs();
        HashMap<RecipeTag, Preference> a_prefs = user.getAttributePrefs(); //note: look at what recipetag is

        //update preference values for all ingredients in the recipe according to user rating of recipe.
        for (RecipeIngredient ri : recipe.getIngredients()) {
            //not sure if this updates original or not, might overwrite to be safe.
            i_prefs.get(ri.getIngredient().getName()).updatePreference(rating);
        }

        //update preference values for all attribtues (currently just recipe tags)
        //in the recipe according to user rating of recipe.
        for (RecipeTag rt : recipe.getRecipeTags()) {
            //not sure if this updates original or not, might overwrite to be safe.
            a_prefs.get(rt).updatePreference(rating);
        }

        //for "safety" because I lack java knowledge.
        user.setIngredientPrefs(i_prefs);
        user.setAttributePrefs(a_prefs);

    }

}
