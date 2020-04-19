# assembleia-cooperativa

Requisitos de Ambiente
	
	Java 11
	Gradle 6.2.2
  PostgreSQL 12
 
Configuração de Banco de Dados

		Criar Banco de Dados "assembleia" no PostgreSQL, usando usuário postgres e senha 123456 (ou adaptar  
	essas configurações no arquivo resources/application.properties):
    - CREATE DATABASE assembleia with owner postgres;

Compilação e testes

	gradlew test

Execução

	gradlew bootRun
	
		Ao finalizar o comando acima, a API REST estará disponível em http://localhost:8080/assembleia/v1.0.0/, 
	e a documentação da API gerada pelo Swagger estará disponível em 
	http://localhost:8080/assembleia/v1.0.0/swagger-ui.html#/


Documentação API

		Conforme comentado no item anterior, ao rodar a aplicação, na mesma porta em que a aplicação está rodando,
	é disponibilizada uma página HTML, contendo a documentação da API. Isso é ralizado fazendo uso da ferramenta 
	Swagger (https://swagger.io/), que constrói essa documentação baseada na estrutura de controllers e classes de 
	requisições e respostas, permitindo uma descrição das operações e dos componentes dentro do próprio código. O link
	abaixo é um documento baseado na documentação gerada pelo Swagger:
	https://docs.google.com/document/d/1INldnCpntCRKkZmyrMnL8oVUnL1Lm_1jlzXHjsaXxGY
	
Informações sobre a Implementação

	Frameworks e bibliotecas utilizados: 
		- Spring Boot (configuração da aplicação, controle de dependências e recursos para o repositório de dados) 
		- Spock (testes)
		- Lombok (simplificação de código de classes, operação de logs e builders)
		
	Organização interna da aplicação:
		De modo geral, a aplicação é organizada no modelo de 3 camadas de controle (interface com cliente), 
		serviço (controle de regras de negócio e comunicação entre camadas) e repositório (armazenamento de dados).
		Esse tipo de arquitetura tem se popularizado bastante, pelo fato de permitir o desenvolvimento com maior 
		separação de responsabilidades entre as classes e redução do acoplamento.
		Outro ponto relevante é a separação de um pacote contendo classes e interfaces que são relevantes para um
		cliente da API: classes de Request aceitas pela API e de Response a serem esperadas, interfaces das 
		controllers, que expõe o contrato a ser seguido. Essa separação é útil porque possibilita disponibilizar 
		essas informações para os clientes sem a necessidade de expor detalhes da implementação. O uso de interface
		para as controllers também é útil para o cadastro de anotações Spring Web, Swagger e validação dos
		parâmetros das requisições.
		Além disso, foram utilizados fundamentos de DDD (Domain Driven Design), como o uso de modelagem rica, onde
		onde as entidades (classes que representam conceitos do negócio que possuem informações que precisam ser 
		persistidas e/ou consultadas) assumem um papel importante no objetivo de controlar das regras de negócio, 
		fornecendo métodos relevantes, auxiliando os serviços a realizarem esse objetivo.
		
Tratamento de Erros e Exceções


	
