# 6-Semestre-TP01-PB
Esta API foi projetada especificamente para gerenciar equipamentos de prevenção e combate a incêndios, com foco inicial em extintores, além de inspeções associadas a esses equipamentos.
A API permite o cadastro, atualização, exclusão e consulta tanto de extintores quanto de inspeções.
Durante o cadastro dos extintores, não é permitido registrar dois equipamentos com o mesmo número de controle interno.
Cada extintor pode passar por várias inspeções, no entanto, cada inspeção está vinculada a apenas um extintor.
Para realizar uma inspeção, é necessário que o extintor em questão já esteja cadastrado no sistema.

## Funcionalidades

- **Gerenciamento de Extintores**
  - Cadastro de extintores
  - Atualização de extintores
  - Exclusão de extintores
  - Consulta de extintores por ID e número de controle interno
  - Consulta do histórico de alterações dos extintores

- **Gerenciamento de Inspeções**
  - Cadastro de inspeções
  - Atualização de inspeções
  - Exclusão de inspeções
  - Consulta de inspeções por ID e por ID do extintor

- **Gerenciamento de Usuários**
  - Cadastro de usuários
  - Atualização de usuários
  - Exclusão de usuários
  - Consulta de usuários por ID

## Estrutura do Projeto

### Controllers

- **ExtintorController**
  - `GET /extintor`: Obtém todos os extintores
  - `GET /extintor/{id}`: Obtém um extintor pelo ID
  - `GET /extintor/controleInterno/{numeroControleInterno}`: Obtém um extintor pelo número de controle interno
  - `POST /extintor`: Adiciona um novo extintor
  - `DELETE /extintor/{id}`: Remove um extintor pelo ID
  - `PUT /extintor/{id}`: Atualiza um extintor pelo ID
  - `GET /extintor/historico/{numeroControleInterno}`: Obtém o histórico de um extintor pelo número de controle interno

- **InspecaoExtintorController**
  - `GET /inspecoes`: Obtém todas as inspeções
  - `GET /inspecoes/{id}`: Obtém uma inspeção pelo ID
  - `GET /inspecoes/extintor/{idExtintor}`: Obtém todas as inspeções de um extintor pelo ID
  - `POST /inspecoes`: Adiciona uma nova inspeção
  - `DELETE /inspecoes/{id}`: Remove uma inspeção pelo ID
  - `PUT /inspecoes/{id}`: Atualiza uma inspeção pelo ID

- **UsuarioController**
  - `GET /usuarios`: Obtém todos os usuários
  - `GET /usuarios/{id}`: Obtém um usuário pelo ID
  - `POST /usuarios`: Adiciona um novo usuário
  - `DELETE /usuarios/{id}`: Remove um usuário pelo ID
  - `PUT /usuarios/{id}`: Atualiza um usuário pelo ID

### Entidades

- **Extintor**
  - Representa um extintor com atributos como número de controle interno, número do cilindro, e data de vencimento.
  - Relacionado a inspeções e histórico de alterações.

- **ExtintorHistorico**
  - Registra as alterações feitas em um extintor, incluindo data, tipo de operação e os dados antigos.

- **InspecaoExtintor**
  - Representa uma inspeção realizada em um extintor, contendo informações sobre a condição do extintor e a data da inspeção.

- **Usuario**
  - Representa um usuário do sistema com informações como nome, email, senha e se é administrador.

### DTOs

- **ExtintorDTO**
  - Usado para transferir dados de extintores entre a camada de controle e a de serviço.

- **InspecaoExtintorDTO**
  - Usado para transferir dados de inspeções entre a camada de controle e a de serviço.

- **UsuarioDTO**
  - Usado para transferir dados de usuários entre a camada de controle e a de serviço.
