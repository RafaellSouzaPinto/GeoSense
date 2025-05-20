# GeoSense
*Transforme seu pátio em um espaço inteligente, seguro e eficiente*

---

<p align="center">
  <img src="https://img.shields.io/badge/Spring%20Boot-3.2.5-blue.svg" alt="Spring Boot"/>
  <img src="https://img.shields.io/badge/Java-17-orange.svg" alt="Java 17"/>
  <img src="https://img.shields.io/badge/License-MIT-green.svg" alt="License"/>
</p>

---

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
| Descrição do Projeto          | [#descrição-do-projeto](#📖-descrição-do-projeto)        |
| Problemas Identificados       | [#problemas-identificados](#🛑-problemas-identificados)    |
| Solução Proposta              | [#solução-proposta](#🚀-solução-proposta)                |
| Benefícios                    | [#benefícios](#🎯-benefícios)                          |
| Como Funciona                 | [#como-funciona](#⚙️-como-funciona)                     |
| Arquitetura                   | [#arquitetura](#🏗️-arquitetura)                        |
| **Funcionalidades & Testes**  | [#funcionalidades--testes](#⚡-funcionalidades--testes) |
| Como Executar                 | [#como-executar](#▶️-como-executar)                      |
| Contribuição                  | [#contribuição](#🤝-contribuição)                       |
| Licença                       | [#licença](#📄-licença)                                 |

---

## 🛑 Problemas Identificados

1. **Localização manual** de motos  
2. **Dados fragmentados** sem painel unificado  
3. **Downtime elevado** e inatividade da frota  
4. **Vulnerabilidade** a furtos/extravios  
5. **Escalabilidade lenta** em novas filiais

---

## 🚀 Solução Proposta

GeoSense digitaliza e centraliza todas as informações do pátio, entregando:

- 📍 Mapeamento em **tempo real**  
- 🤖 Alocação **inteligente** de vagas  
- 🔄 Atualização **automática** de status  
- 🔔 **Alertas e filtros** avançados  
- 📲 Dashboard **mobile** com notificações

---

## 🎯 Benefícios

- **80%+** redução no tempo de busca  
- Decisões **proativas** com dados em tempo real  
- Aumento na **disponibilidade** da frota  
- Segurança reforçada e menos furtos  
- Expansão **rápida** e padronizada

---

## ⚙️ Como Funciona

1. 🚀 **Registro**: mecânico informa defeito no app  
2. 🤖 **Processamento**: sugerimos a vaga ideal  
3. 📋 **Alocação**: vaga e moto vinculadas no sistema  
4. 🔍 **Busca**: filtros por modelo, status ou tempo  
5. 🔔 **Monitoramento**: notificações de prazos

---

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

⚡ Funcionalidades & Testes

Atenção: esta é a seção mais importante! Teste cada endpoint através do Postman.

📂 Alocação de Moto

POST /api/v1/alocacoesAloca moto em vaga disponível.

{
  "motoId": 1,
  "vagaId": 10,
  "mecanicoId": 2
}

GET /api/v1/alocacoes — Lista todas as alocações

GET /api/v1/alocacoes/{id} — Busca alocação por ID

DELETE /api/v1/alocacoes/{id} — Remove alocação por ID

📂 Defeitos

POST /api/v1/defeitosRegistra novo defeito.

{
  "tiposDefeitos": "MOTOR_DEFEITUOSO",
  "descricao": "Falha no motor",
  "motoId": 1
}

GET /api/v1/defeitos — Lista todos os defeitos

GET /api/v1/defeitos/{id} — Busca defeito por ID

DELETE /api/v1/defeitos/{id} — Remove defeito por ID

📂 Motos

POST /api/v1/motosCadastra nova moto.

{
  "modelo": "CB500",
  "placa": "ABC1234",
  "chassi": "XYZ9876543210",
  "problemaIdentificado": "Freio traseiro"
}

GET /api/v1/motos — Lista todas as motos

DELETE /api/v1/motos/{id} — Remove moto por ID

📂 Pátios

POST /api/v1/patiosCria novo pátio.

{
  "vagas": [
    { "numero": 1, "tipo": "REPARO_SIMPLES" },
    { "numero": 2, "tipo": "MOTOR_DEFEITUOSO" }
  ]
}

GET /api/v1/patios — Lista todos os pátios

GET /api/v1/patios/{id} — Busca pátio por ID

DELETE /api/v1/patios/{id} — Remove pátio por ID

📂 Usuários

POST /api/v1/usuariosRegistra novo usuário.

{
  "nome": "João",
  "email": "joao@email.com",
  "senha": "123456",
  "tipo": "MECANICO"
}

POST /api/v1/usuarios/loginAutentica e retorna JWT.

{ "email": "joao@email.com", "senha": "123456" }

GET /api/v1/usuarios — Lista todos os usuários

GET /api/v1/usuarios/{id} — Busca usuário por ID

PUT /api/v1/usuarios/{id} — Atualiza usuário (mesmo payload do POST)

DELETE /api/v1/usuarios/{id} — Remove usuário por ID

📂 Vagas

POST /api/v1/vagasCria nova vaga.

{ "numero": 5, "tipo": "SEM_PLACA", "patioId": 1 }

GET /api/v1/vagas — Lista todas as vagas

GET /api/v1/vagas/{id} — Busca vaga por ID

DELETE /api/v1/vagas/{id} — Remove vaga por ID
