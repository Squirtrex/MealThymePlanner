package com.mycompany.mealthymeplanner;

public class RecipeIngredient {

    private double amount;
    private String amountType;
    Ingredient ingredient;

    public RecipeIngredient() {
        this(0.0, "No Name", new Ingredient());
    }

    public RecipeIngredient(double amount, String amountType, Ingredient ingredient) {
        this.amount = amount;
        this.amountType = amountType;
        this.ingredient = ingredient;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getAmountType() {
        return amountType;
    }

    public void setAmountType(String amountType) {
        this.amountType = amountType;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

}
