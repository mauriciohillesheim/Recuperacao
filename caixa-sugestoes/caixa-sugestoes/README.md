# Caixa de Sugestões Anônima

## Descrição do Projeto

Este projeto é uma aplicação web desenvolvida com Spring Boot que permite aos usuários enviar sugestões, feedbacks ou reclamações de forma anônima. O objetivo é criar um espaço seguro e acessível para que os usuários expressem suas opiniões, contribuindo para a melhoria de produtos, serviços ou ambientes.

## Dependências Utilizadas

As principais dependências utilizadas no projeto são:

- **Spring Web**: Para construção de aplicações web e APIs RESTful.
- **Spring Data JPA**: Para facilitar a interação com o banco de dados utilizando o padrão JPA.
- **H2 Database**: Banco de dados em memória para desenvolvimento e testes.
- **Spring Boot Starter Validation**: Para validação de dados de entrada.

## Entidades

O projeto possui duas entidades principais:

### Sugestão

Representa uma sugestão enviada pelos usuários.

| Atributo        | Tipo          | Descrição                                         |
|-----------------|---------------|---------------------------------------------------|
| `id`            | `Long`        | Chave primária, gerada automaticamente.           |
| `titulo`        | `String`      | Título da sugestão (obrigatório, max 255 caracteres). |
| `descricao`     | `String`      | Descrição completa da sugestão (obrigatório, max 2000 caracteres). |
| `dataEnvio`     | `LocalDateTime` | Data e hora em que a sugestão foi enviada (gerada automaticamente). |
| `dataAtualizacao` | `LocalDateTime` | Data e hora da última atualização da sugestão (incluindo novos comentários). |
| `comentarios`   | `List<Comentario>` | Lista de comentários associados a esta sugestão. |

### Comentário

Representa um comentário ou resposta a uma sugestão.

| Atributo        | Tipo          | Descrição                                         |
|-----------------|---------------|---------------------------------------------------|
| `id`            | `Long`        | Chave primária, gerada automaticamente.           |
| `sugestao`      | `Sugestao`    | Chave estrangeira para a sugestão à qual o comentário pertence. |
| `texto`         | `String`      | Conteúdo do comentário ou resposta (obrigatório, max 1000 caracteres). |
| `dataEnvio`     | `LocalDateTime` | Data e hora em que o comentário foi enviado (gerada automaticamente). |

## Endpoints da API REST

Todos os endpoints seguem os padrões REST e convenções Java.

### 1. Cadastrar Sugestão

- **Endpoint**: `POST /sugestoes`
- **Descrição**: Cria uma nova sugestão anônima.
- **Request Body**: `SugestaoCreateDTO`
  ```json
  {
    "titulo": "Título da Sugestão",
    "descricao": "Descrição detalhada da sugestão."
  }
  ```
- **Response**: `201 Created` com `SugestaoResponseDTO` da sugestão criada.

### 2. Consultar Todas as Sugestões

- **Endpoint**: `GET /sugestoes`
- **Descrição**: Retorna uma lista de todas as sugestões, ordenadas pela `dataAtualizacao` em ordem decrescente. Permite filtrar por título.
- **Parâmetros de Query (Opcional)**:
  - `titulo`: `String` - Filtra sugestões cujo título contém o texto informado (case-insensitive).
- **Response**: `200 OK` com `List<SugestaoResponseDTO>`.

### 3. Consultar Sugestão por ID

- **Endpoint**: `GET /sugestoes/{id}`
- **Descrição**: Retorna uma sugestão específica pelo seu ID, incluindo todos os seus comentários ordenados pela `dataEnvio` em ordem decrescente.
- **Parâmetros de Path**:
  - `id`: `Long` - ID da sugestão.
- **Response**: `200 OK` com `SugestaoResponseDTO` (incluindo comentários) ou `404 Not Found` se a sugestão não existir.

### 4. Adicionar Comentário a uma Sugestão

- **Endpoint**: `POST /sugestoes/{id}/comentarios`
- **Descrição**: Adiciona um novo comentário a uma sugestão existente. A `dataAtualizacao` da sugestão será automaticamente atualizada.
- **Parâmetros de Path**:
  - `id`: `Long` - ID da sugestão.
- **Request Body**: `ComentarioCreateDTO`
  ```json
  {
    "texto": "Texto do comentário ou resposta."
  }
  ```
- **Response**: `201 Created` com `ComentarioResponseDTO` do comentário criado ou `404 Not Found` se a sugestão não existir.

## Como Rodar a Aplicação

1.  **Pré-requisitos**:
    - Java Development Kit (JDK) 11
    - Apache Maven

2.  **Clonar o repositório (se aplicável)**:
    ```bash
    git clone <URL_DO_REPOSITORIO>
    cd caixa-sugestoes
    ```

3.  **Compilar o projeto**:
    ```bash
    mvn clean install
    ```

4.  **Executar a aplicação**:
    ```bash
    mvn spring-boot:run
    ```

A aplicação será iniciada na porta `8080`. Você pode acessar o console H2 em `http://localhost:8080/h2-console` (com `jdbc:h2:mem:testdb`, usuário `sa`, senha `password`).

## Estrutura do Projeto

```
caixa-sugestoes
├── pom.xml
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── example
│   │   │           └── caixasugestoes
│   │   │               ├── CaixaSugestoesApplication.java
│   │   │               ├── controller
│   │   │               │   ├── ComentarioController.java
│   │   │               │   ├── GlobalExceptionHandler.java
│   │   │               │   └── SugestaoController.java
│   │   │               ├── dto
│   │   │               │   ├── ComentarioCreateDTO.java
│   │   │               │   ├── ComentarioResponseDTO.java
│   │   │               │   ├── SugestaoCreateDTO.java
│   │   │               │   └── SugestaoResponseDTO.java
│   │   │               ├── entity
│   │   │               │   ├── Comentario.java
│   │   │               │   └── Sugestao.java
│   │   │               ├── repository
│   │   │               │   ├── ComentarioRepository.java
│   │   │               │   └── SugestaoRepository.java
│   │   │               └── service
│   │   │                   ├── ComentarioService.java
│   │   │                   └── SugestaoService.java
│   │   └── resources
│   │       └── application.properties
│   └── test
│       └── java
│           └── com
│               └── example
│                   └── caixasugestoes
└── README.md
```

