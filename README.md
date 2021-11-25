# Projeto Ambientes Computacionais e Conectividade
## Spring Boot | REST API

Documentação do Projeto:

<a href="https://secret-sea-60678.herokuapp.com/swagger-ui.html"> Documentação da API via Swagger - Heroku </a>

### Como configurar o ambiente:

- Faça clone do projeto;
- Importe o projeto para sua IDE de preferência, via pom (Maven).  

### Tecnologias utilizadas
- Java 11
- Springboot 2.5.5
- Spring Webflux
- Spring Data JPA
- Banco de dados em memoria H2
- Lombok 
- Springfox 3.0.0 (SwaggerUi)
- Heroku para deploy

### Para fazer os testes dos casos de uso em funcionamento:

API construída para demonstrar a criação e consumo de serviços via arquitetura REST.

Para construção deste projeto foram consumidas as seguintes API's:

<a href="https://ipinfo.io/"> Ip Info - Rastreamento de IP e Geolocalização</a><br>
<a href="https://api.hgbrasil.com/"> HgBrasil - Previsão do Tempo por localidade </a><br>
<a href="https://covid19-brazil-api.vercel.app/"> Covid19-Brazil - Informações Covid 19 </a><br>
<a href="https://newsapi.org/"> NewsApi - APi de Notícias </a><br>


# API REST para teste
Os recursos desta API podem ser acessados através do método HTTP GET para os seguintes EndPoints

**GET** requisições para <a href="https://secret-sea-60678.herokuapp.com/api/v1/ip"> ```/api/v1/ip``` </a> Obter IP e Geolocalização em formato JSON
Somente irá mostrar o IP do usuário se for executado localmente, abrir este endpoint pelo Heroku irá trazer o IP do Heroku.

**GET** requisições para <a href="https://secret-sea-60678.herokuapp.com/api/v1/covid"> ```/api/v1/covid``` </a> Status do Covid no Brasil em formato JSON

**GET** requisições para <a href="https://secret-sea-60678.herokuapp.com/api/v1/covid/sp">```/api/v1/covid/{uf}``` </a> Status do Covid no Estado escolhido em formato JSON
Mude o uf para obter informações sobre outros estados (ex: rj, ba, ce, etc)

**GET** requisições para <a href="https://secret-sea-60678.herokuapp.com/api/v1/covid/previsao"> ```/api/v1/previsao``` </a> Obter previsão do tempo em formato JSON
Este recurso identifica a localidade do usuário através do IP. Testar a API através do Heroku trará a previsão de tempo para a cidade onde o servidor do Heroku está hospedado.

**GET** requisições para <a href="https://secret-sea-60678.herokuapp.com/api/v1/covid/noticias"> ```/api/v1/noticias``` </a> Principais notícias no Brasil em formato JSON

**GET** requisições para <a href="https://secret-sea-60678.herokuapp.com/api/v1/covid/today"> ```/api/v1/today``` </a> Reporte diário com um resumo das informações obtidas nos endpointsa cima em formato JSON

# Tarefas CRUD
### Esta API também conta com um CRUD (Um sistema com métodos HTTP que podem CRIAR, LER, ATUALIZAR ou EXCLUIR) para uma listagem de Tarefas

**GET** requisições para <a href="https://secret-sea-60678.herokuapp.com/api/v1/tarefas"> ```/api/v1/tarefas``` </a> Obter lista de tarefas cadastradas em formato JSON

**POST** requisições para <a href="https://secret-sea-60678.herokuapp.com/api/v1/tarefas"> ```/api/v1/tarefas``` </a> Cadastrar uma nova tarefa em formato JSON

**PUT** requisições para <a href="https://secret-sea-60678.herokuapp.com/api/v1/tarefas/1"> ```/api/v1/tarefas/{id}``` </a> Atualizar uma tarefa cadastrada passando o id como parâmetro 

**DELETE** requisições para <a href="https://secret-sea-60678.herokuapp.com/api/v1/tarefas/1"> ```/api/v1/tarefas/{id}``` </a> Apagar uma tarefa cadastrada passando o id como parâmetro 
