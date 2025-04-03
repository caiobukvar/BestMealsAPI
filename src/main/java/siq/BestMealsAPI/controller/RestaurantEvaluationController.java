package siq.BestMealsAPI.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import siq.BestMealsAPI.model.RestaurantEvaluation;
import siq.BestMealsAPI.service.RestaurantEvaluationService;

import java.util.List;

@RestController
@RequestMapping("/restaurant-evaluations")
public class RestaurantEvaluationController {
    private final RestaurantEvaluationService evaluationService;

    public RestaurantEvaluationController(RestaurantEvaluationService evaluationService) {
        this.evaluationService = evaluationService;
    }

    @PostMapping("/{restaurantId}")
    public ResponseEntity<RestaurantEvaluation> createEvaluation(
            @PathVariable Long restaurantId,
            @RequestBody @Valid RestaurantEvaluation evaluation) {
        RestaurantEvaluation savedEvaluation = evaluationService.createEvaluation(restaurantId, evaluation);
        return ResponseEntity.ok(savedEvaluation);
    }

    @GetMapping("/{restaurantId}")
    public ResponseEntity<List<RestaurantEvaluation>> getRestaurantEvaluations(@PathVariable Long restaurantId) {
        List<RestaurantEvaluation> evaluations = evaluationService.getRestaurantEvaluations(restaurantId);
        return ResponseEntity.ok(evaluations);
    }

    @DeleteMapping("/{evaluationId}")
    public ResponseEntity<Void> deleteEvaluation(@PathVariable Long evaluationId) {
        evaluationService.deleteEvaluation(evaluationId);
        return ResponseEntity.noContent().build();
    }
}
