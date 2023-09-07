package Scs.runners;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;

import Scs.entities.ingredients.Ingredient;
import Scs.entities.ingredients.IngredientRepository;
import Scs.entities.ingredients.IngredientService;
import Scs.entities.recipe.Recipe;
import Scs.entities.recipe.RecipeRepository;
import Scs.entities.user.Role;
import Scs.entities.user.User;
import Scs.entities.user.UserRepository;

@Order(value = 0)
@Component
public class Runner implements CommandLineRunner {

    @Autowired
    RecipeRepository recipeRepo;

    @Autowired
    IngredientService ingredientSrv;

    @Autowired
    IngredientRepository ingredientRepo;

    @Autowired
    UserRepository userRepo;

    @Autowired
    PasswordEncoder bcrypt;

    @Override
    public void run(String... args) throws Exception {

        Faker faker = new Faker(new Locale("it"));

        List<User> users = new ArrayList<>();
        List<Recipe> recipes = new ArrayList<>();
        List<Ingredient> allIngredients = new ArrayList<>();

        // Creare 5 utenti
        for (int i = 0; i < 5; i++) {
            User user = new User();
            user.setName(faker.lordOfTheRings().character());
            user.setSurname(faker.lordOfTheRings().character());
            user.setUsername(faker.funnyName().name());
            user.setEmail(faker.internet().emailAddress());
            String password = faker.lorem().characters(6, 15);
            String hashedPassword = bcrypt.encode(password);
            user.setPassword(hashedPassword);
            user.setRole(Role.USER);
            users.add(user);
        }

        // Salvare gli utenti
        userRepo.saveAll(users);

        // Creare ingredienti
        for (int j = 0; j < 3; j++) {
            Ingredient ingredient = new Ingredient();
            ingredient.setName(faker.food().ingredient());
            ingredient.setQuantity(faker.food().measurement());
            allIngredients.add(ingredient);
        }

        // Creare 5 ricette diverse con ingredienti diversi
        for (int i = 0; i < 5; i++) {
            Recipe recipe = new Recipe();
            recipe.setTitle(faker.food().dish());
            recipe.setCategory(faker.food().ingredient());
            recipe.setInstructions(faker.lorem().paragraph());
            recipe.setPrepTime("About " + faker.number().numberBetween(1, 60) + " minutes");
            recipe.setCookTime("About " + faker.number().numberBetween(1, 60) + " minutes");
            recipe.setServings(faker.number().numberBetween(1, 5));

            List<Ingredient> ingredientsForRecipe = new ArrayList<>();

            // Aggiungi 3 ingredienti casuali alla ricetta
            for (int j = 0; j < 3; j++) {
                Ingredient randomIngredient = allIngredients.get(faker.random().nextInt(allIngredients.size()));
                ingredientsForRecipe.add(randomIngredient);
            }

            recipe.setIngredients(ingredientsForRecipe);
            recipe.setUser(users.get(i)); // Collega la ricetta a un utente diverso

            recipes.add(recipe);
        }

        // Salvare le ricette
        recipeRepo.saveAll(recipes);

        // Collegare gli ingredienti alle ricette
        for (Recipe recipe : recipes) {
            for (Ingredient ingredient : recipe.getIngredients()) {
                ingredient.getRecipes().add(recipe);
                ingredientRepo.save(ingredient);
            }
        }
    }
}
