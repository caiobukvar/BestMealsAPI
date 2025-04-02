package siq.BestMealsAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import siq.BestMealsAPI.model.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}
