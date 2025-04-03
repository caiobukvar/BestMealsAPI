package siq.BestMealsAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import siq.BestMealsAPI.model.Meal;

import java.util.List;
import java.util.Optional;

public interface MealRepository extends JpaRepository<Meal, Long> {
    List<Meal> findByRestaurantId(Long restaurantId);
    Optional<Meal> findByIdAndRestaurant_Id(Long id, Long restaurantId);
}
