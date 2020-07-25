package fr.clement.entities;

import java.util.ArrayList;

public class RecetteWrapper {
	
	private ArrayList<Recette> recettes;
	
	public RecetteWrapper() {	
		this.recettes=new ArrayList<Recette>();
	}
	
	public void addRecette(Recette recette) {
		this.recettes.add(recette);
	}
	
	public void displayAllRecette() {
		for(Recette e : this.recettes) {
			System.out.println(e.getLabelRecette());
			System.out.println(e.getReceipeNutriments());
		}
	}
}
