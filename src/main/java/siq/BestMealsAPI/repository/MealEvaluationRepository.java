package siq.BestMealsAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import siq.BestMealsAPI.model.MealEvaluation;

import java.util.List;

public interface MealEvaluationRepository extends JpaRepository<MealEvaluation, Long> {
    // Busca todas as avaliações de uma refeição
    List<MealEvaluation> findByMealId(Long mealId);

    // Query para obter a média de avaliações em uma refeição
    @Query("SELECT COALESCE(AVG(m.rating), 0) FROM MealEvaluation m WHERE m.meal.id = :mealId")
    Double findAverageRatingByMealId(@Param("mealId") Long mealId);
}
