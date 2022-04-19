/**
 * @author Nicholas Pepin
 */

package com.mycompany.mealthymeplanner;

import java.util.*;
import java.io.*;
import java.net.*;

public class CSV_to_HashMap {
	
	//These are the ArrayLists I used for storage
	ArrayList<RecipeIngredient> ingredients = new ArrayList<RecipeIngredient>();
	ArrayList<IngredientTag> ingredientTags = new ArrayList<IngredientTag>();
	ArrayList<String> steps = new ArrayList<String>();
	HashMap<String, Recipe> recipes = new HashMap<String, Recipe>();
	
	//This is a method I made to make the ingredients ArrayList
	public ArrayList<RecipeIngredient> Ing (String str){
		
		double amount=0;
		String amountType = "";
		
		//This gets the numerical amount for the ingredients
		char[] chars = str.toCharArray();
		for(char c : chars) {
			if(Character.isDigit(c)) {
				amount += Double.valueOf(String.valueOf(c));
				if(str.contains("⅓")) {
					amount += (double) 1/3; 
				}if(str.contains("½")) {
					amount += (double) 1/2;
				}
			}
		}
		
		//This checks for tags
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
		}else if(str.contains("beef") || str.contains("Beef")) {
			ingredientTags.add(IngredientTag.Beef);
		}else if(str.contains("pork") || str.contains("Pork") || str.contains("bacon") || str.contains("Bacon")) {
			ingredientTags.add(IngredientTag.Pork);
		}else if(str.contains("flour") || str.contains("Flour")) {
			ingredientTags.add(IngredientTag.Gluten);
		}else if(str.contains("crab") || str.contains("Crab") || str.contains("shrimp") || str.contains("Shrimp") || str.contains("lobster") || str.contains("Lobster")
				|| str.contains("clam") || str.contains("Clam") || str.contains("Oyster") || str.contains("oyster") || str.contains("mussel") || str.contains("Mussel")
				|| str.contains("Crayfish") || str.contains("crayfish")) {
			ingredientTags.add(IngredientTag.Shellfish);
		}else if(str.contains("salmon") || str.contains("Salmon") || str.contains("tuna") || str.contains("Tuna") || str.contains("Trout") || str.contains("trout")
				|| str.contains("halibut") || str.contains("Halibut") || str.contains("Cod") || str.contains("cod") || str.contains("Sardines") || str.contains("sardines")
				|| str.contains("Anchovie") || str.contains("anchovie")) {
			ingredientTags.add(IngredientTag.Fish);
		}
		if(!ingredientTags.contains(IngredientTag.Chicken) && !ingredientTags.contains(IngredientTag.Beef) && !ingredientTags.contains(IngredientTag.Pork)) {
			ingredientTags.add(IngredientTag.Vegan);
			ingredientTags.add(IngredientTag.Vegetarian);
		}else if(!ingredientTags.contains(IngredientTag.Gluten)) {
			ingredientTags.add(IngredientTag.Gluten_Free);
		}
		
		//Puts all of these factors into a RecipeIngredient object then into the ArrayList
		Ingredient ing = new Ingredient(str,ingredientTags);
		RecipeIngredient ingredient = new RecipeIngredient(amount, amountType, ing);
		ingredients.add(ingredient);
		
		return ingredients;
	}
	
	public static void main(String[] args) throws IOException, URISyntaxException{
		//This allows the class to use other methods inside of the file
		CSV_to_HashMap hm = new CSV_to_HashMap();
		
		String name = "";
		float cookTime;
		String servingSize;
		String imgpath = null;
		ArrayList<RecipeIngredient> In;
		
		URL path = ClassLoader.getSystemResource("Welsh.csv");
		File file = new File(path.toURI());
		
		//Find a way to read from Data folder universally.
		//File dir = new File("/eclipse-workspace/Meal Prep App/src/Data");
		//File[] files = dir.listFiles();
		//for(File file : files) {
			//if(file.isFile()) {
				try (BufferedReader br = new BufferedReader(new FileReader(file))) {
				    String line="";
				    String sub ="";
				    
				    int iend = 0;
				    
				    //ignored first line
				    br.readLine();
				    //Loops through the csv file
				    while ((line = br.readLine()) != null) {
				    	//Goes from comma to comma
				    	iend = line.indexOf(",");
				    	if(iend != -1) {
				    		//Finds the name of the recipe and trims the fat.
				    		name = line.substring(0, iend);
				    		name = name.replaceAll("\"", "");
				    		name = name.trim();
				    		//Moves to next comma
				    		line = line.substring(line.indexOf(",")+1);
				    		//Finds the cook time of the recipe
				    		cookTime = Float.parseFloat(line.substring(0, line.indexOf(",")));
				    		//moves to the next comma
				    		line = line.substring(line.indexOf(",")+1);
				    		//Finds the serving size
				    		servingSize = line.substring(0,line.indexOf(","));
				    		servingSize = servingSize.trim();
				    		//Moves to the next single-quote
				    		line = line.substring(line.indexOf("'")+1);
				    		//Loops through the ingredients and parses out the ingredients
				    		while(!sub.contains("]")) {
				    			sub = line.substring(0,line.indexOf("'"));
				    			sub = sub.replaceAll("\"", "");
				    			sub = sub.replaceAll(",", "");
				    			line = line.substring(line.indexOf("'")+1);
				    			//Takes the str of the line of the ingredient and passes it onto the Ing method.
				    			hm.Ing(sub);
				    		}
				    		sub = line.substring(0,line.indexOf("'"));
				    		sub = sub.replaceAll("\"", "");
			    			sub = sub.replaceAll(",", "");
				    		hm.steps.add(sub);
				    		line = line.substring(line.indexOf("'")+1);
				    		while(!sub.contains("]")) {
				    			sub = line.substring(0,line.indexOf("'"));
				    			sub = sub.replaceAll("\"", "");
				    			sub = sub.replaceAll(",", "");
				    			line = line.substring(line.indexOf("'")+1);
				    			hm.steps.add(sub);
				    		}
				    		imgpath = sub.replaceAll("\\{", "").replaceAll("\\]", "");
				    		sub = line.substring(0,line.indexOf(","));
				    		/**
				    		 * If we want to implement Nutrition label, this is where we do it
				    		 *
				    		 *
				    		 *
				    		 *
				    		 */
				    		//Creates recipe object out of the data.
				    		Recipe recipe = new Recipe(name, hm.ingredients, hm.steps, servingSize, cookTime, hm.ingredientTags);
				    		//Puts recipe object into HashMap using title as key.
				    		hm.recipes.put(name,recipe);
				    	}
				    }
				}catch(IOException e) {
					System.out.println(e);
				}
			}
		//}
	//}
}

