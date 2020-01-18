# Movies Api

Api REST que retorna a lista de indicados e vencedores da categoria Pior Filme do Golden Raspberry Awards. Assim como retorna os produtores ganhadores dos prêmios, com maior e menor intervalo entre os prêmios recebidos.

## Tecnologias Principais

+ Spring Boot
+ Spring JPA
+ H2 Database in memory
+ OpenCSV

## Linguagem

Java 8

## Execução

**Start da aplicação**: ApiApplication

**DataBase**: moviesdb

**Urls**: 

*{server}/consulta* -> retorna a lista de indicados e premiados

*{server}/consulta/intervalo-premios* -> retorna os 2 produtores com maior e menor intervalo entre prêmios

## API Documentada 

Swagger -> {server}/swagger-ui.html

## Testes

**Classe:** MoviesControllerTest

**Métodos:**

findAllMovies() -> testa retorno json que deve conter todos os filmes inclusos no teste

findAwardWinners() -> testa retorno json que deve conter 2 produtores com intervalos entre prêmios minimo e máximo




## Autor
Laryssa Alves - 2020
