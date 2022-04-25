/**
 * @author Nicholas Pepin
 */
package com.mycompany.mealthymeplanner;

/**
 * These are the imports I used for the database creation
 */
import com.codename1.io.*;
import com.codename1.ui.*;
import java.io.*;
import java.util.*;

/**
 * This class reads a .csv file into a HashMap and stores it in the App's memory for faster loading and referencing.
 */
public class CSV_to_HashMap extends HashMap<String, Recipe> {

    /**
     * Storage structures used.
     */
    ArrayList<RecipeIngredient> I;
    ArrayList<IngredientTag> it;
    ArrayList<RecipeTag> rt;
    ArrayList<String> s;

    HashMap<String, Recipe> recipes = new HashMap<>();

    /**
     * This is the constructor that loads all the csv files into string arrays.
     * IMPORTANT: THE .CSV FILES MUST BE IN THE /src DIRECTORY, NOT THE /src/Tester DIRECTORY.
     * @throws IOException If the file cannot be opened, this is thrown
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
                CSVParser parser = new CSVParser();
                String[][] data = parser.parse(is);
                for (int i = 1; i < data.length; i++) {
                    this.rt = new ArrayList<RecipeTag>();
                    this.I = new ArrayList<RecipeIngredient>();
                    if(file.contains("American")){ rt.add(RecipeTag.American); }
                    else if(file.contains("Italian")){rt.add(RecipeTag.Italian); }
                    else if(file.contains("Mexican")){rt.add(RecipeTag.Mexican); }
                    else if(file.contains("MiddleEastern")){ rt.add(RecipeTag.Middle_Eastern); }
                    else if(file.contains("Asian")){ rt.add(RecipeTag.Asian); }
                    else if(file.contains("African")){ rt.add(RecipeTag.African); }
                    for (int j = 0; j < data[i].length; j++) {
                        switch (j) {
                            case 0: name = data[i][j]; break;
                            case 1: cookTime = Float.parseFloat(data[i][j]); break;
                            case 2: servingSize = data[i][j]; break;
                            case 3: placeholder = data[i][j]; parseIngredients(placeholder); break;
                            case 4: placeholder = data[i][j]; parseSteps(placeholder); break;
                            case 5: imgpath = data[i][j]; break;
                            case 6: placeholder = data[i][j]; calories = parseCalories(placeholder); break;
                        }
                    }
                    Recipe recipe = new Recipe(name, I, s, servingSize, calories, cookTime, rt);
                    recipes.put(name, recipe);
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
     * @return recipes the hashmap of recipe objects
     */
    HashMap<String, Recipe> getHashMap(){ return recipes; }

    /**
     * This is a tester method to test if the hashmap is holding the recipe objects from all of the .csvs.
     */
    private void tester(){
        System.out.println("EastAfrican recipes: "+recipes.containsKey("Roasted Okra"));
        Recipe r = recipes.get("Roasted Okra");
        System.out.println("Name: "+r.getName());
        for(RecipeIngredient ri : r.getIngredients()) {
            Ingredient ing = ri.getIngredient();
            System.out.println("Ingredients: " + ing.getName());
        }
        System.out.println(r.getRecipeTags());
    }

    /**
     * This takes the ingredient as a string and it parses out the unnecessary stuff.
     * @param text the string to be parsed
     */
    private void parseIngredients(String text){
        ArrayList<String> splitArr = new ArrayList<>();
        StringTokenizer arr = new StringTokenizer(text,"'");
        while(arr.hasMoreTokens()){
            splitArr.add(arr.nextToken());
        }
        String[] array = splitArr.toArray(new String[splitArr.size()]);
        for(String s : array){
            s=s.replace("]","");
            s=s.replace("[","");
            s=s.replace("\"","");
            s=s.replace("'","");
            if(!s.equals(", ")){
                tagIngredients(s);
            }
        }
    }

    /**
     * This method parses the steps and puts them into the steps ArrayList.
     * @param text the string to be parsed
     */
    private void parseSteps(String text){
        this.s = new ArrayList<>();
        ArrayList<String> splitArr = new ArrayList<>();
        StringTokenizer arr = new StringTokenizer(text,"'");
        while(arr.hasMoreTokens()){
            splitArr.add(arr.nextToken());
        }
        String[] array = splitArr.toArray(new String[splitArr.size()]);
        int i = 0;
        String prevStr = "";
        for(String str : array){
            if(str.length()>2){
                str=str.replaceAll("\\\\r\\\\n|\\\\r|\\\\n","");
                str=str.replace("]","");
                str=str.replace("[","");
                str=str.replace("\"","");
                str=str.replace("'","");
                str = str.trim();
                if(str.startsWith(", ")){ str = str.substring(2); }
                prevStr = str;
                if(!str.startsWith("Step") && i!=0){
                    str = prevStr.concat(str);
                    s.set(i-1,str);
                }
                s.add(str);
                i++;
            }
        }
    }

    /**
     * This method pulls the double value associated with the calorie value of the recipe.
     * @param text the string to be parsed
     * @return cal is the double that contains the amount of calories
    */
    private double parseCalories(String text){
        ArrayList<String> splitArr = new ArrayList<>();
        StringTokenizer arr = new StringTokenizer(text," ");
        while(arr.hasMoreTokens()){
            splitArr.add(arr.nextToken());
        }
        String[] array = splitArr.toArray(new String[splitArr.size()]);
         if (array.length < 2) {
            return 0;
         }
         String str = array[1];
         str = str.trim();
         str = str.substring(1);
         return Double.parseDouble(str);
    }


    /**
     * This takes the strings and finds tags to be assigned to the ingredient and recipe, then places the ingredient into an ArrayList.
     * @param text is the string to be parsed
     */
    private void tagIngredients(String text){
        this.it = new ArrayList<>();
        String[] arr = text.split(" ");
        String str = "";
        String amountType = "";
        double dub = 0;
        for (String value : arr) {
            try {
                if (value.contains("⅓")) {
                    dub += (double) 1 / 3;
                }if (value.contains("½")) {
                    dub += (double) 1 / 2;
                }if (value.contains("¼")) {
                    dub += (double) 1 / 4;
                }if (value.contains("⅛")) {
                    dub += (double) 1 / 8;
                }if (value.contains("¾")) {
                    dub += (double) 3 / 4;
                }if (value.contains("⅔")) {
                    dub += (double) 2 / 3;
                }
                dub += Double.parseDouble(value);
            } catch (NumberFormatException nfe) {
                str = str.concat(value + " ");
            }
        }
        str = str.trim();

        //This checks for tags of style of measurement
        if(str.contains("cup") || str.contains("Cup")){
            amountType = "cups";
        }else if(str.contains("ounce") || str.contains("Ounce")) {
            amountType = "ounces";
        }else if(str.contains("teaspoon") || str.contains("Teaspoon")) {
            amountType = "teaspoon";
        }else if(str.contains("tablespoon") || str.contains("Tablespoon")){
            amountType = "tablespoons";
        }else if(str.contains("pound") || str.contains("Pound")) {
            amountType = "pounds";
        }else if(str.contains("pint") || str.contains("Pint")) {
            amountType = "pints";
        }else if(str.contains("quart") || str.contains("Quart")) {
            amountType = "quarts";
        }else if(str.contains("gallon") || str.contains("Gallon")) {
            amountType = "gallons";
        }

        //This gives tags for what kind of food it is.
        if(text.contains("chicken") || text.contains("Chicken")) {
            if(!it.contains(IngredientTag.Chicken)){it.add(IngredientTag.Chicken);}
            if(!rt.contains(RecipeTag.Chicken)){rt.add(RecipeTag.Chicken);}
        }else if(text.contains("beef") || text.contains("Beef")) {
            if(!it.contains(IngredientTag.Beef)){it.add(IngredientTag.Beef);}
            if(!rt.contains(RecipeTag.Beef)){rt.add(RecipeTag.Beef);}
        }else if(text.contains("pork") || text.contains("Pork") || text.contains("bacon") || text.contains("Bacon")) {
            if(!it.contains(IngredientTag.Pork)){it.add(IngredientTag.Pork);}
            if(!rt.contains(RecipeTag.Pork)){rt.add(RecipeTag.Pork);}
        }else if(text.contains("flour") || text.contains("Flour")) {
            if(!it.contains(IngredientTag.Gluten)){it.add(IngredientTag.Gluten);}
            if(!rt.contains(RecipeTag.Gluten)){rt.add(RecipeTag.Gluten);}
        }else if(text.contains("crab") || text.contains("Crab") || text.contains("shrimp") || text.contains("Shrimp") || text.contains("lobster") || text.contains("Lobster")
                || text.contains("clam") || text.contains("Clam") || text.contains("Oyster") || text.contains("oyster") || text.contains("mussel") || text.contains("Mussel")
                || text.contains("Crayfish") || text.contains("crayfish")) {
            if(!it.contains(IngredientTag.Shellfish)){it.add(IngredientTag.Shellfish);}
            if(!rt.contains(RecipeTag.Shellfish)){rt.add(RecipeTag.Shellfish);}
        }else if(text.contains("salmon") || text.contains("Salmon") || text.contains("tuna") || text.contains("Tuna") || text.contains("Trout") || text.contains("trout")
                || text.contains("halibut") || text.contains("Halibut") || text.contains("Cod") || text.contains("cod") || text.contains("Sardines") || text.contains("sardines")
                || text.contains("Anchovie") || text.contains("anchovie")) {
            if(!it.contains(IngredientTag.Fish)){it.add(IngredientTag.Fish);}
            if(!rt.contains(RecipeTag.Fish)){rt.add(RecipeTag.Fish);}
        }else if(text.contains("milk") || text.contains("Milk") || text.contains("cream") || text.contains("Cream") || text.contains("cheese") || text.contains("Cheese")){
            if(!it.contains(IngredientTag.Dairy)){it.add(IngredientTag.Dairy);}
            if(!rt.contains(RecipeTag.Dairy)){rt.add(RecipeTag.Dairy);}
        }else if(text.contains("egg") || text.contains("Egg")){
            if(!it.contains(IngredientTag.Eggs)){it.add(IngredientTag.Eggs);}
            if(!rt.contains(RecipeTag.Eggs)){rt.add(RecipeTag.Eggs);}
        }

        //Dietary restriction tag addition
        if(!it.contains(IngredientTag.Chicken) && !it.contains(IngredientTag.Beef) && !it.contains(IngredientTag.Pork) && !it.contains(IngredientTag.Eggs) && !it.contains(IngredientTag.Dairy)) {
            if(!it.contains(IngredientTag.Vegan) && !it.contains(IngredientTag.Vegetarian)){it.add(IngredientTag.Vegan); it.add(IngredientTag.Vegetarian);}
        }else if(!it.contains(IngredientTag.Gluten) && !it.contains(IngredientTag.Gluten_Free)) {
            it.add(IngredientTag.Gluten_Free);
        }else if(!it.contains(IngredientTag.Dairy) && !it.contains(IngredientTag.Dairy_Free)){
            it.add(IngredientTag.Dairy_Free);
        }
        if(!rt.contains(RecipeTag.Chicken) && !rt.contains(RecipeTag.Beef) && !rt.contains(RecipeTag.Pork) && !rt.contains(RecipeTag.Eggs) && !rt.contains(RecipeTag.Dairy)) {
            if(!rt.contains(RecipeTag.Vegan) && !rt.contains(RecipeTag.Vegetarian)){rt.add(RecipeTag.Vegan); rt.add(RecipeTag.Vegetarian);}
        }else if(!rt.contains(RecipeTag.Gluten) && !rt.contains(RecipeTag.Gluten_Free)){
            rt.add(RecipeTag.Gluten_Free);
        }else if(!rt.contains(RecipeTag.Dairy) && !rt.contains(RecipeTag.Dairy_Free)){
            rt.add(RecipeTag.Dairy_Free);
        }

        //Puts all of these factors into a RecipeIngredient object then into the ArrayList
        Ingredient ing = new Ingredient(text,it);
        RecipeIngredient ingredient = new RecipeIngredient(dub, amountType, ing);
        I.add(ingredient);
    }
}
