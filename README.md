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


Documentação API

	Conforme comentado no item anterior, ao rodar a aplicação, na mesma porta em que a aplicação está rodando, é disponibilizada uma página HTML, contendo a documentação da API. Isso é feito fazendo uso da ferramenta Swagger (https://swagger.io/), que constrói essa documentação baseada na estrutura de controllers e classes de requisições e respostas, permitindo uma descrição das operações e dos componentes dentro do próprio código. O link abaixo é um documento baseado na documentação gerada pelo Swagger:
	https://docs.google.com/document/d/1INldnCpntCRKkZmyrMnL8oVUnL1Lm_1jlzXHjsaXxGY
	
