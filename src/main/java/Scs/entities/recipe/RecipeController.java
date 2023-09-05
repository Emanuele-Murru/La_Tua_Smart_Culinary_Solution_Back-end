package Scs.entities.recipe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class RecipeController {

	@Autowired
	private RecipeService recipeSrv;
	
//	@GetMapping("")
//	public Page<Recipe> findAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "id") String order) {
//		return recipeSrv.findAll(page, order);
//	}
	
}
