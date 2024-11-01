# API de Gerenciamento de Contas

Esta API permite gerenciar clientes, fornecedores, contas a receber e contas a pagar. A API é construída usando Spring e utiliza um banco de dados H2 em memória.

## Base URL
A API está disponível em: 
http://localhost:8080



## Endpoints

### 1. Clientes

#### Criar um novo cliente

**Endpoint:** `POST /api/clientes`

**Exemplo de Request:**
```json
{
  "nome": "João"
}
```


### 2. Fornecedor

#### Criar um novo fornecedor

**Endpoint:** `POST /api/fornecedor`

**Exemplo de Request:**
```json
{
  "nome": "Bruno"
}

```

### 3. Contas a Pagar 

#### Criar uma nova conta a pagar

**Endpoint:** `POST /api/contas_pagar`

**Exemplo de Request:**
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
### 4. Contas a Receber 

#### Criar uma nova conta a receber

**Endpoint:** `POST /api/contas_receber`

**Exemplo de Request:**
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
