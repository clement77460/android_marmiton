package fr.clement.entities;

import java.util.ArrayList;

public class IngredientWrapper {
	private ArrayList<Ingredient> ingredients;
	
	public IngredientWrapper() {
		this.ingredients=new ArrayList<Ingredient>(); 
	}
	
	public void addIngredient(Ingredient ingredient) {
		this.ingredients.add(ingredient);
	}
	
	public  ArrayList<Ingredient> getIngredients() {
		return this.ingredients;
	}
}
