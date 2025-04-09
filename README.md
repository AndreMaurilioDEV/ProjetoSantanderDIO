# 💻 API de Banco Digital - Inspirada na Santander Dev Week 2023

Este projeto simula o **back-end de um banco digital**, desenvolvido com o objetivo de aplicar boas práticas de arquitetura, organização de código, regras de negócio e tecnologias amplamente utilizadas em APIs REST com Java e Spring Boot.

---

## 🚀 Funcionalidades

- CRUD de usuários
- Associação de conta e cartão a cada usuário
- Cadastro de funcionalidades (features) e notícias (news) para usuários
- Tratamento de exceções personalizadas
- Validações de regras de negócio
- Documentação automática com Swagger
- Integração com banco de dados MySQL via Docker
- Segurança inicial com Spring Security
- **Novas funcionalidades para gerenciamento de cartões**

---

## 🧠 Técnicas e Tecnologias Utilizadas

- **Spring Boot**  
- **Spring Data JPA**  
- **Spring Security**  
- **MapStruct**  
- **Docker + MySQL**  
- **DTOs e validações com Jakarta Bean Validation**  
- **Swagger (OpenAPI)**  
- **Exceções personalizadas**

---

## 🧱 Arquitetura do Projeto

O projeto segue uma arquitetura dividida em camadas:

- `controller`: lida com as requisições HTTP  
- `service`: contém as regras de negócio  
- `dto`: objetos de transferência de dados  
- `entity`: modelagem das tabelas e relacionamentos  
- `repository`: abstração do acesso ao banco de dados  

---

## 📁 Entidades e Relacionamentos

- `User`: entidade principal  
  - 🔁 1:1 com `Account` e `Card`  
  - 🔁 1:N com `Feature` e `News`

---

## 📦 Principais Endpoints

### 👤 UserController

- `GET /users`  
- `GET /users/{id}`  
- `POST /users`  
- `PUT /users/{id}`  
- `DELETE /users/{id}`  

### 💳 CardController

- `GET /cards`  
- `GET /cards/{idCard}`  
- `GET /cards/user/{userId}`  
- `PATCH /cards/user/{userId}/limit`  

---

## 🔄 Novas Funcionalidades: Gerenciamento de Cartões

Com o avanço do projeto, foi adicionada uma feature exclusiva para gerenciamento de **cartões** dos usuários.

### 📁 Novo Controller: `CardController`

Gerencia as seguintes rotas:

- `GET /cards`: lista todos os cartões cadastrados.  
- `GET /cards/{idCard}`: busca um cartão específico pelo ID.  
- `GET /cards/user/{userId}`: retorna o cartão associado a um determinado usuário.  
- `PATCH /cards/user/{userId}/limit`: atualiza o limite de um cartão com validação de regra de negócio.

### ⚙️ Service: `CardService`

- Centraliza a lógica de negócio dos cartões  
- Validações manuais  
- Lançamento de exceções personalizadas  
- Regras de negócio como unicidade de número e limites válidos  

### 📍 Regras de Negócio para Cartões

- Nenhum cartão pode ter **limite negativo**  
- Só é possível alterar o limite se o cartão **existir** e estiver associado a um **usuário válido**  
- O **número do cartão** deve ser **único**  
- Todas as validações de integridade ocorrem na **camada de serviço**

---

## 🧩 Novo DTO

```java
public record UpdateLimitCardDto(
    @DecimalMin(value = "0.00", inclusive = true, message = "Limit must be positive")
    BigDecimal limit
) {}

```

## 🔐 Segurança

- Segurança básica com **Spring Security**
- Sessões configuradas como `STATELESS`
- CSRF desabilitado
- Swagger liberado para testes durante o desenvolvimento
- Estrutura pronta para autenticação com **JWT** futuramente

---

## 📄 Documentação da API

- **Swagger UI** disponível em: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
- **OpenAPI JSON**: [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)

---

## 🛠️ Como Rodar o Projeto

### Pré-requisitos

- Java 17+
- Docker
- Maven

### Passos

1. Clone o repositório:

   ```bash
   git clone https://github.com/seu-usuario/api-banco-digital.git
   cd api-banco-digital


2. Suba o banco de dados com Docker:
```
docker-compose up -d

```

3. Rode a aplicação com sua IDE ou terminal:

```

./mvnw spring-boot:run

```

4. Acesse a documentação Swagger:

 ```

http://localhost:8080/swagger-ui.html

```

## 🔧 Melhorias Futuras

- ✅ **Autenticação com JWT**
- 🛡️ **Controle de acesso com roles** (admin, user, etc.)
- 🧪 **Testes automatizados** com JUnit e Mockito
- 🗂️ **Versionamento da API**
- 🔗 **Integração com front-end** em React ou Angular


