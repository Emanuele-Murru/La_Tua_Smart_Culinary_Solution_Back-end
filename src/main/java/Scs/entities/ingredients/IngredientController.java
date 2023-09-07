package Scs.entities.ingredients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ingredients")
public class IngredientController {

	@Autowired
	IngredientService ingredientSrv;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Ingredient saveIngredient(@RequestBody IngredientPayload body) throws NotFoundException {

		Ingredient ingredientCreated = ingredientSrv.createIngredient(body);

		return ingredientCreated;

	}

	@GetMapping("")
	public Page<Ingredient> findAll(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "name") String order) {
		return ingredientSrv.findAll(page, order);
	}
	
	@GetMapping("/{id}")
	public Ingredient findById(@PathVariable Long id) throws NotFoundException {
		return ingredientSrv.findById(id);
	}

	@PutMapping("/{id}")
	public Ingredient updateCliente(@PathVariable Long id, @RequestBody IngredientPayload body) throws NotFoundException {
		return ingredientSrv.findByIdAndUpdate(id, body);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteById(@PathVariable Long id) throws NotFoundException {
		ingredientSrv.findByIdAndDelete(id);
	}
}
