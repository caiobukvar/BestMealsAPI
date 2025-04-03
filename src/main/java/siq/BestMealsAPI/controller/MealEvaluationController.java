package siq.BestMealsAPI.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import siq.BestMealsAPI.model.MealEvaluation;
import siq.BestMealsAPI.service.MealEvaluationService;

@RestController
@RequestMapping("/meal-evaluations")
public class MealEvaluationController {
    private final MealEvaluationService mealEvaluationService;

    public MealEvaluationController(MealEvaluationService mealEvaluationService) {
        this.mealEvaluationService = mealEvaluationService;
    }

    @PostMapping("/{restaurantId}/{mealId}")


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

    public ResponseEntity<MealEvaluation> addEvaluation(
            @PathVariable Long restaurantId,
            @PathVariable Long mealId,
            @RequestBody MealEvaluation evaluation) {

        MealEvaluation savedEvaluation = mealEvaluationService.addEvaluation(restaurantId, mealId, evaluation);
        return ResponseEntity.ok(savedEvaluation);
    }
}
