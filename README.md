# GeoSense
*Transforme seu pátio em um espaço inteligente, seguro e eficiente*

---

<p align="center">
  <img src="https://img.shields.io/badge/Spring%20Boot-3.2.5-blue.svg" alt="Spring Boot"/>
  <img src="https://img.shields.io/badge/Java-17-orange.svg" alt="Java 17"/>
  <img src="https://img.shields.io/badge/License-MIT-green.svg" alt="License"/>
</p>

---

<a id="descricao-do-projeto"></a>
## 📖 Descrição do Projeto

Com o crescimento acelerado da **Mottu** desde 2020, a empresa se destaca com:

- **250 000+ entregas/mês** em 171 cidades (Brasil & México)  
- **100 000+ motos** alugadas em 100+ filiais  

Contudo, a **gestão de pátio** ainda é manual, fragmentada entre:

- Planilhas  
- Chamadas telefônicas  
- Inspeção visual  

Isso gera _atrasos_, _retrabalho_ e _decisões reativas_, impactando custos e escalabilidade.

> "<strong>GeoSense</strong> conecta você ao seu pátio — experimente a transformação."  

---

## 🔗 Sumário

| Seção                         | Link                                  |
|-------------------------------|---------------------------------------|
| Descrição do Projeto          | [Descrição do Projeto](#descricao-do-projeto)        |
| Problemas Identificados       | [Problemas Identificados](#problemas-identificados)    |
| Solução Proposta              | [Solução Proposta](#solucao-proposta)                |
| Benefícios                    | [Benefícios](#beneficios)                          |
| Como Funciona                 | [Como Funciona](#como-funciona)                     |
| Arquitetura                   | [Arquitetura](#arquitetura)                        |
| Funcionalidades & Testes      | [Funcionalidades & Testes](#funcionalidades--testes) |
| Como Executar                 | [Como Executar](#como-executar)                      |

---


<a id="problemas-identificados"></a>
## 🛑 Problemas Identificados

1. **Localização manual** de motos  
2. **Dados fragmentados** sem painel unificado  
3. **Downtime elevado** e inatividade da frota  
4. **Vulnerabilidade** a furtos/extravios  
5. **Escalabilidade lenta** em novas filiais

---

<a id="solucao-proposta"></a>
## 🚀 Solução Proposta

GeoSense digitaliza e centraliza todas as informações do pátio, entregando:

- 📍 Mapeamento em **tempo real**  
- 🤖 Alocação **inteligente** de vagas  
- 🔄 Atualização **automática** de status  
- 🔔 **Alertas e filtros** avançados  
- 📲 Dashboard **mobile** com notificações

---

<a id="beneficios"></a>
## 🎯 Benefícios

- **80%+** redução no tempo de busca  
- Decisões **proativas** com dados em tempo real  
- Aumento na **disponibilidade** da frota  
- Segurança reforçada e menos furtos  
- Expansão **rápida** e padronizada

---

<a id="como-funciona"></a>
## ⚙️ Como Funciona

1. 🚀 **Registro**: mecânico informa defeito no app  
2. 🤖 **Processamento**: sugerimos a vaga ideal  
3. 📋 **Alocação**: vaga e moto vinculadas no sistema  
4. 🔍 **Busca**: filtros por modelo, status ou tempo  
5. 🔔 **Monitoramento**: notificações de prazos

---
<a id="arquitetura"></a>
## 🏗️ Arquitetura
```text

src/main/java/com/geosense/geosense
├── controller   # Endpoints REST
├── service      # Lógica de negócio
├── repository   # Acesso a dados (JPA/Hibernate)
├── entity       # Mapeamentos JPA
├── dto          # Transferência de dados
└── GeosenseApplication.java  # Boot Spring

<parent>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-parent</artifactId>
  <version>3.2.5</version>
</parent>
<properties>
  <java.version>17</java.version>
</properties>
<dependencies>
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
  </dependency>
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
  </dependency>
  <dependency>
    <groupId>com.oracle.database.jdbc</groupId>
    <artifactId>ojdbc8</artifactId>
    <version>21.9.0.0</version>
  </dependency>
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
  </dependency>
</dependencies>

spring.application.name=geosense
spring.datasource.url=jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl
spring.datasource.username=SEU_RM
spring.datasource.password=SUA_SENHA
spring.jpa.database-platform=org.hibernate.dialect.OracleDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```
---

<a id="funcionalidades--testes"></a>
## ⚡ Funcionalidades & Testes

> **Atenção:** esta é a seção mais importante!  
> Teste cada endpoint utilizando o [Postman](https://www.postman.com/) ou seu cliente HTTP favorito.

---

### 📂 Alocação de Moto

**Base URL:**  
http://localhost:8080/alocacoes
---

#### ▶ POST `/`

**Descrição:** aloca uma moto em vaga disponível.

##### Request

POST http://localhost:8080/alocacoes

```json
{
  "motoId": 1,
  "vagaId": 10,
  "mecanicoResponsavelId": 2
}
```
</details>
| Campo                   | Tipo | Obrigatório | Descrição                                    |
| ----------------------- | ---- | ----------- | -------------------------------------------- |
| `motoId`                | Long | ✅           | ID da moto que será alocada                  |
| `vagaId`                | Long | ✅           | ID da vaga onde a moto ficará                |
| `mecanicoResponsavelId` | Long | ❌           | ID do mecânico responsável (pode ser `null`) |

Fluxo no Service
1-Busca Moto por motoId.

2-Busca Vaga por vagaId.

3-(Opcional) Busca Usuario se mecanicoResponsavelId informado.

4-Cria AlocacaoMoto com dataHoraAlocacao = LocalDateTime.now().

5-Persiste e retorna o DTO.

Response 200 OK
```json
{
  "id": 5,
  "motoId": 1,
  "vagaId": 10,
  "mecanicoResponsavelId": 2,
  "dataHoraAlocacao": "2025-05-21T14:32:10.123"
}
```
id: identificador da alocação
dataHoraAlocacao: timestamp da operação

Possíveis Erros
| Exceção                                       | HTTP | Quando ocorre                    |
| --------------------------------------------- | ---- | -------------------------------- |
| `RuntimeException: "Moto não encontrada"`     | 500  | `motoId` inválido ou ausente     |
| `RuntimeException: "Vaga não encontrada"`     | 500  | `vagaId` inválido ou ausente     |
| `RuntimeException: "Mecânico não encontrado"` | 500  | `mecanicoResponsavelId` inválido |

▶ GET /
Descrição: lista todas as alocações.

Request
GET http://localhost:8080/alocacoes

Response 200 OK
```json
[
  {
    "id": 5,
    "motoId": 1,
    "vagaId": 10,
    "mecanicoResponsavelId": 2,
    "dataHoraAlocacao": "2025-05-21T14:32:10.123"
  },
  {
    "id": 6,
    "motoId": 2,
    "vagaId": 12,
    "mecanicoResponsavelId": null,
    "dataHoraAlocacao": "2025-05-21T15:10:45.789"
  }
]
```
▶ GET /{id}
Descrição: busca uma alocação específica por ID.

Request
GET http://localhost:8080/alocacoes/{id}

| Parâmetro | Tipo | Descrição                    |
| --------- | ---- | ---------------------------- |
| `id`      | Long | ID da alocação a ser buscada |

Response 200 OK
```json
{
  "id": 5,
  "motoId": 1,
  "vagaId": 10,
  "mecanicoResponsavelId": 2,
  "dataHoraAlocacao": "2025-05-21T14:32:10.123"
}
```

Response 404 Not Found
Nenhuma alocação encontrada para o id informado.

▶ DELETE /{id}
Descrição: remove uma alocação por ID.

Request
DELETE http://localhost:8080/alocacoes/{id}

| Parâmetro | Tipo | Descrição                     |
| --------- | ---- | ----------------------------- |
| `id`      | Long | ID da alocação a ser removida |

Response 204 No Content
Sem conteúdo no corpo, indica que a remoção foi bem-sucedida.

### 📂 Defeitos  
**Base URL:** `http://localhost:8080/defeitos`

---

| Verbo  | Endpoint  | Descrição                      |
| ------ | --------- | ------------------------------ |
| POST   | `/`       | Registra um novo defeito       |
| GET    | `/`       | Lista todos os defeitos        |
| GET    | `/{id}`   | Busca defeito por ID           |
| DELETE | `/{id}`   | Remove defeito por ID          |

---

#### ▶ POST `/`

**Descrição:** registra um defeito associado a uma moto.

POST http://localhost:8080/defeitos

```json
{
  "tiposDefeitos": "MOTOR_DEFEITUOSO",
  "descricao": "Falha no motor",
  "motoId": 1
}
```

| Campo           | Tipo          | Obrigatório | Descrição                                                      |
| --------------- | ------------- | ----------- | -------------------------------------------------------------- |
| `tiposDefeitos` | enum `String` | ✅           | `REPAROS_SIMPLES` \| `MOTOR_DEFEITUOSO` \| `DANOS_ESTRUTURAIS` |
| `descricao`     | `String`      | ✅           | Descrição (máx. 255 caracteres)                                |
| `motoId`        | `Long`        | ✅           | ID da moto que receberá o defeito                              |

#### Fluxo no Service:

Busca a entidade Moto por motoId.

Instancia Defeito, preenche campos e associa à moto.

Persiste via defeitoRepository.save().

Retorna DefeitoDTO com id, tiposDefeitos, descricao e motoId.

#### Response 200 OK
{
  "id": 3,
  "tiposDefeitos": "MOTOR_DEFEITUOSO",
  "descricao": "Falha no motor",
  "motoId": 1
}

#### Possíveis Erros:

| Erro                                                     | HTTP | Quando ocorre                            |
| -------------------------------------------------------- | ---- | ---------------------------------------- |
| `RuntimeException: "Moto não encontrada"`                | 500  | `motoId` inválido ou ausente             |
| Violação de `@NotBlank` ou `@Size(max=255)` em descrição | 400  | `descricao` em branco ou muito longa     |
| `@NotNull` ou valor inválido em `tiposDefeitos`          | 400  | `tiposDefeitos` nulo ou fora dos valores |

▶ GET /
Descrição: lista todos os defeitos registrados.
GET http://localhost:8080/defeitos

#### Response 200 OK
```json

[
  {
    "id": 1,
    "tiposDefeitos": "REPAROS_SIMPLES",
    "descricao": "Troca de óleo",
    "motoId": 2
  },
  {
    "id": 2,
    "tiposDefeitos": "DANOS_ESTRUTURAIS",
    "descricao": "Rachadura no quadro",
    "motoId": 3
  }
]
```
▶ GET /{id}
Descrição: busca um defeito específico por ID.
GET http://localhost:8080/defeitos/{id}

| Parâmetro | Tipo | Descrição              |
| --------- | ---- | ---------------------- |
| `id`      | Long | ID do defeito desejado |

#### Response 200 OK
```json
{
  "id": 2,
  "tiposDefeitos": "DANOS_ESTRUTURAIS",
  "descricao": "Rachadura no quadro",
  "motoId": 3
}
```
#### Response 404 Not Found
Nenhum defeito encontrado com o ID informado.

▶ DELETE /{id}
Descrição: remove um defeito cadastrado.
DELETE http://localhost:8080/defeitos/{id}

| Parâmetro | Tipo | Descrição               |
| --------- | ---- | ----------------------- |
| `id`      | Long | ID do defeito a remover |

Response 204 No Content
Corpo vazio indica remoção bem-sucedida.

### 📂 Motos  
**Base URL:** `http://localhost:8080/motos`

---

| Verbo  | Endpoint  | Descrição                    |
| ------ | --------- | ---------------------------- |
| POST   | `/`       | Cadastra nova moto           |
| GET    | `/`       | Lista todas as motos         |
| DELETE | `/{id}`   | Remove moto por ID           |

---

#### ▶ POST `/`

**Descrição:** cadastra uma nova moto, aplicando validações de negócio.

POST http://localhost:8080/motos

```json
{
  "modelo": "CB500",
  "placa": "ABC1234",
  "chassi": "XYZ9876543210",
  "problemaIdentificado": "motor defeituoso",
  "vagaId": 5
}
```

| Campo                  | Tipo   | Obrigatório | Descrição                                                                                                                   |
| ---------------------- | ------ | ----------- | --------------------------------------------------------------------------------------------------------------------------- |
| `modelo`               | String | ✅           | Modelo da moto (não em branco, até 50 caracteres)                                                                           |
| `placa`                | String | ❌           | Placa (único, 7–10 caracteres). Se ausente, `chassi` é obrigatório                                                          |
| `chassi`               | String | ❌           | Chassi (único, até 50 caracteres). Se ausente, `placa` é obrigatório                                                        |
| `problemaIdentificado` | String | ✅           | Problema inicial. **Valores válidos** (case-insensitive):<br>• reparos simples<br>• motor defeituoso<br>• danos estruturais |
| `vagaId`               | Long   | ❌           | Vincula a vaga existente (se informado, altera o status para OCUPADA)                                                       |

#### Fluxo no Service:

1.Valida problemaIdentificado e presença de pelo menos placa ou chassi.

2.Busca Vaga se vagaId informado.

3.Cria e persiste Moto, associando à Vaga (status e bidirecional).

4.Retorna MotoDTO.

#### Response 200 OK
```json
{
  "id": 7,
  "modelo": "CB500",
  "placa": "ABC1234",
  "chassi": "XYZ9876543210",
  "problemaIdentificado": "motor defeituoso",
  "vagaId": 5
}
```

Possíveis Erros:
| Erro                                                                | HTTP | Quando ocorre                        |
| ------------------------------------------------------------------- | ---- | ------------------------------------ |
| `RuntimeException: "O campo problemaIdentificado é obrigatório."`   | 500  | Se `problemaIdentificado` ausente    |
| `RuntimeException: "Problema identificado inválido. Use: [...]"`    | 500  | Se valor fora dos permitidos         |
| `RuntimeException: "Informe a placa ou o chassi obrigatoriamente."` | 500  | Se ambos `placa` e `chassi` ausentes |
| `RuntimeException: "Vaga não encontrada"`                           | 500  | Se `vagaId` inválido                 |

▶ GET /
Descrição: lista todas as motos cadastradas.
GET http://localhost:8080/motos

#### Response 200 OK

```json
[
  {
    "id": 3,
    "modelo": "Ninja 300",
    "placa": "DEF5678",
    "chassi": null,
    "problemaIdentificado": "reparos simples",
  },
  {
    "id": 5,
    "modelo": "Hornet 600",
    "placa": null,
    "chassi": "ABC123XYZ4567",
    "problemaIdentificado": "danos estruturais",
  }
]
```
▶ DELETE /{id}
Descrição: remove uma moto por ID.
DELETE http://localhost:8080/motos/{id}

| Parâmetro | Tipo | Descrição                 |
| --------- | ---- | ------------------------- |
| `id`      | Long | ID da moto a ser removida |

Response 204 No Content
Sem conteúdo no corpo, indica sucesso na remoção.

### 📂 Pátios  
**Base URL:** `http://localhost:8080/patios`

---

| Verbo  | Endpoint  | Descrição                                  |
| ------ | --------- | ------------------------------------------ |
| POST   | `/`       | Cria um novo pátio com vagas associadas    |
| GET    | `/`       | Lista todos os pátios cadastrados          |
| GET    | `/{id}`   | Busca um pátio específico por ID           |
| DELETE | `/{id}`   | Remove um pátio por ID                     |

---

#### ▶ POST `/`

**Descrição:** cria um novo pátio e vincula as vagas existentes.

POST http://localhost:8080/patios

```json
{
  "vagaIds": [1, 2, 3]
}
```

| Campo     | Tipo          | Obrigatório | Descrição                                                              |
| --------- | ------------- | ----------- | ---------------------------------------------------------------------- |
| `vagaIds` | `Array<Long>` | ❌           | IDs das vagas a associar ao pátio (opcional—pode ser vazio ou omitido) |

#### Fluxo no Service:

1.Se vagaIds informado, busca entidades Vaga correspondentes.

2.Instancia Patio e atribui a lista de Vaga.

3.Para cada Vaga, define o patio (bidirecional).

4,Persiste Patio (cascade sobre Vaga).

5.Retorna PatioDTO contendo id e vagaIds.

#### Response 200 OK
```json
{
  "id": 4,
  "vagaIds": [1, 2, 3]
}
```
Possíveis Erros:
| Cenário                                         | HTTP | Comportamento                                            |
| ----------------------------------------------- | ---- | -------------------------------------------------------- |
| IDs em `vagaIds` inexistentes                   | 200  | Vagas não encontradas são ignoradas (não lançam exceção) |
| Violação de restrição de FK ou outro erro de BD | 500  | `Internal Server Error`                                  |

▶ GET /
Descrição: lista todos os pátios cadastrados.
GET http://localhost:8080/patios

#### Response 200 OK
```json
[
  { "id": 1, "vagaIds": [1, 2] },
  { "id": 2, "vagaIds": [3] }
]
```

▶ GET /{id}
Descrição: busca um pátio específico por ID.
GET http://localhost:8080/patios/{id}

| Parâmetro | Tipo | Descrição            |
| --------- | ---- | -------------------- |
| `id`      | Long | ID do pátio desejado |

#### Response 200 OK
```json
{ "id": 1, "vagaIds": [1, 2] }
```
▶ DELETE /{id}
Descrição: remove um pátio pelo ID
DELETE http://localhost:8080/patios/{id}

| Parâmetro | Tipo | Descrição             |
| --------- | ---- | --------------------- |
| `id`      | Long | ID do pátio a remover |

Response 204 No Content
Sem conteúdo no corpo, indica remoção bem-sucedida.

### 📂 Usuários  
**Base URL:** `http://localhost:8080/usuarios`

---

| Verbo | Endpoint         | Descrição                                      |
| ----- | ---------------- | ---------------------------------------------- |
| POST  | `/`              | Registra novo usuário                          |
| POST  | `/login`         | Autentica usuário (admin ou mecânico)          |
| GET   | `/`              | Lista todos os usuários                        |
| GET   | `/{id}`          | Busca usuário por ID                           |
| PUT   | `/{id}`          | Atualiza dados de um usuário                   |
| DELETE| `/{id}`          | Remove usuário por ID                          |

---

#### ▶ POST `/`  
**Registra um novo usuário.**  
⚠️ **Não envie** campo `tipo` — o sistema define automaticamente:
- Se `email` = `mottu@gmail.com` **e** `senha` = `Geosense@2025` → ADMINISTRADOR  
- Caso contrário → MECÂNICO  

POST http://localhost:8080/usuarios

```json
{
  "nome": "Rafael",
  "email": "rsrafael@gmail.com",
  "senha": "teste@2025"
}
```
</details>

| Campo   | Tipo   | Obrigatório | Observações               |
| ------- | ------ | ----------- | ------------------------- |
| `nome`  | String | ✅           | 3–100 caracteres          |
| `email` | String | ✅           | Deve ser um e-mail válido |
| `senha` | String | ✅           | Mínimo 6 caracteres       |

Responses

201 Created
```json
{
  "id": 5,
  "nome": "Rafael",
  "email": "rsrafael@gmail.com",
  "senha": "teste@2025",
  "tipo": "MECANICO"
}
```

409 Conflict
```json
"Usuário com e-mail, nome ou senha já existente."
```
403 Forbidden
Quando tenta criar ADMINISTRADOR existente:
```json
"Administrador já existe. Use o login."
```
▶ POST /login
Autentica usuário e retorna mensagem ou vincula primeiro admin.
POST http://localhost:8080/usuarios/login
```json
{
  "email": "joao@email.com",
  "senha": "123456"
}
```
Lógica

Se credenciais = admin padrão e não existir ADMIN → cria e retorna 201 Created "Administrador vinculado".

Se credenciais = admin padrão e já existir → retorna 200 OK "Bem vindo ao modo administrador".

Caso contrário, tenta logar como MECÂNICO:

200 OK "Logado como mecânico: <nome>"

401 Unauthorized "Credenciais inválidas"

▶ GET /
Lista todos os usuários.
GET http://localhost:8080/usuarios
Response 200 OK
```json
[
  {
    "id": 1,
    "nome": "Administrador",
    "email": "mottu@gmail.com",
    "senha": "Geosense@2025",
    "tipo": "ADMINISTRADOR"
  },
  {
    "id": 2,
    "nome": "João",
    "email": "joao@email.com",
    "senha": "123456",
    "tipo": "MECANICO"
  }
]
```
▶ GET /{id}
Busca usuário por ID.
GET http://localhost:8080/usuarios/{id}

| Parâmetro | Tipo | Descrição              |
| --------- | ---- | ---------------------- |
| `id`      | Long | ID do usuário a buscar |

200 OK → retorna objeto Usuario.
404 Not Found → usuário não encontrado.

▶ PUT /{id}
Atualiza dados de um usuário existente.
PUT http://localhost:8080/usuarios/{id}
```json
{
  "nome": "João Alterado",
  "email": "joao_novo@email.com",
  "senha": "nova1234"
}
```
</details>

200 OK → retorna usuário atualizado.

404 Not Found → usuário não encontrado.

▶ DELETE /{id}
Remove um usuário por ID.
DELETE http://localhost:8080/usuarios/{id}

| Parâmetro | Tipo | Descrição               |
| --------- | ---- | ----------------------- |
| `id`      | Long | ID do usuário a remover |

204 No Content → remoção bem-sucedida.

🔑 Resumo das Regras
| Ação                 | `email`           | `senha`         | Resultado esperado         |
| -------------------- | ----------------- | --------------- | -------------------------- |
| Criar ADMIN          | `mottu@gmail.com` | `Geosense@2025` | Cria ou vincula ADMIN      |
| Criar MECÂNICO       | outro qualquer    | outro qualquer  | Cria MECÂNICO              |
| Criar ADMIN via POST | `mottu@gmail.com` | `Geosense@2025` | ⚠️ 403 Forbidden           |
| Login ADMIN          | `mottu@gmail.com` | `Geosense@2025` | 200 OK/201 Created → Admin |
| Login MECÂNICO       | e-mail válido     | senha correta   | 200 OK                     |
| Login falho          | inválido          |                 | 401 Unauthorized           |

### 📂 Vagas  
**Base URL:** `http://localhost:8080/vagas`

---

| Verbo  | Endpoint  | Descrição                                  |
| ------ | --------- | ------------------------------------------ |
| POST   | `/`       | Cria nova vaga no sistema                  |
| GET    | `/`       | Lista todas as vagas cadastradas           |
| GET    | `/{id}`   | Retorna dados de uma vaga específica       |
| DELETE | `/{id}`   | Remove uma vaga pelo ID                   |

---

#### ▶ POST `/`  
**Descrição:** cria uma nova vaga, opcionalmente atribuída a uma moto.

<details>
<summary><code>POST http://localhost:8080/vagas</code> – Request Body</summary>

```json
{
  "numero": 1,
  "status": "DISPONIVEL",
  "tipo": "REPARO_SIMPLES",
  "patioId": 2,
  "motoId": 5
}
```
</details>

| Campo     | Tipo | Obrigatório | Descrição                                                                        |
| --------- | ---- | ----------- | -------------------------------------------------------------------------------- |
| `numero`  | int  | ✅           | Número da vaga (>0, único dentro do pátio)                                       |
| `status`  | enum | ❌           | `DISPONIVEL` ou `OCUPADA` (padrão: `DISPONIVEL`)                                 |
| `tipo`    | enum | ✅           | `REPARO_SIMPLES`, `MOTOR_DEFEITUOSO`, `DANOS_ESTRUTURAIS`, `SEM_PLACA`           |
| `patioId` | Long | ✅           | ID do pátio onde a vaga será criada                                              |
| `motoId`  | Long | ❌           | ID da moto para ocupação imediata (se informado, altera `status` para `OCUPADA`) |

Fluxo no Service:

1-Valida existência de Patio.

2-Garante unicidade de numero dentro do pátio.

3-Cria Vaga com o status informado ou DISPONIVEL.

4-Se motoId informado:

 - Valida existência de Moto.

 - Associa moto → define status = OCUPADA.

5-Persiste Vaga e retorna VagaDTO.

Response 201 Created
```json
{
  "id": 10,
  "numero": 1,
  "status": "OCUPADA",
  "tipo": "REPARO_SIMPLES",
  "patioId": 2,
  "motoId": 5
}
```
Possíveis Erros:
| Erro                                              | HTTP | Quando ocorre                         |
| ------------------------------------------------- | ---- | ------------------------------------- |
| “Já existe uma vaga com esse número nesse pátio.” | 500  | `numero` duplicado no mesmo `patioId` |
| “Pátio não encontrado”                            | 500  | `patioId` inválido                    |
| “Moto não encontrada”                             | 500  | `motoId` inválido                     |

▶ GET /
Descrição: lista todas as vagas cadastradas.
GET http://localhost:8080/vagas

Response 200 OK
```json
[
  {
    "id": 1,
    "numero": 1,
    "status": "DISPONIVEL",
    "tipo": "REPARO_SIMPLES",
    "patioId": 2,
    "motoId": 3
  },
  {
    "id": 2,
    "numero": 2,
    "status": "OCUPADA",
    "tipo": "DANOS_ESTRUTURAIS",
    "patioId": 3,
    "motoId": 7
  }
]
```
▶ GET /{id}
Descrição: retorna os dados de uma vaga específica.
GET http://localhost:8080/vagas/{id}

| Parâmetro | Tipo | Descrição                   |
| --------- | ---- | --------------------------- |
| `id`      | Long | ID da vaga a ser consultada |

Response 200 OK
```json
{
  "id": 2,
  "numero": 2,
  "status": "OCUPADA",
  "tipo": "DANOS_ESTRUTURAIS",
  "patioId": 3,
  "motoId": 7
}
```
Response 404 Not Found
Nenhuma vaga encontrada com o ID informado.

▶ DELETE /{id}
Descrição: remove uma vaga pelo seu ID.
DELETE http://localhost:8080/vagas/{id}

| Parâmetro | Tipo | Descrição                 |
| --------- | ---- | ------------------------- |
| `id`      | Long | ID da vaga a ser removida |

Response 204 No Content
Sucesso na remoção (corpo vazio).

Response 404 Not Found
Vaga não encontrada.


---

<a id="como-executar"></a>
## ▶️ Como Executar
# 1. Clone o repositório
git clone https://github.com/seu-usuario/geosense.git
cd geosense

# 2. Ajuste suas credenciais
# em src/main/resources/application.properties

# 3. Run

Acesse em: http://localhost:8080/



