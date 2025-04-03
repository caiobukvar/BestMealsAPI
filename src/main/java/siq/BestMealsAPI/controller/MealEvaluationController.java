package siq.BestMealsAPI.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import siq.BestMealsAPI.model.MealEvaluation;
import siq.BestMealsAPI.service.MealEvaluationService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@RestController
@RequestMapping("/meal-evaluations")
public class MealEvaluationController {
    private final MealEvaluationService mealEvaluationService;

    public MealEvaluationController(MealEvaluationService mealEvaluationService) {
        this.mealEvaluationService = mealEvaluationService;
    }

    // forçando o swagger a ter apenas os campos rating e comment no body da requisição
    @Operation(summary = "Create a Meal Evaluation",
            description = "Submit a rating and comment for a specific meal.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = "{ \"rating\": 5, \"comment\": \"Delicious meal!\" }"
                            )
                    )
            )
    )

    @PostMapping("/{restaurantId}/{mealId}")
    public ResponseEntity<MealEvaluation> addMealEvaluation(
            @PathVariable Long restaurantId,
            @PathVariable Long mealId,
            @RequestBody MealEvaluation evaluation) {
        MealEvaluation savedEvaluation = mealEvaluationService.addEvaluation(restaurantId, mealId, evaluation);
        return ResponseEntity.ok(savedEvaluation);
    }

    @GetMapping("/meal/{mealId}")
    public ResponseEntity<List<MealEvaluation>> getMealEvaluations(@PathVariable Long mealId) {
        List<MealEvaluation> evaluations = mealEvaluationService.getEvaluationsByMeal(mealId);
        return ResponseEntity.ok(evaluations);
    }

    @GetMapping("/meal/{mealId}/average-rating")
    public ResponseEntity<BigDecimal> getAverageRating(@PathVariable Long mealId) {
        Double averageRating = mealEvaluationService.getAverageRatingForMeal(mealId);

        // usando BigDecimal para fixar a resposta em 2 casas decimais após a vírgula
        BigDecimal roundedRating = BigDecimal.valueOf(averageRating)
                .setScale(2, RoundingMode.HALF_UP);

        return ResponseEntity.ok(roundedRating);
    }
}