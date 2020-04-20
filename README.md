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
		
		Sobre a implementação da API, desconsiderando as operações sobre associados, nota-se que todas as demais
	operações estão na mesma rota "/pautas". A implementação foi feita dessa forma porque, no fim das contas, todo o
	processo de votação gira em torno das pautas: pautas devem ser criadas, pautas devem poder ter sessão aberta,
	pautas devem ser votadas e, por fim, pautas devem ter resultados obtidos. Contudo, é claro, essas operações acabam
	sendo divididas internamente entre serviços especializados.
	
Informações sobre a Implementação

	Frameworks e bibliotecas utilizados: 
		- Spring Boot (configuração da aplicação, controle de dependências e recursos para o repositório de dados) 
		- Spock (testes)
		- Lombok (simplificação de código de classes, operação de logs e builders)
		
	Organização interna da aplicação:
	
		De modo geral, a aplicação é organizada no modelo de 3 camadas de controle (interface com cliente), 
	serviço (controle de regras de negócio e comunicação entre camadas) e repositório (armazenamento de dados). Esse 
	tipo de arquitetura tem se popularizado bastante, pelo fato de permitir o desenvolvimento com maior separação de 
	responsabilidades entre as classes e redução do acoplamento.
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

	Linik para o documento com o diagrama ER e comentários sobre a modelagem utilizada:
	https://docs.google.com/document/d/1TLbPpUUeqBk4dwa2BAG0sypJFRXDfyJwhcP-UaNIwT0
	
Testes Automatizados

		A implementação de testes unitários foi feita usando o framework Spock, que possibilita o desenvolvimento 
	de testes bem documentados e organizados, seguindo o padrão "given-when-then", de modo que os testes sirvam 
	realmente como uma especificação do comportamento ao utilizar-se a unidade do sistema que está sendo testada.
		O próximo passo a ser realizado na aplicação é a implementação de testes de integração, subindo a 
	aplicação em um contexto mockado, para verificar o funcionamento geral da API. Esses testes serão bastante 
	importantes, pois oferecem uma análise complementar à fornecida pelos testes unitários, principalmente na a 
	verificação de falhas em configurações, validação de parâmetros dos métodos da API e análise do tratamento de 
	erros. Além disso, seria de igual importância o aumento na cobertura de testes unitários, pois algumas classes
	ainda não estão cobertas.
	

Integração com Sistemas Externos

		A integração com sistemas externos foi implementada com um cliente que usa a classe RestTemplate, 
	fornecida pelo pacote Spring Web. Para este cliente, também foi criada uma classe de resposta, com o conteúdo
	esperado do serviço externo. Baseado no valor obtido desse serviço o sistema verifica se o CPF é valido, e 
	depois, durante o processo de controle do voto, é feita a análise de habilitação de voto.

Mensageria, Filas e Performance

		Na implementação da API, não foi utilizado sistema de mensageria para emissão dos reusltados da votação.
	No entanto, seria plenamente viável o uso de uma ferramenta como o Kafka para disponibilizar o resultado de cada
	votação, para que pudesse ser lido por aplicações consumidoras (que possivelmente seria a mesma que enviaria os 
	votos). Nesse cenário, o serviço da aplicação que é responsável pelo encerramento das sessões seria um Producer.
		O uso de mensageria e filas também seria interessante para o cenário de grande volume de votos, em que
	poderia ocorrer uma sobrecarga no sistema e, dessa forma, gerar problemas no registro dos votos. Fazendo o uso
	de filas, esse registro poderia ser feito de forma assíncrona, tornando mais o processo de votação mais 
	confiável.

Versionamento da API

		A versão inicial disponibilizada da API é v1.0.0. Com isso, seria possível aplicar um versionamento
	semântico (https://semver.org/), onde o primeiro número é incrementado a cada alteração incompatível (zerando
	os demais números), o segundo é incrementado a cada adição de funcionalidade com alteração compatível (zerando 
	o terceiro número), e o terceiro é incrementado a cada correção de bug.
		Conforme indicado pela URL gerada pela aplicação, a escolha da versão seria por URI path (assim como 
	poderia ser de outras formas, como parâmetro da requisição, por HTTP header, etc). O Swagger e o Spring 
	fornecem recursos que possibilitam distinguir dentre os métodos das controllers a qual versão aquele método 
	pertence, além de poder sinalizar possíveis métodos deprecados. Dessa forma, seria possível manter a
	documentação do Swagger completa, com todas as versões, onde o cliente pode selecionar qual documentação ele
	deseja visualizar. Para utilizar esses recursos sem ter que manter implementações antigas no código de produção,
	uma possibilidade seria manter os métodos antigos apenas na interface das controllers, definindo eles como 
	default.
	
