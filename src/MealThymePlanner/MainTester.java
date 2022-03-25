package MealThymePlanner;

public class MainTester {

    public static void main(String[] args) {
        
        Ingredient a = new Ingredient();
        IngredientTag tag1 = IngredientTag.Spicy ;
        a.addTag(tag1);
        a.addTag(IngredientTag.Vegan);
        System.out.println(a.getTags());
        


    }

}
