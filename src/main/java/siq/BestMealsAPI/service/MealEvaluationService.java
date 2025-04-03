package siq.BestMealsAPI.service;

import org.springframework.stereotype.Service;
import siq.BestMealsAPI.model.Meal;
import siq.BestMealsAPI.model.MealEvaluation;
import siq.BestMealsAPI.model.Restaurant;
import siq.BestMealsAPI.repository.MealEvaluationRepository;
import siq.BestMealsAPI.repository.MealRepository;
import siq.BestMealsAPI.repository.RestaurantRepository;

import java.util.List;


@Service
public class MealEvaluationService {
    private final MealEvaluationRepository mealEvaluationRepository;
    private final RestaurantRepository restaurantRepository;
    private final MealRepository mealRepository;

    public MealEvaluationService(
            MealEvaluationRepository mealEvaluationRepository,
            RestaurantRepository restaurantRepository,
            MealRepository mealRepository
    ) {
        this.mealEvaluationRepository = mealEvaluationRepository;
        this.restaurantRepository = restaurantRepository;
        this.mealRepository = mealRepository;
    }

    public MealEvaluation addEvaluation(Long restaurantId, Long mealId, MealEvaluation evaluation) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));

        Meal meal = mealRepository.findById(mealId)
                .orElseThrow(() -> new RuntimeException("Meal not found"));

        evaluation.setRestaurant(restaurant);
        evaluation.setMeal(meal);

        return mealEvaluationRepository.save(evaluation);
    }

    public List<MealEvaluation> getEvaluationsByMeal(Long mealId) {
        return mealEvaluationRepository.findByMealId(mealId);
    }

    public Double getAverageRatingForMeal(Long mealId) {
        return mealEvaluationRepository.findAverageRatingByMealId(mealId);
    }
}