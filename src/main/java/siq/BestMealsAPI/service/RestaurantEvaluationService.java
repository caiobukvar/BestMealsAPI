package siq.BestMealsAPI.service;

import org.springframework.stereotype.Service;
import siq.BestMealsAPI.model.Restaurant;
import siq.BestMealsAPI.model.RestaurantEvaluation;
import siq.BestMealsAPI.repository.RestaurantEvaluationRepository;
import siq.BestMealsAPI.repository.RestaurantRepository;

import java.util.List;

@Service
public class RestaurantEvaluationService {

    private final RestaurantEvaluationRepository evaluationRepository;
    private final RestaurantRepository restaurantRepository;

    public RestaurantEvaluationService(RestaurantEvaluationRepository evaluationRepository,
                                       RestaurantRepository restaurantRepository) {
        this.evaluationRepository = evaluationRepository;
        this.restaurantRepository = restaurantRepository;
    }

    // criar uma avaliação de restaurante
    public RestaurantEvaluation createEvaluation(Long restaurantId, RestaurantEvaluation evaluation) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));

        evaluation.setRestaurant(restaurant);
        return evaluationRepository.save(evaluation);
    }

    // listar todas as avaliações de um restaurante
    public List<RestaurantEvaluation> getEvaluationsByRestaurantId(Long restaurantId) {
        return evaluationRepository.findByRestaurantId(restaurantId);
    }

    // lista a média das avaliações de um restaurante (utilizando STREAMS)
    public Double getAverageRatingForRestaurant(Long restaurantId) {
        double average = evaluationRepository.findByRestaurantId(restaurantId).stream()
                .mapToInt(RestaurantEvaluation::getRating)
                .average()
                .orElse(0.0);

        // .stream() -> cria uma stream
        // .mapToInt(RestaurantEvaluation::getRating) -> transforma a stream em IntStream -> extrai a 'rating' de cada avaliação
        // .average() -> faz a média entre os dados
        // .orElse(0.0) -> se não encontrar ratings, retorna 0

        // formata a média final para máx 2 decimais
        return Math.round(average * 100) / 100.0;
    }
}