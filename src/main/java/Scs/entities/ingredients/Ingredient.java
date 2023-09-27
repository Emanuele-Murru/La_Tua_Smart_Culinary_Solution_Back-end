package Scs.entities.ingredients;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import Scs.entities.recipe.Recipe;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class Ingredient {
	
	@Id
	private String name;
	private String quantity;
	private String img;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "ingredients")
	private List <Recipe> recipes = new ArrayList<>();
	
	public Ingredient(String _name, String _quantity, String _img) {
		this.name = _name;
		this.quantity = _quantity;
		this.img = _img;
	}
}
