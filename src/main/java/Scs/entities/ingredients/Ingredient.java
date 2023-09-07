package Scs.entities.ingredients;

import java.util.ArrayList;
import java.util.List;

import Scs.entities.recipe.Recipe;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class Ingredient {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String name;
	private String quantity;
	
	@OneToMany
	private List <Recipe> recipes = new ArrayList<>();
	
	public Ingredient(String _name, String _quantity) {
		this.name = _name;
		this.quantity = _quantity;
	}
}