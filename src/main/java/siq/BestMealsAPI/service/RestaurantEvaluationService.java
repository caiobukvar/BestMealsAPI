package siq.BestMealsAPI.service;

import org.springframework.stereotype.Service;
import siq.BestMealsAPI.model.Restaurant;
import siq.BestMealsAPI.model.RestaurantEvaluation;
import siq.BestMealsAPI.repository.RestaurantEvaluationRepository;
import siq.BestMealsAPI.repository.RestaurantRepository;

@Service
public class RestaurantEvaluationService {

    private final RestaurantEvaluationRepository evaluationRepository;
    private final RestaurantRepository restaurantRepository;

    public RestaurantEvaluationService(RestaurantEvaluationRepository evaluationRepository,
                                       RestaurantRepository restaurantRepository) {
        this.evaluationRepository = evaluationRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public RestaurantEvaluation createEvaluation(Long restaurantId, RestaurantEvaluation evaluation) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));

        evaluation.setRestaurant(restaurant);
        return evaluationRepository.save(evaluation);
    }
}