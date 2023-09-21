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
	private RecipeCategory category;
	private String instructions;
	private String prepTime;
	private String cookTime;
	private int servings;
	private String imageUrl;
	private List<Ingredient> ingredients;
	
}
