package fr.clement.entities;

import java.util.HashMap;

public class Ingredient {
	private String label;
	private String portionRecette;



	private String portionNutriments;
	private String kcal;
	private String fibre;
	private String glucide;
	private String lipide;
	
	public Ingredient(String label0, String portion0, HashMap<String, String> dictionnary) {
		this.label=label0;
		this.portionRecette=portion0;
		this.kcal=dictionnary.get("kcal");
		this.fibre=dictionnary.get("fibre");
		this.glucide=dictionnary.get("glucide");
		this.lipide=dictionnary.get("lipide");
		this.portionNutriments=dictionnary.get("portion_moyenne");
	}

	public Ingredient(String label0, String kcal0) {
		this.label=label0;
		this.kcal = kcal0;
	}
	public Double getPortionNutriments() {
		return Double.parseDouble(portionNutriments);
	}
	public Double getKcal() {
		return (Double.parseDouble(this.kcal)*Double.parseDouble(portionRecette))/Double.parseDouble(this.portionNutriments);
	}

	public Double getFibre() {
		return (Double.parseDouble(this.fibre)*Double.parseDouble(portionRecette))/Double.parseDouble(this.portionNutriments);
	}

	public Double getGlucide() {
		return (Double.parseDouble(this.glucide)*Double.parseDouble(portionRecette))/Double.parseDouble(this.portionNutriments);
	}

	public Double getLipide() {
		return (Double.parseDouble(this.lipide)*Double.parseDouble(portionRecette))/Double.parseDouble(this.portionNutriments);
	}
	public Double getPortionRecette() {
		return (Double.parseDouble(this.portionRecette));
	}
	public String getLabel() {
		return this.label;
	}

	@Override
	public String toString() {
		return "Ingredient [label=" + label + " portion= "+portionRecette+ " kcal=" + kcal + ", fibre=" + fibre + ", glucide=" + glucide
				+ ", lipide=" + lipide + "]";
	}
	
}
