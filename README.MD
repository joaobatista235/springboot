# API de Gerenciamento de Contas 💼

Esta API permite gerenciar clientes, fornecedores, contas a receber e contas a pagar. Desenvolvida com **Spring** e utilizando um banco de dados **H2 em memória**, ela oferece uma solução rápida e eficiente para o gerenciamento financeiro.

---

## Índice 📚

- [Visão Geral](#visão-geral-)
- [Base URL](#base-url-)
- [Endpoints](#endpoints-)
  - [Clientes](#clientes-)
  - [Fornecedores](#fornecedores-)
  - [Contas a Pagar](#contas-a-pagar-)
  - [Contas a Receber](#contas-a-receber-)
- [Tecnologias Utilizadas](#tecnologias-utilizadas-)
- [Como Executar](#como-executar-)
- [Contribuição](#contribuição-)
- [Licença](#licença-)

---

## Visão Geral 🌟

A **API de Gerenciamento de Contas** é uma solução robusta para o gerenciamento de clientes, fornecedores, contas a pagar e contas a receber. Desenvolvida com o framework **Spring**, ela utiliza um banco de dados **H2 em memória** para facilitar testes e desenvolvimento.

---

## Base URL 🌐

A API está disponível em:  
`http://localhost:8080`

---

## Endpoints 🛠️

### Clientes

#### Criar um novo cliente
- **Endpoint**: `POST /api/clientes`
- **Exemplo de Request**:
  ```json
  {
    "nome": "João"
  }
  ```

---

### Fornecedores

#### Criar um novo fornecedor
- **Endpoint**: `POST /api/fornecedor`
- **Exemplo de Request**:
  ```json
  {
    "nome": "Bruno"
  }
  ```

---

### Contas a Pagar

#### Criar uma nova conta a pagar
- **Endpoint**: `POST /api/contas_pagar`
- **Exemplo de Request**:
  ```json
  {
    "emissao": "2024-10-31",
    "vencimento": "2024-11-30",
    "fornecedor": {
      "id": 1
    },
    "valor": 1500.00
  }
  ```

---

### Contas a Receber

#### Criar uma nova conta a receber
- **Endpoint**: `POST /api/contas_receber`
- **Exemplo de Request**:
  ```json
  {
    "emissao": "2024-10-31",
    "vencimento": "2024-11-30",
    "cliente": {
      "id": 1
    },
    "valor": 1500.00
  }
  ```

---

## Tecnologias Utilizadas 🛠️

- **Spring Boot**: Framework para desenvolvimento da API.
- **H2 Database**: Banco de dados em memória para testes e desenvolvimento.
- **Maven**: Gerenciador de dependências.
- **Java**: Linguagem de programação principal.

---

## Como Executar 🚀

Siga os passos abaixo para rodar o projeto localmente:

1. **Clone o repositório**:
   ```bash
   git clone https://github.com/seu-usuario/api-gerenciamento-contas.git
   cd api-gerenciamento-contas
   ```

2. **Compile o projeto**:
   ```bash
   mvn clean install
   ```

3. **Execute a aplicação**:
   ```bash
   mvn spring-boot:run
   ```

4. **Acesse a API**:
   - A API estará disponível em: `http://localhost:8080`.

---

## Contribuição 🤝

Contribuições são bem-vindas! Siga os passos abaixo:

1. Faça um fork do projeto.
2. Crie uma branch para sua feature:
   ```bash
   git checkout -b feature/nova-feature
   ```
3. Commit suas alterações:
   ```bash
   git commit -m "Adiciona nova feature"
   ```
4. Envie as alterações:
   ```bash
   git push origin feature/nova-feature
   ```
5. Abra um Pull Request.

---

## Licença 📜

Este projeto está licenciado sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

---

Feito com ❤️ por [João Batista](https://github.com/joaobatista235).
