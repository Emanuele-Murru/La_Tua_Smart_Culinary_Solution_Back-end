package Scs.entities.ingredients;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class IngredientPayload {

	private String name;
	private String quantity;
}
