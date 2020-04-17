# assembleia-cooperativa

Requisitos de Ambiente
	
	Java 11
	Gradle 6.2.2
  PostgreSQL 12
 
Configuração de Banco de Dados

	Criar Banco de Dados "assembleia" no PostgreSQL, usando usuário postgres e senha 123456 (ou adaptar essas configurações no arquivo resources/application.properties):
    - CREATE DATABASE assembleia with owner postgres;

Compilação e testes

	gradlew test

Execução

	gradlew bootRun
	
	Ao finalizar o comando acima, a API REST estará disponível em http://localhost:8080/assembleia/v1.0.0/, e a documentação da API gerada pelo Swagger estará disponível em http://localhost:8080/assembleia/v1.0.0/swagger-ui.html#/

