package Scs.entities.recipe;

import java.util.ArrayList;
import java.util.List;

import Scs.entities.ingredients.Ingredient;
import Scs.entities.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Recipe {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String title;
	private String category;
	private String instructions;
	private String prepTime;
	private String cookTime;
	private int servings;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@OneToMany
	private List<Ingredient> ingredients = new ArrayList<>();
}
