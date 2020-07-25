package fr.clement.entities;

import java.util.ArrayList;
import java.util.HashMap;


public class Recette {
	private IngredientWrapper ingredientsWrapper;
	private String description;
	private String labelRecette;
	private HashMap<String, Double> receipeNutriments;
	
	public Recette(String labelRecette0, String description0) {
		this.description=description0;
		this.labelRecette=labelRecette0;
		this.ingredientsWrapper=new IngredientWrapper();
		this.receipeNutriments=new HashMap<String, Double>();
		this.receipeNutriments.put("fibre", 0.0);
		this.receipeNutriments.put("kcal",  0.0);
		this.receipeNutriments.put("lipide",  0.0);
		this.receipeNutriments.put("glucide",  0.0);
		this.receipeNutriments.put("poids_gramme",  0.0);
	}
	
	public void setIngredientWrapper(IngredientWrapper ingredientWrapper0) {
		this.ingredientsWrapper=ingredientWrapper0;
	}
	
	public void computeReceipeNutriments(){
		for(Ingredient e : ingredientsWrapper.getIngredients()){
			this.receipeNutriments.put("fibre", e.getFibre()+this.receipeNutriments.get("fibre"));
			this.receipeNutriments.put("kcal", e.getKcal()+this.receipeNutriments.get("kcal"));
			this.receipeNutriments.put("lipide", e.getLipide()+this.receipeNutriments.get("lipide"));
			this.receipeNutriments.put("glucide", e.getGlucide()+this.receipeNutriments.get("glucide"));
			this.receipeNutriments.put("poids_gramme", e.getPortionRecette()+this.receipeNutriments.get("poids_gramme"));
		}
	}
	
	public HashMap<String, Double> getReceipeNutriments() {
		return this.receipeNutriments;
	}
	

	public void setDescription(String description0) {
		this.description=description0;
	}
	
	public String getLabelRecette() {
		return this.labelRecette;
	}
}
