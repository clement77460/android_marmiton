package fr.clement.engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import org.w3c.dom.Element;

import fr.clement.entities.Ingredient;
import fr.clement.entities.IngredientWrapper;
import fr.clement.entities.Recette;
import fr.clement.entities.RecetteWrapper;
import fr.clement.parserWidget.XmlDocument;
import fr.clement.parserWidget.XmlParser;

public class Engine {
	private XmlDocument xmlDocument;
	
	public Engine() {
		this.xmlDocument=new XmlDocument();
	}
	
	
	public Ingredient getIngredient(String lablelIngredient, String portion) {
		/**
		 * Function to return information of an ingredient that us used in a receipe
		 * 
		 */
		xmlDocument.readXML("./data/ingredients.xml");
		XmlParser<String, String> parser=new XmlParser<String,String>(xmlDocument.getDocument());
		Element element=parser.findParentElement(lablelIngredient);
		return new Ingredient(lablelIngredient, portion, parser.findSubElement(element, "nutriments"));
	}
	
	public Recette buildSpecificRecette(String labelRecette, Element element) {
		/**
		 * Function to build a recette with all of his ingredients
		 */
		xmlDocument.readXML("./data/recettes.xml");
		XmlParser<String, String> parser=new XmlParser<String,String>(xmlDocument.getDocument());
		
		
		String description=parser.getSpecificValueForElement(element, "description");//description recette
		HashMap<String, String> ingredients=parser.findSubElement(element, "details");//totalite ingredients
		
		
		Recette recette=new Recette(labelRecette, description);
		recette.setIngredientWrapper(this.fillRecetteWithIngredients(ingredients));
		recette.computeReceipeNutriments(); //on 
		return recette;
		
	}
	
	public IngredientWrapper fillRecetteWithIngredients(HashMap<String, String> ingredients) {
		/**
		 * Function to fill a receipe with his ingedients
		 */
		IngredientWrapper ingredientWrapper=new IngredientWrapper();
	
		for(Entry<String, String> entry : ingredients.entrySet()) {
			String cle = entry.getKey();
			String value = entry.getValue();
			ingredientWrapper.addIngredient(this.getIngredient(cle, value));
			
		}
		return ingredientWrapper;
	}
	
	public void initApp() {
		HashMap<String, Element> recettes=new HashMap<String, Element>();
		
		this.getAllReceipe(recettes); //a modifier pour meilleure encapsulation
		
		RecetteWrapper recetteWrapper=this.computeEveryReceipes(recettes);
		recetteWrapper.displayAllRecette();
	}
	
	private RecetteWrapper computeEveryReceipes(HashMap<String, Element> recettes) {
		/**
		 * Function to skim each receipe
		 */
		RecetteWrapper recetteWrapper=new RecetteWrapper();
		
		for(Entry<String, Element> entry : recettes.entrySet()) {
			String cle = entry.getKey(); //name of the receipe
			recetteWrapper.addRecette(this.buildSpecificRecette(cle, entry.getValue()));
			
		}
		
		return recetteWrapper;
	}
	
	public void getAllReceipe(HashMap<String, Element> recettes) {
		/**
		 * Function to insert all receipe in a dictionnary
		 * Key : name of the receipe
		 * Value : reference to the Element <Recette>
		 */
		xmlDocument.readXML("./data/recettes.xml");
		XmlParser<String, Element> parser=new XmlParser<String,Element>(xmlDocument.getDocument());	
		parser.parcourirElemAndFillDictionnary(xmlDocument.getDocument().getDocumentElement().getChildNodes(), recettes, 1);
		//a modifier pour meilleure encapsulation
	}

	public boolean isExisting(String label, int option) {
		/**
		 * Regarde si l'Element existe deja dans le fichier xml
		 * 0: On regarde dans les ingredients
		 * 1: On regarde dans les recettes
		 */
		
		if(option==0)
			xmlDocument.readXML("./data/ingredients.xml");
		else
			xmlDocument.readXML("./data/recettes.xml");
		
		XmlParser<String, String> parser=new XmlParser<String,String>(xmlDocument.getDocument());
		Element element=parser.findParentElement(label);
		
		if(element==null)
			return false;
		return true;
	}
	
	public void addElementIfNotExisting(String label, int option) {
		if(!isExisting(label,option)) {
			System.out.println("n'existe pas donc on ajoute !");
			
		}
	}
}
