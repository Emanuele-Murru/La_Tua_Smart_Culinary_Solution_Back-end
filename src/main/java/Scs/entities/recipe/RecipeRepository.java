package Scs.entities.recipe;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
	List<Recipe> findByIngredientsNameIn(List<String> ingredientNames);
	Page<Recipe> findByCategory(RecipeCategory category, Pageable pageable);
}