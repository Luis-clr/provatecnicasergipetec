# provatecnicasergipetec
# 🏖️ Sistema de Gestão de Férias - Desafio Técnico

Solução desenvolvida para o desafio técnico, focada na história de usuário do servidor "João", permitindo a consulta simples e organizada de períodos de férias e informações financeiras associadas.

## 📋 Sobre o Projeto

O objetivo foi criar um protótipo funcional onde um servidor público possa:
1.  Visualizar seus períodos de férias (passados, planejados e futuros).
2.  Ver detalhes financeiros (pagamentos) de cada período.
3.  Acompanhar o status das solicitações.
4.  Realizar novas solicitações de férias (Bônus).


## 🚀 Tecnologias Utilizadas

- **Linguagem:** Java 17
- **Framework:** Spring Boot 3 (Web, Data JPA, Validation)
- **Banco de Dados:** PostgreSQL 16
- **Infraestrutura:** Docker & Docker Compose
- **Design/Prototipagem:** Excalidraw

---

## 🛠️ Como Executar o Projeto

A aplicação está totalmente containerizada. Para rodar, você precisa apenas ter o **Docker** e o **Docker Compose** instalados.

### Passo a Passo

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/Luis-clr/provatecnicasergipetec.git
   cd código da API/sistema-ferias
   docker-compose up --build
   API disponível em: http://localhost:8080
   Banco disponível em: localhost:5432
