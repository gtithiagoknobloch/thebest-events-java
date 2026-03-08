# Sistema de Gerenciamento de Eventos 🎉

## 📌 Descrição
Este projeto foi desenvolvido como parte de um exercício acadêmico para demonstrar o aprendizado em **Programação Orientada a Objetos (POO)** utilizando Java.  
O sistema permite o **cadastro de usuários e eventos**, além de funcionalidades de participação e cancelamento em eventos.

O foco principal é aplicar conceitos como:
- **Classes e Objetos**
- **Métodos**
- **Alta coesão e baixo acoplamento**
- **Validação de entradas**
- **Uso de enums (Categoria)**
- **Persistência simples de dados (GerenciadorEventos)**

---

## 🚀 Funcionalidades
- **Cadastro de Usuário**
  - Nome, Email, Cidade e Idade.
  - Validação para impedir campos vazios.
  - Idade aceita apenas números positivos.
  - Possibilidade de abortar o cadastro com `-1`.

- **Cadastro de Evento**
  - Nome, Endereço, Categoria, Horário e Descrição.
  - Categoria escolhida por índice (1-FESTA, 2-ESPORTE, 3-SHOW, 4-CULTURAL, 5-OUTROS).
  - Validação para impedir campos vazios.
  - Horário deve seguir o formato `dd/MM/yyyy HH:mm`.
  - Possibilidade de abortar o cadastro com `-1`.

- **Participação em Eventos**
  - Usuário pode escolher um evento pelo índice e confirmar participação.
  - Possibilidade de voltar sem participar (`-1`).

- **Cancelamento de Participação**
  - Usuário pode cancelar participação em eventos confirmados.
  - Possibilidade de voltar sem cancelar (`-1`).

- **Listagem de Eventos**
  - Todos os eventos cadastrados.
  - Eventos ocorrendo agora.
  - Eventos passados.
  - Eventos confirmados pelo usuário.

---

## 🎨 Interface no Console
- Menu principal colorido com **ANSI codes**:
  - Azul para título.
  - Verde para opções de cadastro.
  - Ciano para participação/cancelamento.
  - Amarelo para consultas.
  - Vermelho para sair.

---

## 🛠️ Estrutura de Classes
- **Sistema** → Classe principal, responsável pelo menu e interação com o usuário.
- **Usuario** → Representa um usuário, com dados pessoais e lista de eventos confirmados.
- **Evento** → Representa um evento, com nome, endereço, categoria, horário e descrição.
- **Categoria (enum)** → Define os tipos de evento (FESTA, ESPORTE, SHOW, CULTURAL, OUTROS).
- **GerenciadorEventos** → Responsável por cadastrar, listar e persistir eventos.

---

## 📖 Conceitos Demonstrados
- **Programação Orientada a Objetos (POO)**
- **Encapsulamento**
- **Enums**
- **Validação de entrada**
- **Tratamento de erros de digitação**
- **Alta coesão**: cada classe tem responsabilidade clara.
- **Baixo acoplamento**: classes interagem de forma organizada e independente.

---

## ▶️ Como Executar
1. Compile o projeto:
   ```bash
   javac Sistema.java
2. Dentro da pasta do projeto digite:
    ```bash
   java -cp bin Sistema

## 🐧 Dados sobre os testes
1. Compilado e testado no Linux Mint e com a versão do Java conforme a seguir!
    openjdk version "17.0.18" 2026-01-20
    OpenJDK Runtime Environment (build 17.0.18+8-Ubuntu-124.04.1)
    OpenJDK 64-Bit Server VM (build 17.0.18+8-Ubuntu-124.04.1, mixed mode, sharing)
