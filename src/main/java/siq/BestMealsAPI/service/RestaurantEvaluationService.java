package siq.BestMealsAPI.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;
import siq.BestMealsAPI.model.Restaurant;
import siq.BestMealsAPI.model.RestaurantEvaluation;
import siq.BestMealsAPI.repository.RestaurantEvaluationRepository;

import java.util.List;

@Service
public class RestaurantEvaluationService {
    private final RestaurantEvaluationRepository evaluationRepository;
    private final RestTemplate restTemplate;

    @Value("${services.restaurant-service}")
    private String restaurantServiceUrl;

    public RestaurantEvaluationService(RestaurantEvaluationRepository evaluationRepository, RestTemplate restTemplate) {
        this.evaluationRepository = evaluationRepository;
        this.restTemplate = restTemplate;
    }

    public RestaurantEvaluation createEvaluation(Long restaurantId, RestaurantEvaluation evaluation) {
        String restaurantUrl = restaurantServiceUrl + "/restaurants/" + restaurantId;

        try {
            Restaurant restaurant = restTemplate.getForObject(restaurantUrl, Restaurant.class);
            if (restaurant == null) {
                throw new RuntimeException("Restaurant not found in restaurant service");
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to connect to restaurant service", e);
        }

        return evaluationRepository.save(evaluation);
    }

    public List<RestaurantEvaluation> getRestaurantEvaluations(Long restaurantId) {
        return evaluationRepository.findByRestaurantId(restaurantId);
    }

    public void deleteEvaluation(Long evaluationId) {
        evaluationRepository.deleteById(evaluationId);
    }
}
