package Scs.entities.ingredients;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import Scs.Exceptions.NotFoundException;

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

	public Page<Ingredient> findAll(int page, String order) {
		Pageable pagina = PageRequest.of(page, 10, Sort.by(order));
		return ingredientRepo.findAll(pagina);
	}
	
	public Ingredient findById(Long id) throws NotFoundException {
		return ingredientRepo.findById(id).orElseThrow(() -> new NotFoundException(id));
	}
	
	public Ingredient findByIdAndUpdate(Long id, IngredientPayload body) throws NotFoundException {
		Ingredient ingredientFound = this.findById(id);
		ingredientFound.setName(body.getName());
		ingredientFound.setQuantity(body.getQuantity());
		return ingredientRepo.save(ingredientFound);
	}
	
	public void findByIdAndDelete(Long id) throws NotFoundException {
		Ingredient ingredientFound = this.findById(id);
		ingredientRepo.delete(ingredientFound);
	}
}
