package siq.BestMealsAPI.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import siq.BestMealsAPI.model.Restaurant;
import siq.BestMealsAPI.service.RestaurantService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/restaurants") // seta o prefixo da api para todos os Mappings abaixo
public class RestaurantController {
    // cria o serviço
    private final RestaurantService restaurantService;

    // inicializa o serviço
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping()
    public List<Restaurant> getAllRestaurants() {
        return restaurantService.getAllRestaurants();
    }

    // api/restaurants/id -> id é injetado
    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> getRestaurant(@PathVariable Long id) {
        Optional<Restaurant> restaurant = restaurantService.getRestaurantById(id);

        return restaurant.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        // funcionamento do código acima:
        // Se o restaurante existir -> retorna status 200
        // Se o restaurante não existir -> retorna status 404

        /*
        * if (restaurant.isPresent()) {
            return ResponseEntity.ok(restaurant.get());
          } else {
                return ResponseEntity.notFound().build();
            }
        */
    }

    // cria um restaurante
    @PostMapping
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody Restaurant restaurant) {
        // Força uma identidade nova ao deixar o ID null
        restaurant.setId(null);
        Restaurant saved = restaurantService.createRestaurant(restaurant);
        return ResponseEntity.ok(saved);
    }

    // atualizar um restaurante
    @PutMapping("/{id}")
    public ResponseEntity<Restaurant> updateRestaurant(@PathVariable Long id, @RequestBody Restaurant restaurant) {
        Optional<Restaurant> updatedRestaurant = restaurantService.updateRestaurant(id, restaurant);

        return updatedRestaurant.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // excluir um restaurante
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable Long id) {
        restaurantService.deleteRestaurant(id);
        return ResponseEntity.noContent().build();
    }
}