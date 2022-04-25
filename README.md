# MealThymePlanner
Meal Planning app
CS-321-002: Group 8

Nicholas Pepin:
  My contribution to the project has been creating the back-end hashmap that reads from .csv files and parses out information while using the CodenameOne libraries.
  In order to create the libraries, I made a python web scraper that scraped www.allrecipes.com for the cuisine. My code for that can be found at this link:
  https://colab.research.google.com/drive/15hO9insfRfftJXsiQ6TQs_6gd0nTYNAe?usp=sharing
  Afterwards, I wrote the CSV_to_HashMap file, I wrote a handful of helper methods in the MealThymePlanner.java file to load the hashmap into there, I added some
  allergen tags to the ingredientTags and recipeTags. The code has been written to be modular, allow easy changes throughif you want to add or remove csv files you only
  need to take them out of the /src directory and remove the corresponding "/xxxx.csv" out of the String[] files array. I helped make the search capability of the app.
