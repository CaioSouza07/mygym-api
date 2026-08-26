# MyGym Software - Backend API

API Rest desenvolvida para ser o backend do MyGym Sofware, onde possibilita a persistência, salvamento, obtenção dos dados,
segurança e regras de negócio da aplicação.

## Stack

* Java
* Spring Boot
* Spring Data JPA
* Spring Security + JWT
* PostgreSQL
* Flyway
* Bean Validation
* Swagger UI - Documentação
* Lombok
* Maven
* Docker

## Funcionalidades

* Cadastro e login de usuários
* Cadastro de treino 
* Editar e excluir treinos
* Registrar a execução de treino
* Analisar em um gráfico progressão de carga
* Histórico de treinos

## Como Executar

### Pré-requisitos

- Java
- Maven
- PostgreSQL 15+
- Docker (opcional)

### Execução Local

```bash
# Clonar o repositório
git clone https://github.com/CaioSouza07/mygym-api.git
cd mygym-api

# Configurar variáveis de ambiente (copiar e ajustar)
cp .env.example .env

# Executar com Maven
./mvnw spring-boot:run
```

### Execução com Docker

```bash
# Build da imagem
docker build -t navalrivals-api .

# Executar o container
docker run -p 8080:8080 --env-file .env navalrivals-api
```

A API estará disponível em `http://localhost:8080`.
