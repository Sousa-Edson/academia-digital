# Academia Digital

## Introdução
Esta API gerencia alunos, matrículas e avaliações físicas de uma academia. Os endpoints permitem criar, listar, atualizar e deletar esses recursos.

- **Base URL:** `http://localhost:8081`
- **Formato de data:** `dd/MM/yyyy`

## Endpoints

### 1. Alunos (`/alunos`)

#### Criar um aluno
**Requisição:**
```json
POST /alunos
Content-Type: application/json

{
  "nome": "João Silva",
  "cpf": "123.456.789-00",
  "bairro": "Centro",
  "dataDeNascimento": "02/03/2000"
}
```
**Resposta:**
```json
{
  "id": 1,
  "nome": "João Silva",
  "cpf": "123.456.789-00",
  "bairro": "Centro",
  "dataDeNascimento": "02/03/2000"
}
```

#### Listar todos os alunos
**Requisição:**
```http
GET /alunos
```
**Resposta:**
```json
[
  {
    "id": 1,
    "nome": "João Silva",
    "cpf": "123.456.789-00",
    "bairro": "Centro",
    "dataDeNascimento": "02/03/2000"
  }
]
```

#### Buscar um aluno por ID
**Requisição:**
```http
GET /alunos/1
```
**Resposta:**
```json
{
  "id": 1,
  "nome": "João Silva",
  "cpf": "123.456.789-00",
  "bairro": "Centro",
  "dataDeNascimento": "02/03/2000"
}
```

#### Atualizar um aluno
**Requisição:**
```json
PUT /alunos/1
Content-Type: application/json

{
  "nome": "João Pedro Silva",
  "bairro": "Vila Nova"
}
```
**Resposta:**
```json
{
  "id": 1,
  "nome": "João Pedro Silva",
  "cpf": "123.456.789-00",
  "bairro": "Vila Nova",
  "dataDeNascimento": "02/03/2000"
}
```

#### Deletar um aluno
**Requisição:**
```http
DELETE /alunos/1
```
**Resposta:** `204 No Content`

---

### 2. Matrículas (`/matriculas`)

#### Criar uma matrícula
**Requisição:**
```json
POST /matriculas
Content-Type: application/json

{
  "alunoId": 1
}
```
**Resposta:**
```json
{
  "id": 1,
  "aluno": {
    "id": 1,
    "nome": "João Silva"
  }
}
```

#### Listar todas as matrículas
**Requisição:**
```http
GET /matriculas
```
**Resposta:**
```json
[
  {
    "id": 1,
    "aluno": {
      "id": 1,
      "nome": "João Silva"
    }
  }
]
```

---

### 3. Avaliações Físicas (`/avaliacoes`)

#### Criar uma avaliação física
**Requisição:**
```json
POST /avaliacoes
Content-Type: application/json

{
  "alunoId": 1,
  "peso": 75.5,
  "altura": 1.80
}
```
**Resposta:**
```json
{
  "id": 1,
  "aluno": {
    "id": 1,
    "nome": "João Silva"
  },
  "peso": 75.5,
  "altura": 1.80
}
```

#### Listar todas as avaliações
**Requisição:**
```http
GET /avaliacoes
```
**Resposta:**
```json
[
  {
    "id": 1,
    "aluno": {
      "id": 1,
      "nome": "João Silva"
    },
    "peso": 75.5,
    "altura": 1.80
  }
]
```

#### Atualizar uma avaliação física
**Requisição:**
```json
PUT /avaliacoes/1
Content-Type: application/json

{
  "peso": 78.0,
  "altura": 1.82
}
```
**Resposta:**
```json
{
  "id": 1,
  "aluno": {
    "id": 1,
    "nome": "João Silva"
  },
  "peso": 78.0,
  "altura": 1.82
}
```

#### Deletar uma avaliação física
**Requisição:**
```http
DELETE /avaliacoes/1
```
**Resposta:** `204 No Content`

## Tratamento de Erros
A API retorna mensagens de erro padronizadas:

- `400 Bad Request`: Requisição inválida (exemplo: dados ausentes ou formato incorreto)
- `404 Not Found`: Recurso não encontrado
- `500 Internal Server Error`: Erro inesperado no servidor

**Exemplo de erro:**
```json
{
  "timestamp": "02/03/2024T10:30:00",
  "status": 404,
  "error": "Recurso não encontrado",
  "message": "Aluno não encontrado para o ID: 99"
}
```

## Conclusão
Este projeto foi feito como lab da DIO.me, com o objetivo de criar uma API RESTful para gerenciamento de alunos e matrículas. 🚀

