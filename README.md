# BestMealsAPI

BestMealsAPI é uma API para avaliação de restaurantes e refeições, permitindo que os usuários avaliem pratos com até 5 estrelas e consultem os melhores estabelecimentos.

## Tecnologias Utilizadas
- **Java**
- **Spring Boot** (Spring Data JPA, Spring Web, Validation)
- **PostgreSQL** (Banco de dados)
- **Swagger** (Documentação da API)
- **Eureka** (Registro de serviços para arquitetura distribuída)

## Configuração e Instalação

### Clonando o Repositório
```sh
git clone https://github.com/caiobukvar/BestMealsAPI.git
cd BestMealsAPI
```

### Configurando o Servidor Eureka
A aplicação utiliza **Eureka** para registro e descoberta de serviços. Você pode usar um servidor Eureka já configurado no repositório abaixo:

🔗 [Repositório do Eureka Server](https://github.com/caiobukvar/eureka-server)

1. Clone e execute o Eureka Server:
```sh
git clone https://github.com/caiobukvar/eureka-server.git
cd eureka-server
mvn spring-boot:run
```

2. No **BestMealsAPI**, certifique-se de que a configuração do `application.properties` contém:
```properties
spring.application.name=best-meals-api
eureka.client.service-url.defaultZone=http://localhost:8761/eureka/
```

3. Execute a API normalmente:
```sh
mvn spring-boot:run
```

A API agora estará registrada no Eureka Server e disponível para descoberta por outros serviços.

## Endpoints Principais

### Restaurantes
- `POST /api/restaurants` → Criar um restaurante
- `GET /api/restaurants` → Listar todos os restaurantes
- `GET /api/restaurants/{id}` → Obter detalhes de um restaurante

### Refeições
- `POST /api/restaurants/{restaurantId}/meals` → Criar uma refeição para um restaurante
- `GET /api/restaurants/{restaurantId}/meals` → Listar refeições de um restaurante
- `GET /api/restaurants/{restaurantId}/meals/{mealId}` → Obter detalhes de uma refeição

### Avaliações
- `POST /api/restaurants/{restaurantId}/evaluations` → Avaliar um restaurante
- `POST /api/restaurants/{restaurantId}/meals/{mealId}/evaluations` → Avaliar uma refeição

## Documentação da API
A API conta com documentação interativa através do Swagger:
- Acesse **`http://localhost:8080/swagger-ui.html`** após rodar a aplicação.

## Contribuição
Pull Requests são bem-vindos! Para mudanças maiores, abra uma issue primeiro para discutirmos o que você gostaria de alterar.

---
🚀 **Criado e mantido por [Caio Bukvar](https://github.com/caiobukvar).**
