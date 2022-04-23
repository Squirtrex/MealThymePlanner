/**
 * @author Nicholas Pepin
 */

package com.mycompany.mealthymeplanner;

/**
 * These are the imports I used for the database creation
 */
import com.codename1.io.*;
import com.codename1.ui.*;
import com.codename1.util.regex.*;
import java.io.*;
import java.util.*;

/**
 * This class reads a .csv file into a HashMap and stores it in the App's memory for faster loading and referencing.
 */
public class CSV_to_HashMap extends HashMap<String, Recipe> {

    /**
     * Storage structures used.
     */
    ArrayList<RecipeIngredient> ingredients = new ArrayList<>();
    ArrayList<IngredientTag> ingredientTags = new ArrayList<>();
    ArrayList<RecipeTag> recipeTags = new ArrayList<>();
    ArrayList<String> steps = new ArrayList<>();
    HashMap<String, Recipe> recipes = new HashMap<>();

    /**
     * This is the constructor that loads all the csv files into string arrays.
     * IMPORTANT: THE .CSV FILES MUST BE IN THE /src DIRECTORY, NOT THE /src/Tester DIRECTORY.
     * @throws IOException
     */
    public CSV_to_HashMap() throws IOException {

        String placeholder = "";
        String name = "";
        String servingSize = "";
        String imgpath = "";
        double calories = 0;
        float cookTime = 0;

        String[] files = {"/American.csv","/Italian.csv","/Mexican.csv", "/MiddleEastern.csv","/Asian.csv","/African.csv"};

        try {
            for (String file : files) {
                InputStream is = Display.getInstance().getResourceAsStream(this.getClass(), file);
                if(file.contains("American")){ recipeTags.add(RecipeTag.American); }
                else if(file.contains("Italian")){ recipeTags.add(RecipeTag.Italian); }
                else if(file.contains("Mexican")){ recipeTags.add(RecipeTag.Mexican); }
                else if(file.contains("MiddleEastern")){ recipeTags.add(RecipeTag.Middle_Eastern); }
                else if(file.contains("Asian")){ recipeTags.add(RecipeTag.Asian); }
                else if(file.contains("African")){ recipeTags.add(RecipeTag.African); }
                CSVParser parser = new CSVParser();
                String[][] data = parser.parse(is);
                for (int i = 1; i < data.length; i++) {
                    for (int j = 0; j < data[i].length; j++) {
                        switch (j) {
                            case 0: name = data[i][j]; break;
                            case 1: cookTime = Float.parseFloat(data[i][j]); break;
                            case 2: servingSize = data[i][j]; break;
                            case 3: placeholder = data[i][j]; parseIngredients(placeholder); break;
                            case 4: placeholder = data[i][j]; parseSteps(placeholder); break;
                            case 5: imgpath = data[i][j]; break;
                            case 6: placeholder = data[i][j]; calories = parseCalories(placeholder);
                        }
                        Recipe recipe = new Recipe(name, ingredients, steps, servingSize,calories, cookTime, recipeTags);
                        recipes.put(name, recipe);
                        Clear();
                    }
                }
            }
        }catch(IOException err) {
            Log.e(err);
        }
        //This is to test hashmap functionality.
        //tester();
    }
    
    /**
     * This gives the hashmap to other files.
     * @return
     */
    HashMap<String, Recipe> getHashMap(){ return recipes; }
    
    /**
     * Clears the recipe tags lists after the recipe is put into the hashmap.
     */
    private void Clear(){
        ingredients.clear();
        ingredientTags.clear();
        steps.clear();
        recipeTags.clear();
    }

    /**
     * This is a tester method to test if the hashmap is holding the recipe objects from all of the .csvs.
     */
    private void tester(){
        System.out.println("African recipes: "+recipes.containsKey("Harissa Hummus"));
        System.out.println("American recipes: "+recipes.containsKey("Cajun Pineapple Salad"));
        System.out.println("Asian recipes: "+recipes.containsKey("Thai Peanut Glazed Grouper"));
        System.out.println("French recipes: "+recipes.containsKey("Chestnut Cream Filling"));
        System.out.println("Italian recipes: "+recipes.containsKey("Eggplant Parmesan with Chicken Meatballs"));
        System.out.println("Mexican recipes: "+recipes.containsKey("Prickly Pear Margarita"));
        System.out.println("MiddleEastern recipes: "+recipes.containsKey("Lentil Balls"));
    }

    /**
     * This method parses out the ingredients and sends them to tagIngredients() to be sited through, categorized, and then put into an ArrayList.
     * @param text
     */
    private void parseIngredients(String text){
        String ingredient = "";
        int index = 0;
        RE re = new RE("'([^']*)'");
        while(re.match(text, index)){
            for(int i=0;i<re.getParenCount();i++){
                ingredient = re.getParen(i);
                if(!ingredient.contains(",") && !ingredient.contains("'")){tagIngredients(ingredient); }
            }
            index = re.getParenEnd(re.getParenCount()-1);
        }
    }

    /**
     * This method parses the steps and puts them into the steps ArrayList.
     * @param text
     */
    private void parseSteps(String text){
        String step = "";
        int index = 0;
        RE re = new RE("'([^']*)'");
        while(re.match(text,index)){
            for(int i=0;i<re.getParenCount();i++){
                step = re.getParen(i);
                if(!step.contains("'") && step.length()!=2){ steps.add(step);}
            }
            index = re.getParenEnd(re.getParenCount()-1);
        }
    }

    /**
     * This method pulls the double value associated with the calorie value of the recipe.
     * @param text
     * @return
     */
    private double parseCalories(String text){
        double cal = 0;
        String nut = "";
        String[] c = new String[2];
        int count = 0;
        int index = 0;
        RE re = new RE("'([^']*)'");
        RE re2 = new RE("[:space:]");
        while(re.match(text, index)){
            for(int i=0;i<re.getParenCount();i++) {
                nut = re.getParen(i);
                if (nut.contains("calorie")) {
                    count++;
                }
                if (count == 2 && nut.contains("calorie")) {
                    count = 0;
                    if(re2.match(nut)){
                        c = re2.split(nut);
                        if(c[0]!=null && c[0]!="0.0"){cal = Double.parseDouble(c[0]);return cal;}
                    }
                }
            }
            index = re.getParenEnd(re.getParenCount()-1);
        }
        return cal;
    }

    /**
     * This takes the strings and finds tags to be assigned to the ingredient and recipe, then places the ingredient into an ArrayList.
     * @param str
     */
    private void tagIngredients(String str){

        double amount=0;
        String amountType = "";

        //This gets the numerical amount for the ingredients
        char[] chars = str.toCharArray();
        for(char c : chars) {
            if(Character.isDigit(c)) {
                amount += Double.parseDouble(String.valueOf(c));
                if(str.contains("⅓")) {
                    amount += (double) 1/3;
                }if(str.contains("½")) {
                    amount += (double) 1/2;
                }
            }
        }

        //This checks for tags of style of measurement
        if(str.contains("cup") || str.contains("cups")){
            amountType = "cups";
        }else if(str.contains("teaspoon") || str.contains("teaspoons")) {
            amountType = "teaspoon";
        }else if(str.contains("tablespoon") || str.contains("tablespoons")){
            amountType = "tablespoons";
        }else if(str.contains("pounds") || str.contains("pound")) {
            amountType = "pounds";
        }else if(str.contains("pint") || str.contains("pints")) {
            amountType = "pints";
        }else if(str.contains("quart") || str.contains("quarts")) {
            amountType = "quarts";
        }else if(str.contains("gallon") || str.contains("gallons")) {
            amountType = "gallons";
        }

        //This gives tags for what kind of food it is.
        if(str.contains("chicken") || str.contains("Chicken")) {
            ingredientTags.add(IngredientTag.Chicken);
            recipeTags.add(RecipeTag.Chicken);
        }else if(str.contains("beef") || str.contains("Beef")) {
            ingredientTags.add(IngredientTag.Beef);
            recipeTags.add(RecipeTag.Beef);
        }else if(str.contains("pork") || str.contains("Pork") || str.contains("bacon") || str.contains("Bacon")) {
            ingredientTags.add(IngredientTag.Pork);
            recipeTags.add(RecipeTag.Pork);
        }else if(str.contains("flour") || str.contains("Flour")) {
            ingredientTags.add(IngredientTag.Gluten);
            recipeTags.add(RecipeTag.Gluten);
        }else if(str.contains("crab") || str.contains("Crab") || str.contains("shrimp") || str.contains("Shrimp") || str.contains("lobster") || str.contains("Lobster")
                || str.contains("clam") || str.contains("Clam") || str.contains("Oyster") || str.contains("oyster") || str.contains("mussel") || str.contains("Mussel")
                || str.contains("Crayfish") || str.contains("crayfish")) {
            ingredientTags.add(IngredientTag.Shellfish);
            recipeTags.add(RecipeTag.Shellfish);
        }else if(str.contains("salmon") || str.contains("Salmon") || str.contains("tuna") || str.contains("Tuna") || str.contains("Trout") || str.contains("trout")
                || str.contains("halibut") || str.contains("Halibut") || str.contains("Cod") || str.contains("cod") || str.contains("Sardines") || str.contains("sardines")
                || str.contains("Anchovie") || str.contains("anchovie")) {
            ingredientTags.add(IngredientTag.Fish);
            recipeTags.add(RecipeTag.Fish);
        }else if(str.contains("milk") || str.contains("Milk") || str.contains("cream") || str.contains("Cream") || str.contains("cheese") || str.contains("Cheese")){
            ingredientTags.add(IngredientTag.Dairy);
            recipeTags.add(RecipeTag.Dairy);
        }else if(str.contains("egg") || str.contains("Egg")){
            ingredientTags.add(IngredientTag.Eggs);
            recipeTags.add(RecipeTag.Eggs);
        }

        //Dietary restriction tag addition
        if(!ingredientTags.contains(IngredientTag.Chicken) && !ingredientTags.contains(IngredientTag.Beef) && !ingredientTags.contains(IngredientTag.Pork) && !ingredientTags.contains(IngredientTag.Eggs)
                && !ingredientTags.contains(IngredientTag.Dairy)) {
            ingredientTags.add(IngredientTag.Vegan);
            ingredientTags.add(IngredientTag.Vegetarian);
        }else if(!ingredientTags.contains(IngredientTag.Gluten)) {
            ingredientTags.add(IngredientTag.Gluten_Free);
        }else if(!ingredientTags.contains(IngredientTag.Dairy)){
            ingredientTags.add(IngredientTag.Dairy_Free);
        }
        if(!recipeTags.contains(RecipeTag.Chicken) && !recipeTags.contains(RecipeTag.Beef) && !recipeTags.contains(RecipeTag.Pork) && !recipeTags.contains(RecipeTag.Eggs) && !recipeTags.contains(RecipeTag.Dairy)) {
            recipeTags.add(RecipeTag.Vegan);
            recipeTags.add(RecipeTag.Vegetarian);
        }else if(!recipeTags.contains(RecipeTag.Gluten)){
            recipeTags.add(RecipeTag.Gluten_Free);
        }else if(!recipeTags.contains(RecipeTag.Dairy)){
            recipeTags.add(RecipeTag.Dairy_Free);
        }

        //Puts all of these factors into a RecipeIngredient object then into the ArrayList
        Ingredient ing = new Ingredient(str,ingredientTags);
        RecipeIngredient ingredient = new RecipeIngredient(amount, amountType, ing);
        ingredients.add(ingredient);
    }
}
