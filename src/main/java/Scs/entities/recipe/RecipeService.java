package Scs.entities.recipe;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class RecipeService {
	
	@Autowired
	private RecipeRepository recipeRepo;
	
	public Recipe createRecipe(NewRecipePayload body) throws Exception {
		
		Recipe newRecipe = new Recipe();
		
		newRecipe.setTitle(body.getTitle());
		newRecipe.setCategory(body.getCategory());
		newRecipe.setInstructions(body.getInstructions());
		newRecipe.setPrepTime(body.getPrepTime());
		newRecipe.setCookTime(body.getCookTime());
		newRecipe.setServings(body.getServings());
		newRecipe.setIngredients(body.getIngredients());
		
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
	
}
