/**
 * @author Nicholas Pepin
 */

package com.mycompany.mealthymeplanner;

/**
 * These are the imports I used for the database creation
 */
import com.codename1.io.*;
import com.codename1.io.Externalizable;
import com.codename1.ui.*;
import java.io.*;
import java.util.*;
import java.util.regex.*;

/**
 * This class reads a .csv file into a HashMap and stores it in the App's memory for faster loading and referencing.
 */
public class CSV_to_HashMap implements Externalizable {

    /**
     * These are variables used by Externalizable.
     */
    private static final int VERSION = 0;
    private Date startedAt;
    private String Name;

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

        String[] files = {"/African.csv","/American.csv","/MiddleEastern.csv","/Asian.csv","/French.csv","/Italian.csv","/Mexican.csv"};

        try {
            for (String file : files) {
                InputStream is = Display.getInstance().getResourceAsStream(this.getClass(), file);
                if(file.contains("African")){recipeTags.add(RecipeTag.African); }
                else if(file.contains("American")){recipeTags.add(RecipeTag.American); }
                else if(file.contains("MiddleEastern")){recipeTags.add(RecipeTag.Middle_Eastern); }
                else if(file.contains("Asian")){recipeTags.add(RecipeTag.Asian); }
                else if(file.contains("French")){recipeTags.add(RecipeTag.French); }
                else if(file.contains("Italian")){recipeTags.add(RecipeTag.Italian); }
                else if(file.contains("Mexican")){recipeTags.add(RecipeTag.Mexican); }
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
        Pattern p = Pattern.compile("'([^']*)'");
        Matcher m = p.matcher(text);
        while(m.find()){
            ingredient = m.group();
            ingredient = ingredient.replaceAll("'","");
            tagIngredients(ingredient);
        }
    }

    /**
     * This method parses the steps and puts them into the steps ArrayList.
     * @param text
     */
    private void parseSteps(String text){
        String step = "";
        Pattern p = Pattern.compile("'([^']*)'");
        Matcher m = p.matcher(text);
        while(m.find()){
            step = m.group();
            step = step.replaceAll("'","");
            steps.add(step);
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
        int count = 0;
        Pattern p = Pattern.compile("'([^']*)'");
        Matcher m = p.matcher(text);
        while(m.find()){
            nut = m.group();
            if(nut.contains("calorie")){
                count++;
            }
            if(count==2 && nut.contains("calorie")){
                count=0;
                Matcher m2 = Pattern.compile("(?!=\\d\\.\\d\\.)([\\d.]+)").matcher(nut);
                m2.find();
                cal = Double.parseDouble(m2.group());
            }
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

    /**
     * Necessary methods for for Externalize. The rest of these are for cacheing the HashMap into codename One's Storage.
     * @return
     */
    public int getVersion(){ return VERSION; }

    public void externalize(DataOutputStream out) throws IOException {
        Util.writeUTF(Name, out);
        Util.writeObject(recipes, out);
        if(startedAt != null) {
            out.writeBoolean(true);
            out.writeLong(startedAt.getTime());
        } else {
            out.writeBoolean(false);
        }
    }

    public void internalize(int version, DataInputStream in) throws IOException {
        Name = Util.readUTF(in);
        recipes = (HashMap<String, Recipe>)Util.readObject(in);
        if(version > 1) {
            boolean hasDate = in.readBoolean();
            if(hasDate) {
                startedAt = new Date(in.readLong());
            }
        }
    }

    public String getObjectId(){
        return "CSV_to_HashMap";
    }

}
