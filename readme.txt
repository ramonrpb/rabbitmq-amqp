Não foi possível atender a todos os requisitos solicitados.

Não consegui executar os 3 containers no mesmo docker-compose.yml, não obtive conhecimento suficiente para atender a este requisito.
O maior problema encontrado foi subir a aplicação Spring junto com o RabbitMQ, não consegui criar o "Busy Waiting" conforme sugerido.
Para Solucionar este problema, deixei a configuração da aplicação comentada no docker-compose.yml.Para subir a aplicação foi necessário uso da IDE Spring Tool Suite 4 (Version: 4.3.1.RELEASE).
Também foi necessário alterar o docker-compose.yml fornecido.
Utilizei o phpmyadmin como client iterface para o mysql, também está comentado no docker-compose.yml.

Para executar a aplicação é necessário seguir os passos abaixo:

1 - Entrar do diretório raiz (rabbitmq-amqp)
2 - Executar o comando mvn clean install
3 - Executar docker-compose up ou docker-compose up -d
4 - Abrir a IDE Spring Tool Suite 4
5 - Importar o projeto como Maven project.
5 - Executar o arquivo RabbitmqAmqpApplication.java com Run as > Spring Boot App

 