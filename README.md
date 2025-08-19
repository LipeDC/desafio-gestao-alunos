📚 Sistema de Gestão de Alunos e Matrículas

Este projeto é uma API REST desenvolvida em Java com Spring Boot, cujo objetivo é gerenciar alunos, cursos e matrículas. Ele utiliza DTOs (Data Transfer Objects) para comunicação entre camadas, Mapper para conversão entre entidades e respostas, e JPA/Hibernate para persistência no banco de dados.

🚀 Funcionalidades

👨‍🎓 Alunos

Cadastrar novos alunos

Listar todos os alunos

Buscar matrículas de um aluno específico

Atualizar dados de um aluno (e suas matrículas)

Remover aluno

📖 Matrículas

Associadas diretamente ao aluno

Incluem: código da matrícula, curso e data de início

🛠️ Tecnologias Utilizadas

Java

Spring Boot 3

Spring Data JPA

H2 em memória para testes

Lombok (para reduzir boilerplate)

DTOs + Mapper para separar camadas
