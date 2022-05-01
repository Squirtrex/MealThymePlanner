package com.mycompany.mealthymeplanner;
import java.io.IOException;
import java.util.*;

public class MainTester {



    public static void main(String[] args) {

        User testUser2 = new User();
        if(testUser2.isNewUser())
            System.out.println("Test 1: Pass");
        else
            System.out.println("Test 1: Fail");

        String cuisine1 = "American";
        testUser2.addLikedCuisine(cuisine1);
        if(testUser2.getLikedCuisines().contains(cuisine1) == true)
            System.out.println("Test 2: Pass");
        else
            System.out.println("Test 2: Fail");

        String resrIngred1 = "eggs";
        testUser2.addRestrictedIngredient(resrIngred1);
        if(testUser2.getRestrictedIngredients().contains(resrIngred1) == true)
            System.out.println("Test 3: Pass");
        else
            System.out.println("Test 3: Fail");

        Recipe testRecipe1 = new Recipe();
        testRecipe1.setName("Salad");
        if(testRecipe1.getName().equals("Salad"))
            System.out.println("Test 4: Pass");
        else
            System.out.println("Test 4: Fail");

        testUser2.addFavoriteRecipe(testRecipe1);
        if(testUser2.getFavoriteRecipes().get("Salad").equals(testRecipe1))
            System.out.println("Test 5: Pass");
        else
            System.out.println("Test 5: Fail");

        Recipe testRecipe2 = new Recipe();
        testRecipe2.setName("Pasta");
        testRecipe2.addIngredient(new RecipeIngredient(5, "cups", new Ingredient("flour")));
        testUser2.addFutureRecipe(testRecipe2);
        if(testUser2.getFutureRecipes().contains(testRecipe2))
            System.out.println("Test 6: Pass");
        else
            System.out.println("Test 6: Fail");

        if(testRecipe2.getIngredients() != null)
            System.out.println("Test 7: Pass");
        else
            System.out.println("Test 7: Fail");


        User testUser = new User();
        ArrayList<String> someAllergies = new ArrayList<>();
        someAllergies.add("def");
        testUser.addRestrictedIngredient("ABC");
        testUser.setRestrictedIngredients(someAllergies);
        System.out.println("Here: " + testUser.getRestrictedIngredients());


        //test a preference object
        Preference p = new Preference();
        p.updatePreference(2);
        p.updatePreference(3);
        p.updatePreference(4);
        p.updatePreference(5);
        System.out.println("Preference test: " + p.getPreference());
        System.out.println("Expected value: 3.5");


        //Create some ingredient tag lists
        ArrayList<IngredientTag> itags_1 = new ArrayList<IngredientTag>();
        itags_1.add(IngredientTag.Vegan);
        itags_1.add(IngredientTag.Gluten_Free);

        ArrayList<IngredientTag> itags_2 = new ArrayList<IngredientTag>();
        itags_2.add(IngredientTag.Vegetarian);
        itags_2.add(IngredientTag.Gluten);


        //Create some ingredients
        Ingredient i_1 = new Ingredient ("Test ingredient 1", itags_1);
        Ingredient i_2 = new Ingredient ("Test ingredient 2", itags_2);


        //Create some recipe Ingredients
        RecipeIngredient ri_1 = new RecipeIngredient(0.5, "Cup", i_1);
        RecipeIngredient ri_2 = new RecipeIngredient(0.75, "Tbsp", i_2);


        //Build Ingredient Arrays
        ArrayList<RecipeIngredient> ings_1 = new ArrayList<RecipeIngredient>();
        ings_1.add(ri_1);
        ings_1.add(ri_2);

        ArrayList<RecipeIngredient> ings_2 = new ArrayList<RecipeIngredient>();
        ings_2.add(ri_2);
        ings_2.add(ri_1);

        //Build Directions
        ArrayList<String> d1 = new ArrayList<String>();
        d1.add("Step 1: Mix cup.");
        d1.add("Step 2: Done.");

        ArrayList<String> d2 = new ArrayList<String>();
        d2.add("Step 1: Pour tablespoon.");
        d2.add("Step 2: Finished.");


        //Make Test Recipes

        Recipe r1 = new Recipe();
        r1.setIngredients(ings_1);
        r1.setDirections(d1);
        r1.setName("Recipe 1");

        Recipe r2 = new Recipe();
        r2.setIngredients(ings_2);
        r2.setDirections(d2);
        r2.setName("Recipe 2");

        ArrayList<Recipe> recipes = new ArrayList<Recipe>();
        recipes.add(r1);
        recipes.add(r2);


        //Make test User
        User u = new User();
        HashMap<String, Preference> newPrefs = u.getIngredientPrefs();
        newPrefs.put("Test ingredient 1", p);
        u.setIngredientPrefs(newPrefs);

        //Print User
        System.out.println("User Object:");
        //System.out.println("Ingredient Prefs: " + u.getIngredientPrefs());
        //System.out.println("Ingredient Prefs: " + u.getIngredientPrefs());








    }

}
