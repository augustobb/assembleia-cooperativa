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
	serviço (controle de regras de negócio e comunicação entre camadas) e repositório (armazenamento de dados). Esse 
	tipo de arquitetura tem se popularizado bastante, pelo fato de permitir o desenvolvimento com maior separação de 			responsabilidades entre as classes e redução do acoplamento.
		Outro ponto relevante é a separação de um pacote contendo classes e interfaces que são relevantes para um
	cliente da API: classes de Request aceitas pela API e de Response a serem esperadas, interfaces das controllers, 
	que expõe o contrato a ser seguido. Essa separação é útil porque possibilita disponibilizar essas informações para 
	os clientes sem a necessidade de expor detalhes da implementação. O uso de interface para as controllers também é 
	útil para o cadastro de anotações Spring Web, Swagger e validação dos parâmetros das requisições, deixando a
	implementação das controllers mais limpa, sem todas essas anotações.
		Além disso, foram utilizados fundamentos de DDD (Domain Driven Design), como o uso de modelagem rica, onde 
	as entidades (classes que representam conceitos do negócio que possuem informações que precisam ser persistidas 
	e/ou consultadas) assumem um papel importante no objetivo de controlar das regras de negócio, fornecendo métodos 
	relevantes, auxiliando a camada de serviço a realizar esse objetivo.
		
Tratamento de Erros e Exceções

		Para o tratamento de erros, foi utilizando um Handler (BaseExceptionHandler), que permite um controle 
	centralizado das exceções, facilitando uma padronização das mensagens de erro, usando de mensagens definidas em
	arquivo de properties. Outra vantagem do uso de handler é poder realizar operações adicionais que devem ser feitas
	pelo sistema quando um erro ocorre. Na nossa aplicação, um exemplo disso são as mensagens de logs dos erros, pois
	usando o handler, tiramos essa responsabilidade da camada de negócio, fazendo com que ela se preocupe apenas em
	lançar as exceções.
		Como a API não possui sistema de autenticação nem restrições de acesso, as possibilidades de erro se 
	resumem a 3 tipos:
	- erros de parâmetros inválidos ou requisições mal formuladas (status 400, BAD REQUEST)
	- erros de negócio, usando uma exceção própria, chamada de BusinessExcpetion (status 422, UNPROCESSABLE_ENTITY)
	- erros não esperados (status 500, INTERNAL_SERVER_ERROR)
	
Modelagem Utilizada

Testes Automatizados

Integração com Sistemas Externos

Mensageria, Filas e Performance

Versionamento da API



	
