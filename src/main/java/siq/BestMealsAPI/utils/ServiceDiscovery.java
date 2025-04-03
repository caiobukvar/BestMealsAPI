package siq.BestMealsAPI.utils;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ServiceDiscovery {
    private final DiscoveryClient discoveryClient;

    public ServiceDiscovery(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }

    // metodo para buscar a URL de um serviço por nome
    public Optional<String> getServiceUrl(String serviceName) {
        // usando o DiscoveryClient para obter todas as instâncias do serviço registrado com o nome serviceName
        List<ServiceInstance> instances = discoveryClient.getInstances(serviceName);

        // se não houver instâncias registradas no Eureka, retorna vazio
        if (instances.isEmpty()) {
            return Optional.empty();
        }

        // retorna a URL da primeira instância disponível do serviço encontrado
        return Optional.of(instances.get(0).getUri().toString());
    }
}
