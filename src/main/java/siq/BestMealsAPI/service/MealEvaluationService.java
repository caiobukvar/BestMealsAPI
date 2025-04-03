package siq.BestMealsAPI.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;
import siq.BestMealsAPI.model.Meal;
import siq.BestMealsAPI.model.MealEvaluation;
import siq.BestMealsAPI.repository.MealEvaluationRepository;

import java.util.List;

@Service
public class MealEvaluationService {
    private final MealEvaluationRepository evaluationRepository;
    private final RestTemplate restTemplate;

    @Value("${services.meal-service}")
    private String mealServiceUrl;

    public MealEvaluationService(MealEvaluationRepository evaluationRepository, RestTemplate restTemplate) {
        this.evaluationRepository = evaluationRepository;
        this.restTemplate = restTemplate;
    }

    public MealEvaluation createEvaluation(Long mealId, MealEvaluation evaluation) {
        String mealUrl = mealServiceUrl + "/meals/" + mealId;

        try {
            Meal meal = restTemplate.getForObject(mealUrl, Meal.class);
            if (meal == null) {
                throw new RuntimeException("Meal not found in meal service");
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to connect to meal service", e);
        }

        return evaluationRepository.save(evaluation);
    }

    public List<MealEvaluation> getMealEvaluations(Long mealId) {
        return evaluationRepository.findByMealId(mealId);
    }

    public void deleteEvaluation(Long evaluationId) {
        evaluationRepository.deleteById(evaluationId);
    }
}
