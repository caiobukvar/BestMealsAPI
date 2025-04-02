package siq.BestMealsAPI.service;

import org.springframework.stereotype.Service;
import siq.BestMealsAPI.model.Restaurant;
import siq.BestMealsAPI.repository.RestaurantRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService {

    private final RestaurantRepository repository;

    public RestaurantService(RestaurantRepository repository) {
        this.repository = repository;
    }

    // consultar todos os restaurantes do db
    public List<Restaurant> getAllRestaurants() {
        return repository.findAll();
    }

    // consultar restaurantes por id: usa Optional<> pois evita 'NullPointerException'
    // Sem ele, se o restaurante não existir, findById(id) retornaria null, e qualquer tentativa de acessar um metodo nesse null causaria erro na aplicação.
    public Optional<Restaurant> getRestaurantById(Long id) {
        return repository.findById(id);
    }

    // criar um restaurante
    public Restaurant createRestaurant(Restaurant restaurant) {
        return repository.save(restaurant);
    }

    // atualizar um restaurante
    public Optional<Restaurant> updateRestaurant(Long id, Restaurant updatedRestaurant) {
        return repository.findById(id)
                .map(restaurant -> {
                    restaurant.setName(updatedRestaurant.getName());
                    restaurant.setAddress(updatedRestaurant.getAddress());
                    restaurant.setState(updatedRestaurant.getState());
                    restaurant.setZipCode(updatedRestaurant.getZipCode());
                    return Optional.of(repository.save(restaurant));
                })
                .orElse(Optional.empty()); // Retorna um Optional vazio se o restaurante não for encontrado
    }


    // remover um restaurante
    public void deleteRestaurant(Long id) {
        repository.deleteById(id);
    }
}
