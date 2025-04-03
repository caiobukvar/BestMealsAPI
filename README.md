# BestMealsAPI

BestMealsAPI é uma API desenvolvida em **Java com Spring Boot** para gerenciamento de restaurantes e seus respectivos pratos, permitindo cadastrar, listar, atualizar e deletar restaurantes e refeições. 

## Tecnologias Utilizadas

- Java 17
- Spring Boot 3
- Spring Data JPA
- PostgreSQL (ou qualquer banco de dados compatível com JPA)
- Swagger para documentação da API
- Maven para gerenciamento de dependências
- Eureka para descoberta de serviços

## Como Rodar o Projeto Localmente

### 1. Clonar o Repositório
```bash
git clone https://github.com/seu-usuario/bestmeals-api.git
cd bestmeals-api
```

### 2. Configurar o Banco de Dados
Crie um banco de dados PostgreSQL e configure o arquivo `application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/bestmeals
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
```

### 3. Rodar a Aplicação
Use o Maven para compilar e iniciar a API:
```bash
./mvnw spring-boot:run
```
A API estará disponível em `http://localhost:8080`.

## Endpoints da API

A API está documentada com **Swagger**, acessível via:
```
http://localhost:8080/swagger-ui.html
```

### 📌 **Restaurantes**
- **Criar Restaurante**: `POST /api/restaurants`
- **Listar Todos**: `GET /api/restaurants`
- **Buscar por ID**: `GET /api/restaurants/{restaurantId}`
- **Atualizar Restaurante**: `PUT /api/restaurants/{restaurantId}`
- **Deletar Restaurante**: `DELETE /api/restaurants/{restaurantId}`

### 📌 **Refeições (Meals)**
- **Criar Refeição**: `POST /api/restaurants/{restaurantId}/meals`
- **Listar Refeições de um Restaurante**: `GET /api/restaurants/{restaurantId}/meals`
- **Buscar uma Refeição**: `GET /api/restaurants/{restaurantId}/meals/{mealId}`
- **Atualizar Refeição**: `PUT /api/restaurants/{restaurantId}/meals/{mealId}`
- **Deletar Refeição**: `DELETE /api/restaurants/{restaurantId}/meals/{mealId}`
