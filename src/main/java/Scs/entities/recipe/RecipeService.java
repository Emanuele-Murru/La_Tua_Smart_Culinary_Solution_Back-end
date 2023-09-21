package Scs.entities.recipe;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import Scs.Exceptions.NotFoundException;
import Scs.entities.ingredients.Ingredient;
import Scs.entities.ingredients.IngredientRepository;

@Service
public class RecipeService {

	@Autowired
	private RecipeRepository recipeRepo;
	
	@Autowired
	private IngredientRepository ingredientRepo;

	public Recipe createRecipe(NewRecipePayload body) throws NotFoundException {

		List<Ingredient> ingredients = new ArrayList<>();
		
		for(Ingredient ingredientP : body.getIngredients()) {
			
			Ingredient ingredient = new Ingredient();
			ingredient.setName(ingredientP.getName());
			ingredient.setQuantity(ingredientP.getQuantity());
			ingredients.add(ingredient);
		
		ingredientRepo.save(ingredient);
		
		}
		Recipe newRecipe = new Recipe();

		newRecipe.setTitle(body.getTitle());
		newRecipe.setCategory(body.getCategory());
		newRecipe.setInstructions(body.getInstructions());
		newRecipe.setPrepTime(body.getPrepTime());
		newRecipe.setCookTime(body.getCookTime());
		newRecipe.setServings(body.getServings());
		newRecipe.setImageUrl(body.getImageUrl());

		newRecipe.setIngredients(ingredients);

		recipeRepo.save(newRecipe);

		return newRecipe;

	}

	public List<Recipe> find() {
		return recipeRepo.findAll();
	}

	public Page<Recipe> findAll(int page, String sort) {
		Pageable pageable = PageRequest.of(page, 10, Sort.by(sort));
		return recipeRepo.findAll(pageable);
	}

	public Recipe findById(Long id) throws NotFoundException {
		return recipeRepo.findById(id).orElseThrow(() -> new NotFoundException(id));
	}

	public Recipe findByIdAndUpdate(Long id, NewRecipePayload body) throws NotFoundException {
		Recipe recipeFound = this.findById(id);
		recipeFound.setTitle(body.getTitle());
		recipeFound.setCategory(body.getCategory());
		recipeFound.setInstructions(body.getInstructions());
		recipeFound.setPrepTime(body.getPrepTime());
		recipeFound.setCookTime(body.getCookTime());
		recipeFound.setServings(body.getServings());
		recipeFound.setImageUrl(body.getImageUrl());
		recipeFound.setIngredients(body.getIngredients());
		return recipeRepo.save(recipeFound);
	}

	public void findByIdAndDelete(Long id) throws NotFoundException {
		Recipe recipeFound = this.findById(id);
		recipeRepo.delete(recipeFound);
	}
	
	public List<Recipe> searchRecipesByIngredients(List<String> ingredientNames) {
	    return recipeRepo.findByIngredientsNameIn(ingredientNames);
	}


}
