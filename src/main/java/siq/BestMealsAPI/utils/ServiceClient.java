package siq.BestMealsAPI.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
public class ServiceClient {

    private final RestTemplate restTemplate;
    private final ServiceDiscovery serviceDiscovery;

    public ServiceClient(ServiceDiscovery serviceDiscovery) {
        // inicializa um novo RestTemplate
        this.restTemplate = new RestTemplate();
        // injeta o serviço
        this.serviceDiscovery = serviceDiscovery;
    }

    // metodo genérico para fazer requisições GET.
    public <T> Optional<T> get(String serviceName, String path, Class<T> responseType) {
        Optional<String> serviceUrl = serviceDiscovery.getServiceUrl(serviceName);

        // se a URL do serviço existir, faz um GET para o serviço e retorna a resposta
        if (serviceUrl.isPresent()) {
            String fullUrl = serviceUrl.get() + path;
            // restTemplate faz a requisição HTTP
            return Optional.ofNullable(restTemplate.getForObject(fullUrl, responseType));
        }

        return Optional.empty();
    }

    public <T> Optional<T> post(String serviceName, String path, Object request, Class<T> responseType) {
        Optional<String> serviceUrl = serviceDiscovery.getServiceUrl(serviceName);

        if (serviceUrl.isPresent()) {
            String fullUrl = serviceUrl.get() + path;
            return Optional.ofNullable(restTemplate.postForObject(fullUrl, request, responseType));
        }

        return Optional.empty();
    }
}
