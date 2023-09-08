package Scs.entities.recipe;

import org.springframework.beans.factory.annotation.Autowired;
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

import Scs.Exceptions.BadRequestException;
import Scs.Exceptions.NotFoundException;

@RestController
@RequestMapping("/recipes")
public class RecipeController {

	@Autowired
	private RecipeService recipeSrv;
	
	@PostMapping(consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Recipe saveRecipe(@RequestBody NewRecipePayload body) throws BadRequestException {
		Recipe recipeCreated = recipeSrv.createRecipe(body);
		return recipeCreated;
	}
	
	@GetMapping("")
	public Page<Recipe> findAll(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "id") String order) {
		return recipeSrv.findAll(page, order);
	}
	
	@GetMapping("/{id}")
	public Recipe findById(@PathVariable Long id) throws NotFoundException {
		return recipeSrv.findById(id);
	}

	@PutMapping("/{id}")
	public Recipe updateCliente(@PathVariable Long id, @RequestBody NewRecipePayload body) throws NotFoundException {
		return recipeSrv.findByIdAndUpdate(id, body);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteById(@PathVariable Long id) throws NotFoundException {
		recipeSrv.findByIdAndDelete(id);
	}
	
}
