package siq.BestMealsAPI.model;

import org.springframework.stereotype.Service;
import siq.BestMealsAPI.repository.MealRepository;
import siq.BestMealsAPI.repository.RestaurantRepository;

import java.util.List;

@Service
public class MealService {
    private final MealRepository mealRepository;
    private final RestaurantRepository restaurantRepository;

    public MealService(MealRepository mealRepository,
                       RestaurantRepository restaurantRepository) {
        this.mealRepository = mealRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public Meal createMeal(Long restaurantId, Meal meal) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));

        // Create new meal instance to avoid side effects
        Meal newMeal = new Meal();
        newMeal.setName(meal.getName());
        newMeal.setCost(meal.getCost());
        newMeal.setIngredients(meal.getIngredients());
        newMeal.setRestaurant(restaurant); // Only set via service

        return mealRepository.save(newMeal);
    }

    public Meal getMeal(Long restaurantId, Long mealId) {
        return mealRepository.findByIdAndRestaurantId(mealId, restaurantId)
                .orElseThrow(() -> new RuntimeException("Meal not found"));
    }

    public List<Meal> getRestaurantMeals(Long restaurantId) {
        return mealRepository.findByRestaurantId(restaurantId);
    }

    public Meal updateMeal(Long restaurantId, Long mealId, Meal mealDetails) {
        Meal meal = getMeal(restaurantId, mealId);
        meal.setName(mealDetails.getName());
        meal.setCost(mealDetails.getCost());
        meal.setIngredients(mealDetails.getIngredients());
        return mealRepository.save(meal);
    }

    public void deleteMeal(Long restaurantId, Long mealId) {
        Meal meal = getMeal(restaurantId, mealId);
        mealRepository.delete(meal);
    }
}