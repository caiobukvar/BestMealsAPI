package siq.BestMealsAPI.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import siq.BestMealsAPI.model.Meal;
import siq.BestMealsAPI.service.MealService;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants/{restaurantId}/meals")
public class MealController {
    private final MealService mealService;

    @Autowired
    public MealController(MealService mealService) {
        this.mealService = mealService;
    }

    @PostMapping
    public ResponseEntity<Meal> createMeal(@PathVariable Long restaurantId, @RequestBody Meal meal) {
        Meal savedMeal = mealService.createMeal(meal);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMeal);
    }

    @GetMapping("/{mealId}")
    public ResponseEntity<Meal> getMeal(@PathVariable Long restaurantId, @PathVariable Long mealId) {
        return ResponseEntity.ok(mealService.getMealById(mealId, restaurantId));
    }

    @GetMapping
    public ResponseEntity<List<Meal>> getAllMeals(@PathVariable Long restaurantId) {
        return ResponseEntity.ok(mealService.getMealsByRestaurant(restaurantId));
    }

    @DeleteMapping("/{mealId}")
    public ResponseEntity<Void> deleteMeal(@PathVariable Long restaurantId, @PathVariable Long mealId) {
        mealService.deleteMeal(mealId);
        return ResponseEntity.noContent().build();
    }
}
