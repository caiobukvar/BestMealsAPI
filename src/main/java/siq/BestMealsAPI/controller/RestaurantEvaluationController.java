package siq.BestMealsAPI.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import siq.BestMealsAPI.model.RestaurantEvaluation;
import siq.BestMealsAPI.service.RestaurantEvaluationService;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants/{restaurantId}/evaluations")
public class RestaurantEvaluationController {

    // certificando que a request não passe dos limites
    public record EvaluationRequest(
            @NotNull @Min(1) @Max(5) Integer rating,
            @Size(max = 500) String comment
    ) {}

    // certificando que a response esteja correta
    public record EvaluationResponse(
            Long id,
            Integer rating,
            String comment,
            String restaurantName
    ) {}

    private final RestaurantEvaluationService evaluationService;

    public RestaurantEvaluationController(RestaurantEvaluationService evaluationService) {
        this.evaluationService = evaluationService;
    }

    @PostMapping
    public ResponseEntity<RestaurantEvaluation> createEvaluation(
            @PathVariable Long restaurantId,
            @Valid @RequestBody EvaluationRequest request) {

        RestaurantEvaluation evaluation = new RestaurantEvaluation();
        evaluation.setRating(request.rating());
        evaluation.setComment(request.comment());

        RestaurantEvaluation createdEvaluation = evaluationService.createEvaluation(restaurantId, evaluation);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEvaluation);
    }

    @GetMapping
    public ResponseEntity<List<EvaluationResponse>> getEvaluationsByRestaurant(
            @PathVariable Long restaurantId) {

        List<RestaurantEvaluation> evaluations = evaluationService.getEvaluationsByRestaurantId(restaurantId);

        List<EvaluationResponse> response = evaluations.stream()
                .map(eval -> new EvaluationResponse(
                        eval.getId(),
                        eval.getRating(),
                        eval.getComment(),
                        eval.getRestaurant().getName()))
                .toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/average-rating")
    public ResponseEntity<Double> getAverageRating(
            @PathVariable Long restaurantId) {
        Double average = evaluationService.getAverageRatingForRestaurant(restaurantId);
        return ResponseEntity.ok(average);
    }
}