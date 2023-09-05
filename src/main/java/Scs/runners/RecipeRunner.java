package Scs.runners;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;

import Scs.entities.ingredients.Ingredient;
import Scs.entities.recipe.NewRecipePayload;
import Scs.entities.recipe.Recipe;
import Scs.entities.recipe.RecipeRepository;
import Scs.entities.recipe.RecipeService;

@Order(value = 0)
@Component
public class RecipeRunner implements CommandLineRunner{

	@Autowired
	RecipeRepository recipeRepo;
	
	@Autowired
	RecipeService recipeSrv;
	
	@Override
	public void run(String... args) throws Exception {
		Faker faker = new Faker(new Locale("it"));
		
		List<Recipe> recipesDb = recipeRepo.findAll();
		
		List<Ingredient> ingredients = new ArrayList<>();
		
		if(recipesDb.isEmpty()) {
			
			for (int i = 0; i < 10; i++) {
				String title = faker.food().dish();
				String category = faker.food().vegetable();
				String instructions = faker.lorem().paragraph();
				String prepTime = faker.number().toString();
				String cookTime = faker.number().toString();
				String quantity = faker.food().measurement();
				
				Ingredient ingredient = new Ingredient();
				ingredient.setName(faker.food().ingredient());
				ingredient.setQuantity(faker.number().randomDigitNotZero() + "" + faker.food().measurement());
				
				ingredients.add(ingredient);
				
				NewRecipePayload newRecipe = new NewRecipePayload(title, category, instructions, prepTime, cookTime, quantity, ingredients);
				recipeSrv.createRecipe(newRecipe);
			}
		}
	}
	
}
