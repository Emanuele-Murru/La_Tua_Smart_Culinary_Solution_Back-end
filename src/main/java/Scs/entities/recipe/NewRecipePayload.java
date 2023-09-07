package Scs.entities.recipe;

import java.util.List;

import Scs.entities.ingredients.Ingredient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class NewRecipePayload {
	
	private String title;
	private String category;
	private String instructions;
	private String prepTime;
	private String cookTime;
	private int servings;
	private List<Ingredient> ingredients;
	
}
