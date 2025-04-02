package siq.BestMealsAPI.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import siq.BestMealsAPI.model.Meal;
import siq.BestMealsAPI.model.MealService;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants/{restaurantId}/meals")
public class MealController {
    private final MealService mealService;

    public MealController(MealService mealService) {
        this.mealService = mealService;
    }

    @PostMapping
    public ResponseEntity<Meal> createMeal(
            @PathVariable Long restaurantId,
            @RequestBody Meal mealRequest) {
        Meal createdMeal = mealService.createMeal(restaurantId, mealRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMeal);
    }

    @GetMapping("/{mealId}")
    public ResponseEntity<Meal> getMeal(
            @PathVariable Long restaurantId,
            @PathVariable Long mealId) {
        Meal meal = mealService.getMeal(restaurantId, mealId);
        return ResponseEntity.ok(meal);
    }

    @GetMapping
    public ResponseEntity<List<Meal>> getRestaurantMeals(
            @PathVariable Long restaurantId) {
        List<Meal> meals = mealService.getRestaurantMeals(restaurantId);
        return ResponseEntity.ok(meals);
    }

    @PutMapping("/{mealId}")
    public ResponseEntity<Meal> updateMeal(
            @PathVariable Long restaurantId,
            @PathVariable Long mealId,
            @RequestBody Meal mealDetails) {
        Meal updatedMeal = mealService.updateMeal(restaurantId, mealId, mealDetails);
        return ResponseEntity.ok(updatedMeal);
    }

    @DeleteMapping("/{mealId}")
    public ResponseEntity<Void> deleteMeal(
            @PathVariable Long restaurantId,
            @PathVariable Long mealId) {
        mealService.deleteMeal(restaurantId, mealId);
        return ResponseEntity.noContent().build();
    }
}