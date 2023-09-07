package Scs.entities.recipe;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Scs.entities.user.User;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long>{

}
