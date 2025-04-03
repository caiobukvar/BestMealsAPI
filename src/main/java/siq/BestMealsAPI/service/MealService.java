package siq.BestMealsAPI.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;
import siq.BestMealsAPI.model.Meal;
import siq.BestMealsAPI.repository.MealRepository;

import java.util.List;

@Service
public class MealService {
    private final MealRepository mealRepository;
    private final RestTemplate restTemplate;

    @Value("${services.meal-evaluation-service}")
    private String mealEvaluationServiceUrl;

    public MealService(MealRepository mealRepository, RestTemplate restTemplate) {
        this.mealRepository = mealRepository;
        this.restTemplate = restTemplate;
    }

    public Meal createMeal(Meal meal) {
        return mealRepository.save(meal);
    }

    public Meal getMealById(Long mealId, Long restaurantId) {
        return mealRepository.findByIdAndRestaurant_Id(mealId, restaurantId)
                .orElseThrow(() -> new RuntimeException("Meal not found in this restaurant"));
    }

    public List<Meal> getMealsByRestaurant(Long restaurantId) {
        return mealRepository.findByRestaurantId(restaurantId);
    }

    public void deleteMeal(Long mealId) {
        mealRepository.deleteById(mealId);
    }

    public Object getMealEvaluations(Long mealId) {
        String evaluationsUrl = mealEvaluationServiceUrl + "/meals/" + mealId + "/evaluations";
        return restTemplate.getForObject(evaluationsUrl, Object.class);
    }
}
