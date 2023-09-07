//package Scs.runners;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Locale;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//import com.github.javafaker.Faker;
//
//import Scs.entities.ingredients.Ingredient;
//import Scs.entities.ingredients.IngredientPayload;
//import Scs.entities.ingredients.IngredientRepository;
//import Scs.entities.ingredients.IngredientService;
//import Scs.entities.recipe.Recipe;
//import Scs.entities.recipe.RecipeRepository;
//import Scs.entities.user.Role;
//import Scs.entities.user.User;
//import Scs.entities.user.UserRepository;
//
//@Order(value = 0)
//@Component
//public class RecipeRunner implements CommandLineRunner {
//
//	@Autowired
//	RecipeRepository recipeRepo;
//
//	@Autowired
//	IngredientService ingredientSrv;
//
//	@Autowired
//	IngredientRepository ingredientRepo;
//
//	@Autowired
//	UserRepository userRepo;
//
//	@Autowired
//	PasswordEncoder bcrypt;
//
//	@Override
//	public void run(String... args) throws Exception {
//
//		Faker faker = new Faker(new Locale("it"));
//
//		List<Ingredient> ingredientDb = ingredientRepo.findAll();
//		List<Recipe> recipeDb = recipeRepo.findAll();
//
//		if (ingredientDb.isEmpty() || recipeDb.isEmpty()) {
//
//			for (int i = 0; i < 5; i++) {
//				User user = new User();
//				Recipe recipe = new Recipe();
//				recipe.setTitle(faker.food().dish());
//				recipe.setCategory(faker.food().ingredient());
//				recipe.setInstructions(faker.lorem().paragraph());
//				recipe.setPrepTime("About " + faker.number().numberBetween(1, 60) + " minutes");
//				recipe.setCookTime("About " + faker.number().numberBetween(1, 60) + " minutes");
//				recipe.setServings(faker.number().numberBetween(1, 5));
//				user.setName(faker.lordOfTheRings().character());
//				user.setSurname(faker.lordOfTheRings().character());
//				user.setUsername(faker.funnyName().name());
//				user.setEmail(faker.internet().emailAddress());
//				String password = faker.lorem().characters(6, 15);
//				String HashedPassword = bcrypt.encode(password);
//				user.setPassword(HashedPassword);
//				user.setRole(Role.USER);
//
//				userRepo.save(user);
//
//				recipe.setUser(user);
//
//				List<Ingredient> ingredients = new ArrayList<>();
//				for (int j = 0; j < 3; j++) {
//					Ingredient ingredient = new Ingredient();
//					ingredient.setName(faker.food().ingredient());
//					ingredient.setQuantity(faker.food().measurement());
//					ingredients.add(ingredient);
//					ingredientRepo.save(ingredient); // Salva ciascun ingrediente nel database
//				}
//
//				recipe.setIngredients(ingredients);
//				recipeRepo.save(recipe); // Salva la ricetta nel database
//			}
//		}
//
//	}
//}
