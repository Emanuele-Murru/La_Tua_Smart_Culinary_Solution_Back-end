package Scs.entities.recipe;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import Scs.entities.ingredients.Ingredient;
import Scs.entities.user.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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
	@JsonIgnore
	private User user;
	
	@ManyToMany
	@JoinTable(name = "recipe_ingredients", joinColumns = @JoinColumn(name = "recipe_id"), inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
	private List<Ingredient> ingredients = new ArrayList<>();
}
