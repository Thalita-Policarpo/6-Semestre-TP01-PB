# 6-Semestre-TP01-PB
Esta API foi projetada especificamente para gerenciar equipamentos de prevenção e combate a incêndios, com foco inicial em extintores, além de inspeções associadas a esses equipamentos.
A API permite o cadastro, atualização, exclusão e consulta tanto de extintores quanto de inspeções.
Durante o cadastro dos extintores, não é permitido registrar dois equipamentos com o mesmo número de controle interno.
Cada extintor pode passar por várias inspeções, no entanto, cada inspeção está vinculada a apenas um extintor.
Para realizar uma inspeção, é necessário que o extintor em questão já esteja cadastrado no sistema.


# Endpoints:
ExtintorController:

• GET /extintor: Retorna todos os extintores cadastrados.

• GET /extintor/{id}: Retorna um extintor específico com base no ID fornecido.

• GET /extintor/controleInterno/{numeroControleInterno}: Retorna um extintor com base no número de controle interno.

• POST /extintor: Adiciona um novo extintor.

• DELETE /extintor/{id}: Exclui um extintor com base no ID fornecido.

• PUT /extintor/{id}: Atualiza as informações de um extintor existente com base no ID fornecido.




InspecaoExtintorController:

• GET /inspecoes: Retorna todas as inspeções de extintores realizadas.

• GET /inspecoes/{id}: Retorna uma inspeção específica com base no ID fornecido.

• GET /inspecoes/extintor/{idExtintor}: Retorna todas as inspeções associadas a um extintor específico.

• POST /inspecoes/{idExtintor}: Adiciona uma nova inspeção para um extintor específico.

• DELETE /inspecoes/{id}: Exclui uma inspeção com base no ID fornecido.

• PUT /inspecoes/{id}: Atualiza as informações de uma inspeção existente com base no ID fornecido.
