package Scs.entities.ingredients;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngredientService {

	@Autowired
	IngredientRepository ingredientRepo;
	
	public Ingredient createIngredient(IngredientPayload body) {
		
		Ingredient newIngredient = new Ingredient(body.getName(), body.getQuantity());
		
		return ingredientRepo.save(newIngredient);
	}
	
	public List<Ingredient> getIngredient() {
		return ingredientRepo.findAll();
	}
	
}
