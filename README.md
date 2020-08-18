# Api-Luiza-Labs
API feita para processo seletivo no Luizalabs


#Configuração do Ambiente
Para rodar o projeto é necessário ter o MongoDB rodando na porta 27017 (padrão),
Foi utilizado Java 8 usando o SpringBoot (2.3.3.RELEASE) para rodá-los importe-o em sua IDE Java que suporte Springboot
O projeto subirá localmente na porta 8080

#Documentação
Foi criado um swagger para a documentação da API podendo ser acessado em: localhost:8080/swagger-ui.html?configUrl=/v3/api-docs/swagger-config
Entretando não foi possível usá-lo devido a problemas de integração entre o Swagger e o oAuth2, para isto, foi criado uma collection do Postman que esta 
disponível em src/main/resources/collection para ser importado no Postman com os endpoints já pré-configurados.

#Autenticação e Autorização
Para os endpoints da aplicação é necessário um Bearer Token, este pode ser adquirido no endpoint da Collection Get Access Token
é necessário fazer a requisição do serviço desta collection para se obter o token, após isso, deve-se usar na parte de Authorization com o Type OAuth 2.0 (ou Bearer Token).
