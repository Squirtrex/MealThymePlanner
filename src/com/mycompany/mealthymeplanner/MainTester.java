package com.mycompany.mealthymeplanner;
import java.util.*;

public class MainTester {

    public static void main(String[] args) {

        User testUser = new User();
        ArrayList<String> someAllergies = new ArrayList<>();
        someAllergies.add("def");
        testUser.addRestrictedIngredient("ABC");
        testUser.setRestrictedIngredients(someAllergies);
        System.out.println("Here: " + testUser.getRestrictedIngredients());

    }

}
