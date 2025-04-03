package siq.BestMealsAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import siq.BestMealsAPI.model.MealEvaluation;

import java.util.List;

public interface MealEvaluationRepository extends JpaRepository<MealEvaluation, Long> {
    List<MealEvaluation> findByMealId(Long mealId);
}
