# Projeto de Biblioteca - Sistema RESTful para Criação e Empréstimo de Livros

Este projeto é o MVP para uma aplicação simples desenvolvida em Java utilizando o estilo arquitetural RESTful e clean
architecture
para a gestão de uma biblioteca escolar. Ele permite a criação, atualização, remoção e empréstimo de livros, fornecendo
endpoints para interação com a aplicação.

## Tecnologias Utilizadas

- Java
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate
- Lombok
- Banco de Dados MySQL
- Postman
- JWT
- Princípios de Clean Arch
- Jackson

# Consumo da Aplicação

> [!TIP]
> No diretório `./Postman Export` existe um arquivo nomeado `Biblioteca.postman_collection.json` com todos os endpoints configurados.

A aplicação é acessada através de endpoints HTTP. Aqui está o mapa dos endpoints disponíveis: **[Endpoints Disponíveis](./ENDPOINTS.md)**

# Banco de Dados

> [!TIP]
> No diretório `./mySQL` Contem arquivos úteis, como o arquivo sql para criação do banco, o schema para futura edição e uma imagem do banco para melhor interpretação.

Foi utilizado o MySQL como banco de dados devido ao seu desempenho, escalabilidade e ampla adoção no mercado,
facilitando futuras implementações colaborativas.

# Como Executar

**Pré-requisitos:**

- JDK instalado (versão 8 ou superior)
- Maven instalado
- Terminal de comandos

**Clonar o Repositório:**

```sh
git clone https://github.com/pedroHenrique57/ProjetoBiblioteca.git
```

## Configurar o Banco de Dados

Configure as propriedades do banco de dados no arquivo `application.properties`.

## Compilar e Executar o Projeto

> [!WARNING]
> Dentro de um terminal, verifique se você está no diretório do projeto `./biblioteca` para rodar o comando.  
> caso esteja com dúvida verifique se existe um arquivo nomeado `Pom.xml` no diretório.

Navegue até o diretório do projeto e abra um terminal. Neste terminal execute o seguinte comando:

```sh
mvn spring-boot:run
```

## Contribuições

Contribuições são bem-vindas! Sinta-se à vontade para abrir issues e pull requests para melhorias e correções.

## Licença

Este projeto está licenciado sob a licença MIT. Acesse o arquivo `LICENSE` para mais informações.
