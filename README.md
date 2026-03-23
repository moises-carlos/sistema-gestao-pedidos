# Sistema de Organização de Pedidos

Um sistema backend de Ponto de Venda e gerenciamento de comandas desenvolvido em Java. Este projeto foi projetado para simular um ambiente de produção, com foco em princípios de Programação Orientada a Objetos (POO), Clean Code e Arquitetura em Camadas.

## Visão Geral

A aplicação gerencia o ciclo de vida completo de pedidos em um estabelecimento. Ela atua desde o cadastro de clientes e controle de catálogo de produtos até a transição de estados dos pedidos e o processamento de filas de prioridade. O armazenamento de dados é realizado através de uma simulação *In-Memory Database*, atuando como um mock para facilitar testes e execuções locais antes da integração com um banco de dados relacional.

## Arquitetura e Padrões Aplicados

O projeto adota uma arquitetura de software baseada em camadas, garantindo baixo acoplamento e alta coesão entre os componentes:

* **Core:** Interfaces genéricas e contratos base (como `CrudService`) para garantir a padronização das classes de serviço.
* **Models (Domain Layer):** Entidades de negócio ricas (`Cliente`, `Produto`, `Pedido`, `ItemPedido`) e enums estruturados.
* **Repositories (Data Access Layer):** Isolamento da persistência de dados.
* **Services (Business Logic Layer):** Centralização das regras de negócio, validações de integridade e lógica de processamento.
* **Exceptions:** Tratamento de erros customizado (ex: `ClienteNaoEncontradoException`) para garantir resiliência, impedindo interrupções abruptas da aplicação e fornecendo logs claros.

## Principais Funcionalidades

* **Gestão de Entidades:** Operações de cadastro e listagem para Clientes e Produtos.
* **Gerenciamento de Comandas:** Criação de pedidos com suporte a múltiplos itens, cálculo automatizado de subtotais e consolidação do valor total.
* **Máquina de Estados:** Transição segura do status do pedido (`NOVO`, `EM_PREPARO`, `PRONTO`, `FINALIZADO`, `CANCELADO`), com validações que impedem transições de regras de negócio inválidas (ex: alterar um pedido já finalizado).
* **Fila de Prioridade Dinâmica:** Algoritmo de ordenação utilizando a API de *Streams* e `Comparator` do Java para organizar a fila de processamento da cozinha com base nos níveis de prioridade pré-definidos.

## Estrutura do Projeto

```text
src/
└── com/moises/pedidos/
    ├── core/         # Contratos genéricos e interfaces (CrudService)
    ├── exception/    # Classes de exceções personalizadas
    ├── model/        # Entidades de domínio e Enumerações
    ├── repository/   # Camada de abstração de dados (In-Memory)
    ├── service/      # Camada de regras de negócio e validações
    └── Main.java     # Entrypoint da aplicação e interface de console
```

## Roadmap e Futuras Melhorias

Este projeto está em evolução contínua. As próximas etapas de desenvolvimento visam aproximar a aplicação de um ambiente corporativo real:

* **Integração com Banco de Dados:** Substituir a persistência *In-Memory* por um banco de dados relacional (PostgreSQL ou MySQL) utilizando **JPA/Hibernate** para mapeamento objeto-relacional (ORM).
* **Migração para API RESTful:** Evoluir a aplicação de console para um serviço web utilizando o ecossistema **Spring Boot**, expondo endpoints para que frontends e aplicativos móveis possam consumir as funcionalidades.
* **Cobertura de Testes Automatizados:** Implementar testes unitários e de integração utilizando **JUnit 5** e **Mockito** para garantir a estabilidade das regras de negócio e validações.
* **Autenticação e Autorização:** Implementar segurança na aplicação com **Spring Security e JWT**, criando perfis de acesso diferentes (ex: Atendente, Cozinheiro, Administrador).
* **Design Patterns:** Refatorar partes do código para aplicar padrões de projeto avançados, como *Strategy* (para cálculo de descontos) e *Factory* (para criação de pedidos complexos).
